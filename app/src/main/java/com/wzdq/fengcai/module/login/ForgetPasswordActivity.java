package com.wzdq.fengcai.module.login;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wzdq.fengcai.R;
import com.wzdq.fengcai.base.BaseActivity;

public class ForgetPasswordActivity extends BaseActivity {

    @Override
    public int getContentViewRes() {
        return R.layout.activity_forget_password;
    }

    @Override
    public String getTitleContent() {
        return "找回密码";
    }

    @Override
    public void onFinally(@Nullable Bundle savedInstanceState) {

    }
}
