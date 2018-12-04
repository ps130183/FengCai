package com.wzdq.fengcai.mvp.module.login.forgetpwd;

import android.text.TextUtils;

import com.blankj.utilcode.util.RegexUtils;
import com.wzdq.fengcai.mvp.base.BasePresenter;
import com.wzdq.fengcai.mvp.base.ModelManager;

import io.reactivex.functions.Consumer;

/**
 * Created by PengSong on 18/12/4.
 */

public class ForgetPasswordPresenterImpl extends BasePresenter<IForgetPasswordContract.IForgetPasswordView> implements IForgetPasswordContract.IForgetPasswordPresenter {

    IForgetPasswordContract.IForgetPasswordModel forgetPasswordModel;

    public ForgetPasswordPresenterImpl(IForgetPasswordContract.IForgetPasswordView mView) {
        super(mView);
        forgetPasswordModel = (IForgetPasswordContract.IForgetPasswordModel) getModel(ModelManager.Token.MODEL_FORGET_PWD);
    }

    @Override
    public boolean isSubmit(String account, String phone, String smsCode, String newPwd, String doublePwd) {
        boolean isSubmit = true;

        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(newPwd) || TextUtils.isEmpty(doublePwd)){
            isSubmit = false;
            getView().showError("请将信息补充完整！");
        } else if(TextUtils.isEmpty(phone) && RegexUtils.isMobileExact(phone)){
            isSubmit = false;
            getView().showError("请填写正确的手机号！");
        } else if (!newPwd.equals(doublePwd)){
            isSubmit = false;
            getView().showError("新密码与确认密码不一致！");
        }

        if (!isSubmit){
            hideLoading();
        }

        return isSubmit;
    }

    @Override
    public void doModifyPassword(String account, String phone, String smsCode, String newPwd, String doublePwd) {
        showLoading();
        if (isSubmit(account,phone,smsCode,newPwd,doublePwd)){
            forgetPasswordModel.doModifyPassword(account,phone,smsCode,newPwd)
                    .subscribe(newSubscriber(new Consumer<String>() {
                        @Override
                        public void accept(String s) throws Exception {
                            getView().modifyPwdSuccess(s);
                        }
                    }));
        }
    }
}
