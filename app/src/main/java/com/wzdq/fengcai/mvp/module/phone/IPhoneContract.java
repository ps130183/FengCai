package com.wzdq.fengcai.mvp.module.phone;

import com.wzdq.fengcai.mvp.base.IBaseView;

import io.reactivex.Observable;

/**
 * 关于手机的一些请求
 * 获取短信验证码
 * Created by PengSong on 18/12/4.
 */

public interface IPhoneContract {
    interface IPhoneView extends IBaseView{
        void requestSmsCodeResult(String result);
    }

    interface IPhoneModel {
        Observable<String> getSmsCode(String phone);
    }
    interface IPhonePresenter{
        void getSmsCode(String phone);
    }
}
