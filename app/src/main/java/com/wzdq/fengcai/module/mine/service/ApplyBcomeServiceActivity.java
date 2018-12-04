package com.wzdq.fengcai.module.mine.service;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wzdq.fengcai.R;
import com.wzdq.fengcai.base.BaseActivity;

/**
 * 申请成为 服务中心 也就是报单员或报单中心
 */
public class ApplyBcomeServiceActivity extends BaseActivity {

    @Override
    public int getContentViewRes() {
        return R.layout.activity_apply_bcome_service;
    }

    @Override
    public String getTitleContent() {
        return "申请成为报单员";
    }

    @Override
    public void onFinally(@Nullable Bundle savedInstanceState) {

    }
}
