package com.wzdq.fengcai.module.mine.shareholder;

import android.support.annotation.Nullable;
import android.os.Bundle;

import com.wzdq.fengcai.R;
import com.wzdq.fengcai.base.BaseActivity;

public class ApplyBecomeShareholderOfUserActivity extends BaseActivity {

    @Override
    public int getContentViewRes() {
        return R.layout.activity_apply_become_shareholder_of_user;
    }

    @Override
    public String getTitleContent() {
        return "股东分润系统申请";
    }

    @Override
    public void onFinally(@Nullable Bundle savedInstanceState) {

    }
}
