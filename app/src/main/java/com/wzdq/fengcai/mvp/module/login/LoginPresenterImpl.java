package com.wzdq.fengcai.mvp.module.login;

import android.text.TextUtils;

import com.wzdq.fengcai.dto.LoginResultDto;
import com.wzdq.fengcai.mvp.base.BasePresenter;
import com.wzdq.fengcai.mvp.base.ModelManager;

import io.reactivex.functions.Consumer;

/**
 * Created by PengSong on 18/11/29.
 */

public class LoginPresenterImpl extends BasePresenter<ILoginContract.ILoginView> implements ILoginContract.ILoginPresenter {

    private ILoginContract.ILoginModel mLoginModel;

    public LoginPresenterImpl(ILoginContract.ILoginView mView) {
        super(mView);
        mLoginModel = (LoginModelImpl) getModel(ModelManager.Token.MODEL_LOGIN);
    }

    @Override
    public void doLogin(String account, String password) {
        if (TextUtils.isEmpty(account)){
            getView().showError("请输入会员账号");
            return;
        } else if (TextUtils.isEmpty(password)){
            getView().showError("请输入登录密码");
            return;
        }
        showLoading();
        mLoginModel.login(account,password)
                .subscribe(newSubscriber(new Consumer<LoginResultDto>() {
                    @Override
                    public void accept(LoginResultDto loginResultDto) throws Exception {
                        getView().loginSuccess(1);
                    }
                }));


    }

    @Override
    public void doForgetPassword() {
        getView().startForgetPassword();
    }

    @Override
    public void doActivateAccount() {
        getView().startActivateAccount();
    }

    @Override
    public void checkInputFinish(String content, int inputType) {
        getView().showInputResult(inputType,!TextUtils.isEmpty(content));
    }
}
