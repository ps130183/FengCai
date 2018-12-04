package com.wzdq.fengcai.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by PengSong on 18/1/8.
 */

public interface IPresenterDelegate {

    void oncreate(@Nullable Bundle savedInstanceState);

    void onStart();

    void onRestart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void addPresenter(BasePresenter presenter);

    BasePresenter getPresenter(Class<? extends BasePresenter> cla);

}
