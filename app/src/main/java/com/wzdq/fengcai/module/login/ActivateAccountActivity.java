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

public class ActivateAccountActivity extends BaseActivity implements IActivateContract.IActivateView, View.OnClickListener {

    IActivateContract.IActivatePresenter mActivatePresenter;

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
        mActivatePresenter = (IActivateContract.IActivatePresenter) getPresenter(ActivatePresenterImpl.class);
    }

    @Override
    public void onFinally(@Nullable Bundle savedInstanceState) {
        String account = getIntent().getStringExtra("account");
        mViewManager.setText(R.id.inputAccount,account == null? "" : account);
        mViewManager.setClickListener(R.id.btnConfirm,this);
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

        mActivatePresenter.doActivateAcount(inputAccount.getText().toString(),inputPhone.getText().toString(),
                inputCode.getText().toString(),inputNewPassword.getText().toString(),inputPayPassword.getText().toString());
    }
}
