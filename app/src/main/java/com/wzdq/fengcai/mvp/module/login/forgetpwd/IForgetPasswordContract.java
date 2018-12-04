package com.wzdq.fengcai.mvp.module.login.forgetpwd;

import com.wzdq.fengcai.mvp.base.IBaseView;

import io.reactivex.Observable;

/**
 * Created by PengSong on 18/12/4.
 */

public interface IForgetPasswordContract {
    interface IForgetPasswordView extends IBaseView{
        void modifyPwdSuccess(String s);
    }

    interface IForgetPasswordModel{
        Observable<String> doModifyPassword(String account,String phone,String smsCode,String newPwd);
    }

    interface IForgetPasswordPresenter{
        boolean isSubmit(String account,String phone,String smsCode,String newPwd,String doublePwd);
        void doModifyPassword(String account,String phone,String smsCode,String newPwd,String doublePwd);
    }
}
