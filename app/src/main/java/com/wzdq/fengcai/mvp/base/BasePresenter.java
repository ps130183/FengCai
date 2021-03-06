package com.wzdq.fengcai.mvp.base;

import com.blankj.utilcode.util.LogUtils;
import com.wzdq.fengcai.Obserable.IObservable;
import com.wzdq.fengcai.Obserable.IObserver;
import com.wzdq.fengcai.retrofit.RetCode;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.exceptions.OnErrorNotImplementedException;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

/**
 * Created by PengSong on 18/1/8.
 */

public abstract class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {

    private V mView;
    private CompositeDisposable mCompositeDisposable;

    public BasePresenter(V mView) {
        attachView(mView);
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void attachView(V view) {
        mView = view;
    }

    @Override
    public void detachView() {
        LogUtils.a("detach presenter bind view");
        mView = null;
        clearSubscription();
    }


    /**
     * 根据 token 获取对应的model
     * @param token
     * @return
     */
    public Object getModel(String token){
        return ModelManager.getInstance().getModel(token);
    }

    /**
     * 是否与View建立连接
     * @return
     */
    @Override
    public boolean isViewAttached(){
        return mView != null;
    }

    /**
     * 获取当前View
     * @return
     */
    protected V getView() {
        return mView;
    }

    protected void showLoading(){
        if (isViewAttached()){
            mView.showLoading();
        }
    }

    protected void hideLoading(){
        if (isViewAttached()){
            mView.hideLoading();
        }
    }

    /**
     * 检查是否与当前View建立连接，没有则抛异常
     */
    public void checkViewAttached(){
        if (!isViewAttached()){
            throw new MvpViewNotAttachedException();
        }
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("请求数据前请先调用 attachView(IBaseView) 方法与View建立连接");
        }
    }


    /**
     * 创建观察者
     *
     * @param onNext
     * @param <T>
     * @return
     */
    protected <T> Observer<T> newSubscriber(final Consumer<? super T> onNext) {
        DisposableObserver<T> observer = new DisposableObserver<T>() {
            @Override
            public void onNext(T t) {
                hideLoading();
                try {
                    onNext.accept(t);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
                hideLoading();
                if (e instanceof BaseModel.APIException) { //后台报的错误
                    BaseModel.APIException exception = (BaseModel.APIException) e;
                    mView.showError(exception.getMessage());
                    if (RetCode.USER_IS_NOT_LOGIN.getStatus().equals(exception.code)){//未登录
                        mView.userIsNotLogin();
                    }
//                    if (RetCode.USER_IS_NOT_LOGIN.getStatus().equals(exception.code)){//用户未登录
//                        ActivityUtils.startActivity(LoginActivity.class);
//                    }
                } else if (e instanceof SocketTimeoutException) {
                    mView.showError("请求超时，请稍后再试！");
                } else if (e instanceof ConnectException) {
                    mView.showError("连接服务器失败，请稍后再试！");
                } else if (e instanceof HttpException){
                    mView.showError("网络异常，请稍后再试！");
                } else if (e instanceof OnErrorNotImplementedException){
                    mView.showError("找不到请求的接口");
                }else if (e instanceof NullPointerException) {
                    try {
                        onNext.accept((T) "");
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else {
                    e.printStackTrace();
                }
            }

            @Override
            public void onComplete() {
                hideLoading();
            }
        };

        mCompositeDisposable.add(observer);
        return observer;
    }

    public void clearSubscription() {
        mCompositeDisposable.dispose();
        mCompositeDisposable.clear();
    }
}


