package com.wzdq.fengcai.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by PengSong on 18/1/8.
 */

public class ProxyPresenter implements IPresenterDelegate {

    private IPresenterDelegate presenterDelegate;

    public ProxyPresenter(IPresenterDelegate presenterDelegate) {
        this.presenterDelegate = presenterDelegate;
    }

    @Override
    public void oncreate(@Nullable Bundle savedInstanceState) {
        presenterDelegate.oncreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        presenterDelegate.onStart();
    }

    @Override
    public void onRestart() {
        presenterDelegate.onRestart();
    }

    @Override
    public void onResume() {
        presenterDelegate.onResume();
    }

    @Override
    public void onPause() {
        presenterDelegate.onPause();
    }

    @Override
    public void onStop() {
        presenterDelegate.onStop();
    }

    @Override
    public void onDestroy() {
        presenterDelegate.onDestroy();
    }

    @Override
    public void addPresenter(BasePresenter presenter) {
        presenterDelegate.addPresenter(presenter);
    }

    @Override
    public BasePresenter getPresenter(Class<? extends BasePresenter> cla) {
        return presenterDelegate.getPresenter(cla);
    }

}
