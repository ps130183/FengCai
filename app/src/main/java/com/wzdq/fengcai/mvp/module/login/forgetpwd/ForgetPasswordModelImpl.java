package com.wzdq.fengcai.mvp.module.login.forgetpwd;

import com.wzdq.fengcai.mvp.base.BaseModel;

import io.reactivex.Observable;

/**
 * Created by PengSong on 18/12/4.
 */

public class ForgetPasswordModelImpl extends BaseModel implements IForgetPasswordContract.IForgetPasswordModel {

    @Override
    public Observable<String> doModifyPassword(String account, String phone, String smsCode, String newPwd) {
        return getService().doModifyPassword(account,newPwd)
                .compose(this.<String>applySchedulers());
    }
}
