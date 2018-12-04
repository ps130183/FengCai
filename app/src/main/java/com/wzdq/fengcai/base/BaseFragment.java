package com.wzdq.fengcai.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;;

import com.wzdq.fengcai.mvp.base.BasePresenter;
import com.wzdq.fengcai.mvp.base.IBaseView;
import com.wzdq.fengcai.mvp.base.IBasePresenter;
import com.wzdq.fengcai.mvp.base.PresenterDelegateImpl;
import com.wzdq.fengcai.mvp.base.PresenterManager;
import com.wzdq.fengcai.mvp.base.ProxyPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by PengSong on 18/1/10.
 */

public abstract class BaseFragment extends Fragment implements IBaseView {

    protected ViewManager mViewManager;

    private View mRootView;

    private Toast mToast;

    private ProxyPresenter proxyPresenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        proxyPresenter.onDestroy();
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutRes = getLayoutRes();
        if (mRootView == null){
            mRootView = getRootView(inflater, container, layoutRes);
//            ButterKnife.bind(this,mRootView);
            //页面的控件管理器
            mViewManager = new ViewManager(mRootView);

            //presenter代理
            proxyPresenter = new ProxyPresenter(new PresenterDelegateImpl());
            proxyPresenter.oncreate(savedInstanceState);
            bindingPresenter();

//            mSwipeRefresh = mRootView.findViewById(R.id.swipeRefresh);
//            mXRefreshView = mRootView.findViewById(R.id.xRefreshView);

            onCreateView(savedInstanceState);
        }

        return mRootView;
    }


    @Override
    public void addPresenter(BasePresenter presenter) {
        proxyPresenter.addPresenter(presenter);
    }


    @Override
    public BasePresenter getPresenter(Class<? extends BasePresenter> cla) {
        return proxyPresenter.getPresenter(cla);
    }

    private View getRootView(LayoutInflater inflater, ViewGroup parent, @LayoutRes int layoutRes) {
        return inflater.inflate(layoutRes, parent, false);
    }

    /**
     * 绑定presenter
     */
    protected void bindingPresenter(){};

    /**
     * 获取布局文件Id
     *
     * @return
     */
    public abstract @LayoutRes
    int getLayoutRes();

    /**
     * 页面初始化完成，开始真正的业务代码
     *
     * @param savedInstanceState
     */
    public abstract void onCreateView(@Nullable Bundle savedInstanceState);


    /**
     * 打印提示信息
     *
     * @param message
     */
    protected void showToast(String message) {
        if (getActivity() == null){
            return;
        }
        if (mToast == null) {
            mToast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(message);
        }
        mToast.show();
    }

    @Override
    public void showLoading() {
//        if (mSwipeRefresh != null && !mSwipeRefresh.isRefreshing()) {
//            mSwipeRefresh.setRefreshing(true);
//        } else if (mXRefreshView != null && !mXRefreshView.mPullRefreshing){
//            mXRefreshView.startRefresh();
//        }

//        if (dialogLoading == null){
//            dialogLoading = new DialogLoading(getContext());
//        }
//
//        dialogLoading.show();
    }

    @Override
    public void hideLoading() {
//        if (mSwipeRefresh != null && mSwipeRefresh.isRefreshing()) {
//            mSwipeRefresh.setRefreshing(false);
//        }  else if (mXRefreshView != null && mXRefreshView.mPullRefreshing){
//            mXRefreshView.stopRefresh();
//        }
//        if (dialogLoading != null){
//            dialogLoading.hide();
//        }

    }

    @Override
    public void showError(String message) {
//        if (mXRefreshView != null && mXRefreshView.mPullRefreshing){
//            mXRefreshView.stopRefresh(false);
//        }
//        if (this.getClass() == HomeNewFragment.class || this.getClass() == PersonalCenterFragment.class){
//            return;
//        }
        showToast(message);
    }

    @Override
    public void userIsNotLogin() {
//        Constant.userLoginInfo.detachView();
//        if (this.getClass() != HomeNewFragment.class && this.getClass() != PersonalCenterFragment.class){
//            startActivity(LoginActivity.class);
//        }

    }

    /**
     * 启动activity
     * @param activityClass
     */
    public void startActivity(Class activityClass){
        startActivity(activityClass,null);
    }

    /**
     * 启动activity
     * @param activityClass
     * @param bundle
     */
    public void startActivity(Class activityClass, Bundle bundle) {
        Intent intent = new Intent(getContext(), activityClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void defaultMethod(String s) {

    }
}

