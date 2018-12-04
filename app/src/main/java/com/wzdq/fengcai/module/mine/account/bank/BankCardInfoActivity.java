package com.wzdq.fengcai.module.mine.account.bank;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wzdq.fengcai.R;
import com.wzdq.fengcai.base.BaseActivity;

public class BankCardInfoActivity extends BaseActivity {

    @Override
    public int getContentViewRes() {
        return R.layout.activity_bank_card_info;
    }

    @Override
    public String getTitleContent() {
        return "银行卡详情";
    }

    @Override
    public void onFinally(@Nullable Bundle savedInstanceState) {

    }
}
