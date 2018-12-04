package com.wzdq.fengcai.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by PengSong on 18/1/8.
 */

public class PresenterDelegateImpl implements IPresenterDelegate {

    private PresenterManager mPresenterManager;

    public PresenterDelegateImpl() {
        mPresenterManager = new PresenterManager();
    }

    public void addPresenter(BasePresenter presenter){
        mPresenterManager.addPresenter(presenter);
    }

    public BasePresenter getPresenter(Class<? extends BasePresenter> cla){
       return mPresenterManager.getPresenter(cla);
    }

    @Override
    public void oncreate(@Nullable Bundle savedInstanceState) {
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        mPresenterManager.detachView();
    }

}
