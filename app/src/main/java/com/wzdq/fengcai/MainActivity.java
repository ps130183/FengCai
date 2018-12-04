package com.wzdq.fengcai;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.wzdq.fengcai.base.BaseActivity;
import com.wzdq.fengcai.base.BaseTitleBar;
import com.wzdq.fengcai.dto.WithDrawAccountDto;
import com.wzdq.fengcai.entity.TabEntity;
import com.wzdq.fengcai.event.ChangeStatusBarEvent;
import com.wzdq.fengcai.module.shop.ShopFragment;
import com.wzdq.fengcai.module.mine.MineFragment;
import com.wzdq.fengcai.mvp.module.home.HomePresenter;
import com.wzdq.fengcai.mvp.module.withdraw.WithDrawPresenter;
import com.wzdq.fengcai.mvp.module.home.IHomeView;
import com.wzdq.fengcai.mvp.module.withdraw.IWithDrawView;
import com.wzdq.fengcai.utils.statusBar.StatusManager;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements IHomeView, IWithDrawView {

    private String[] tabLayoutTitls = {"首页","我的"};
    private int[] tabSelectedIcons = {R.mipmap.icon_tab_selected1,R.mipmap.icon_tab_selected2};
    private int[] tabUnSelectedIcons = {R.mipmap.icon_tab_un_selected1,R.mipmap.icon_tab_un_selected2};
    private ArrayList<Fragment> mFragments;
    private CommonTabLayout mTabLayout;

    @Override
    public int getContentViewRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void bindingPresenter() {
        addPresenter(new HomePresenter(this));
        addPresenter(new WithDrawPresenter(this));
    }

    @Override
    public BaseTitleBar createTitleBar() {
        return null;
    }


    @Override
    public void onFinally(@Nullable Bundle savedInstanceState) {
        StatusManager.getInstance().initStatusTheme(this,false,false);
        initView();

        PermissionUtils.permission(PermissionConstants.STORAGE)
                .callback(new PermissionUtils.SimpleCallback() {
                    @Override
                    public void onGranted() {

                    }

                    @Override
                    public void onDenied() {

                    }
                }).request();
    }

    private void initView(){
        mTabLayout = mViewManager.findView(R.id.tabLayout);
        mFragments = new ArrayList<>();
        mFragments.add(ShopFragment.newInstance(null));
        mFragments.add(MineFragment.newInstance(null));

        ArrayList<CustomTabEntity> tabEntities = new ArrayList<>();
        for (int i = 0; i < tabLayoutTitls.length; i++){
            tabEntities.add(new TabEntity(tabLayoutTitls[i],tabSelectedIcons[i],tabUnSelectedIcons[i]));
        }
        mTabLayout.setTabData(tabEntities,this,R.id.container,mFragments);
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public boolean onTabSelect(int position) {
                switch (position){
                    case 0://商城
                        EventBus.getDefault().post(new ChangeStatusBarEvent());
                        break;
                    case 1://个人中心
                        StatusManager.getInstance().initStatusTheme(MainActivity.this,false,false,StatusManager.DEFAULT_BG_COLOR);
                        break;
                }
                return true;
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        HomePresenter presenter = (HomePresenter) getPresenter(HomePresenter.class);
        presenter.getString();

        WithDrawPresenter drawPresenter = (WithDrawPresenter) getPresenter(WithDrawPresenter.class);
        drawPresenter.submitWithdraw(null,"111");
    }

    @Override
    public void showMessage(String string) {
        LogUtils.d(string);
    }

    @Override
    public void withdrawSuccess(String money) {
        LogUtils.d(money);
    }

    @Override
    public void creatOrUpdateSuccess() {

    }

    @Override
    public void showWithDrawList(List<WithDrawAccountDto> withDrawAccountDtos) {

    }

    @Override
    public void deleteSuccess(WithDrawAccountDto withDrawAccountDto) {

    }
}
