package com.wzdq.fengcai.module.login;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.wzdq.fengcai.R;
import com.wzdq.fengcai.base.BaseActivity;
import com.wzdq.fengcai.mvp.module.login.forgetpwd.ForgetPasswordPresenterImpl;
import com.wzdq.fengcai.mvp.module.login.forgetpwd.IForgetPasswordContract;
import com.wzdq.fengcai.mvp.module.phone.IPhoneContract;
import com.wzdq.fengcai.mvp.module.phone.PhonePresenterImpl;
import com.wzdq.fengcai.view.MTextView;

public class ForgetPasswordActivity extends BaseActivity implements IForgetPasswordContract.IForgetPasswordView, IPhoneContract.IPhoneView, View.OnClickListener {

    private IForgetPasswordContract.IForgetPasswordPresenter forgetPasswordPresenter;
    private IPhoneContract.IPhonePresenter phonePresenter;

    @Override
    public int getContentViewRes() {
        return R.layout.activity_forget_password;
    }

    @Override
    public String getTitleContent() {
        return "找回密码";
    }

    @Override
    protected void bindingPresenter() {
        addPresenter(new ForgetPasswordPresenterImpl(this));
        addPresenter(new PhonePresenterImpl(this));
        forgetPasswordPresenter = (IForgetPasswordContract.IForgetPasswordPresenter) getPresenter(ForgetPasswordPresenterImpl.class);
        phonePresenter = (IPhoneContract.IPhonePresenter) getPresenter(PhonePresenterImpl.class);
    }

    @Override
    public void onFinally(@Nullable Bundle savedInstanceState) {
        String account = getIntent().getStringExtra("account");
        mViewManager.setText(R.id.inputAccount,account == null? "" : account);

        mViewManager.setClickListener(R.id.sendCode,this);
        mViewManager.setClickListener(R.id.btnConfirm,this);

        MTextView sendCode = mViewManager.findView(R.id.sendCode);
        sendCode.setOnStartCountDownTimeListener(new MTextView.OnStartCountDownTimeListener() {
            @Override
            public void start() {
                EditText inputPhone = mViewManager.findView(R.id.inputPhone);
                phonePresenter.getSmsCode(inputPhone.getText().toString());
            }
        });
    }

    @Override
    public void modifyPwdSuccess(String s) {
        showToast("密码修改成功，请登录！");
        startActivity(LoginActivity.class,getIntent().getExtras());
    }

    @Override
    public void requestSmsCodeResult(String result) {
        showToast("短信发送成功，请注意查收！");
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()){
            case R.id.sendCode://发送短信验证码
                MTextView sendCode = (MTextView) v;
                sendCode.startCountDown();
                break;
            case R.id.btnConfirm:
                EditText inputPhone = mViewManager.findView(R.id.inputPhone);
                EditText inputAccount = mViewManager.findView(R.id.inputAccount);
                EditText inputCode = mViewManager.findView(R.id.inputCode);
                EditText inputNewPassword = mViewManager.findView(R.id.inputNewPassword);
                EditText inputDoublePassword = mViewManager.findView(R.id.inputDoubleNewPassword);

                forgetPasswordPresenter.doModifyPassword(inputAccount.getText().toString(),inputPhone.getText().toString(),
                        inputCode.getText().toString(),inputNewPassword.getText().toString(),inputDoublePassword.getText().toString());
                break;
        }
    }
}
