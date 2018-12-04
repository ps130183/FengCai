package com.wzdq.fengcai.module.mine.setting;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wzdq.fengcai.R;
import com.wzdq.fengcai.base.BaseActivity;

public class BindPhoneActivity extends BaseActivity {

    @Override
    public int getContentViewRes() {
        return R.layout.activity_bind_phone;
    }

    @Override
    public String getTitleContent() {
        return "绑定手机";
    }

    @Override
    public void onFinally(@Nullable Bundle savedInstanceState) {

    }
}
