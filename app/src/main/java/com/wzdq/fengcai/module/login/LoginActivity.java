package com.wzdq.fengcai.module.login;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ruffian.library.widget.REditText;
import com.wzdq.fengcai.MainActivity;
import com.wzdq.fengcai.R;
import com.wzdq.fengcai.base.BaseActivity;
import com.wzdq.fengcai.mvp.module.login.ILoginContract;
import com.wzdq.fengcai.mvp.module.login.LoginPresenterImpl;

public class LoginActivity extends BaseActivity implements View.OnClickListener, ILoginContract.ILoginView {

    private static final int INPUT_TYPE_ACCOUNT = 0;
    private static final int INPUT_TYPE_PASSWORD = 1;


    private boolean isInputAccountFinish = false;
    private boolean isInputPasswordFinish = false;

    private ILoginContract.ILoginPresenter loginPresenter;
    @Override
    public int getContentViewRes() {
        return R.layout.activity_login;
    }

    @Override
    public String getTitleContent() {
        return "登录";
    }

    @Override
    protected void bindingPresenter() {
        addPresenter(new LoginPresenterImpl(this));
        loginPresenter = (LoginPresenterImpl) getPresenter(LoginPresenterImpl.class);
    }

    @Override
    public void onFinally(@Nullable Bundle savedInstanceState) {
        initView();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        String account = intent.getStringExtra("account");
        if (!TextUtils.isEmpty(account)){
            mViewManager.setText(R.id.etAccount,account);
        }
    }

    private void initView(){
        TextView activateAccount = mViewManager.findView(R.id.activateAccount);
        activateAccount.setText(Html.fromHtml("<u>激活账户 >></u>"));
        activateAccount.setOnClickListener(this);


        mViewManager.setClickListener(R.id.forgetPassword,this);

        REditText etAccount = mViewManager.findView(R.id.etAccount);
        REditText etPassword = mViewManager.findView(R.id.etPassword);

        final Button btnLogin = mViewManager.findView(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        etAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loginPresenter.checkInputFinish(s.toString(),INPUT_TYPE_ACCOUNT);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loginPresenter.checkInputFinish(s.toString(),INPUT_TYPE_PASSWORD);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin://登录
                REditText etAccount = mViewManager.findView(R.id.etAccount);
                REditText etPassword = mViewManager.findView(R.id.etPassword);
                loginPresenter.doLogin(etAccount.getText().toString(),etPassword.getText().toString());
                break;
            case R.id.activateAccount://激活账户
                loginPresenter.doActivateAccount();
                break;
            case R.id.forgetPassword://忘记密码
                loginPresenter.doForgetPassword();
                break;
        }
    }

    @Override
    public void loginSuccess(int pageType) {
        if (pageType == 1){
            showToast("登录成功");
            startActivity(MainActivity.class);
        }
    }

    @Override
    public void startForgetPassword() {
        EditText etAccount = mViewManager.findView(R.id.etAccount);
        String account = etAccount.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString("account",account);
        startActivity(ForgetPasswordActivity.class,bundle);
    }

    @Override
    public void startActivateAccount() {
        EditText etAccount = mViewManager.findView(R.id.etAccount);
        String account = etAccount.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString("account",account);
        startActivity(ActivateAccountActivity.class,bundle);
    }

    @Override
    public void showInputResult(int inputType, boolean isFinished) {
        if (inputType == INPUT_TYPE_ACCOUNT){
            isInputAccountFinish = isFinished;
        } else if (inputType == INPUT_TYPE_PASSWORD){
            isInputPasswordFinish = isFinished;
        }

        Button btnLogin = mViewManager.findView(R.id.btnLogin);
        btnLogin.setEnabled(isInputAccountFinish && isInputPasswordFinish);
    }
}
