package com.wzdq.fengcai.mvp.module.home;

import com.blankj.utilcode.util.LogUtils;
import com.wzdq.fengcai.mvp.base.BasePresenter;
import com.wzdq.fengcai.mvp.base.ModelManager;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by PengSong on 18/11/14.
 */

public class HomePresenter extends BasePresenter<IHomeView> {

    private HomeModel homeModel;

    public HomePresenter(IHomeView mView) {
        super(mView);
        homeModel = (HomeModel) getModel(ModelManager.Token.MODEL_HOME);
    }

    public void getString(){
        LogUtils.d("发送请求");
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newSubscriber(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        getView().showMessage("this is Hello Home !!!");
                    }
                }));

    }

}
