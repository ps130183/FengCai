package com.wzdq.fengcai.module.mine.setting;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.wzdq.fengcai.R;
import com.wzdq.fengcai.base.BaseActivity;

public class ModifyPasswordTwoActivity extends BaseActivity {

    @Override
    public int getContentViewRes() {
        return R.layout.activity_modify_password_two;
    }


    @Override
    public void onFinally(@Nullable Bundle savedInstanceState) {
        initView();
    }

    private void initView(){
        int modifyType = getIntent().getIntExtra("modifyType",-1);
        TextView tvTitle = mViewManager.findView(R.id.simple_tb_title_name);

        EditText inputNewPassword = mViewManager.findView(R.id.inputNewPassword);
        EditText inputDoublePassword = mViewManager.findView(R.id.inputDoublePassword);


        switch (modifyType){
            case 0:
                tvTitle.setText("修改登录密码");
                inputNewPassword.setHint("请输入新的登录密码");
                inputDoublePassword.setHint("请输入新的登录密码");
                break;
            case 1:
                tvTitle.setText("修改支付密码");
                inputNewPassword.setHint("请输入新的支付密码");
                inputDoublePassword.setHint("请输入新的支付密码");
                break;
        }
    }
}
