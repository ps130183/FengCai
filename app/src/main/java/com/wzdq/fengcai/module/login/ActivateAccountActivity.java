package com.wzdq.fengcai.module.login;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.wzdq.fengcai.R;
import com.wzdq.fengcai.base.BaseActivity;
import com.wzdq.fengcai.mvp.module.login.activate.ActivatePresenterImpl;
import com.wzdq.fengcai.mvp.module.login.activate.IActivateContract;
import com.wzdq.fengcai.mvp.module.phone.IPhoneContract;
import com.wzdq.fengcai.mvp.module.phone.PhonePresenterImpl;
import com.wzdq.fengcai.view.MTextView;

public class ActivateAccountActivity extends BaseActivity implements IActivateContract.IActivateView, View.OnClickListener, IPhoneContract.IPhoneView {

    private IActivateContract.IActivatePresenter mActivatePresenter;
    private IPhoneContract.IPhonePresenter phonePresenter;
    @Override
    public int getContentViewRes() {
        return R.layout.activity_activate_account;
    }

    @Override
    public String getTitleContent() {
        return "激活账号";
    }

    @Override
    protected void bindingPresenter() {
        addPresenter(new ActivatePresenterImpl(this));
        addPresenter(new PhonePresenterImpl(this));
        mActivatePresenter = (IActivateContract.IActivatePresenter) getPresenter(ActivatePresenterImpl.class);
        phonePresenter = (IPhoneContract.IPhonePresenter) getPresenter(PhonePresenterImpl.class);
    }

    @Override
    public void onFinally(@Nullable Bundle savedInstanceState) {
        String account = getIntent().getStringExtra("account");
        mViewManager.setText(R.id.inputAccount,account == null? "" : account);
        mViewManager.setClickListener(R.id.btnConfirm,this);
        mViewManager.setClickListener(R.id.sendCode,this);

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
    public void activateAccountResult(String result) {
        showToast("激活成功");
        startActivity(LoginActivity.class,getIntent().getExtras());
    }

    @Override
    public void onClick(View v) {
        EditText inputAccount = mViewManager.findView(R.id.inputAccount);
        EditText inputPhone = mViewManager.findView(R.id.inputPhone);
        EditText inputCode = mViewManager.findView(R.id.inputCode);
        EditText inputNewPassword = mViewManager.findView(R.id.inputNewPassword);
        EditText inputPayPassword = mViewManager.findView(R.id.inputPayPassword);

        switch (v.getId()){
            case R.id.btnConfirm:
                mActivatePresenter.doActivateAcount(inputAccount.getText().toString(),inputPhone.getText().toString(),
                        inputCode.getText().toString(),inputNewPassword.getText().toString(),inputPayPassword.getText().toString());
                break;
            case R.id.sendCode:
                MTextView sendCode = (MTextView) v;
                sendCode.startCountDown();
                break;
        }


    }

    @Override
    public void requestSmsCodeResult(String result) {
        showToast("短信发送成功，请注意查收！");
    }
}
