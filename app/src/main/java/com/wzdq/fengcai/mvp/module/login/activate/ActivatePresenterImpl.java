package com.wzdq.fengcai.mvp.module.login.activate;

import android.text.TextUtils;

import com.blankj.utilcode.util.RegexUtils;
import com.wzdq.fengcai.mvp.base.BasePresenter;
import com.wzdq.fengcai.mvp.base.ModelManager;

import io.reactivex.functions.Consumer;

/**
 * Created by PengSong on 18/12/4.
 */

public class ActivatePresenterImpl extends BasePresenter<IActivateContract.IActivateView> implements IActivateContract.IActivatePresenter {


    IActivateContract.IActivateModel activateModel;

    public ActivatePresenterImpl(IActivateContract.IActivateView mView) {
        super(mView);
        activateModel = (IActivateContract.IActivateModel) getModel(ModelManager.Token.MODEL_ACTIVATE_ACCOUNT);
    }

    @Override
    public boolean isSubmitActivate(String account, String phone, String smsCode, String loginPwd, String payPwd) {
        boolean isActivate = true;
        if (TextUtils.isEmpty(account)
                || TextUtils.isEmpty(smsCode) || TextUtils.isEmpty(loginPwd) || TextUtils.isEmpty(payPwd)){
            getView().showError("请将信息补充完整！");
            isActivate = false;
        } else if (TextUtils.isEmpty(phone) && RegexUtils.isMobileExact(phone)){
            getView().showError("请填写正确的手机号");
            isActivate = false;
        }

        if (!isActivate){
            hideLoading();
        }
        return isActivate;
    }

    @Override
    public void doActivateAcount(String account, String phone, String smsCode, String loginPwd, String payPwd) {
        showLoading();
        if (isSubmitActivate(account,phone,smsCode,loginPwd,payPwd)){
            activateModel.doActivateAccount(account,phone,smsCode,loginPwd,payPwd)
                    .subscribe(newSubscriber(new Consumer<String>() {
                        @Override
                        public void accept(String s) throws Exception {
                            getView().activateAccountResult(s);
                        }
                    }));
        }
    }
}
