package com.wzdq.fengcai.mvp.base;

import com.wzdq.fengcai.Obserable.IObservable;

/**
 * Created by PengSong on 18/1/8.
 */

public interface IBasePresenter<V extends IBaseView> {
    /**
     * 绑定View
     * @param view
     */
    void attachView(V view);

    /**
     * 解绑View
     */
    void detachView();

    /**
     * 是否和view绑定
     * @return
     */
    boolean isViewAttached();


}
