package com.wzdq.fengcai.mvp.module.login.activate;

import com.wzdq.fengcai.mvp.base.BaseModel;

import io.reactivex.Observable;

/**
 * Created by PengSong on 18/12/4.
 */

public class ActivateModelImpl extends BaseModel implements IActivateContract.IActivateModel {

    @Override
    public Observable<String> doActivateAccount(String account, String phone, String smsCode, String loginPwd, String payPwd) {
        return getService().doActivateAccount(account,loginPwd,payPwd)
                .compose(this.<String>applySchedulers());
    }
}
