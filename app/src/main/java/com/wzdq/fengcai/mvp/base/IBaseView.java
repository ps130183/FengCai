package com.wzdq.fengcai.mvp.base;

import com.wzdq.fengcai.Obserable.IObserver;

/**
 * Created by PengSong on 18/1/8.
 */

public interface IBaseView {

    void addPresenter(BasePresenter presenter);

    BasePresenter getPresenter(Class<? extends BasePresenter> cla);

    /**
     * 显示加载动画
     */
    void showLoading();

    /**
     * 隐藏加载动画
     */
    void hideLoading();

    /**
     * 显示错误信息
     * @param message
     */
    void showError(String message);

    /**
     * 未登录
     */
    void userIsNotLogin();

}
