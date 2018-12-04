package com.wzdq.fengcai.module.mine.setting;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.wzdq.fengcai.R;
import com.wzdq.fengcai.base.BaseActivity;
import com.wzdq.fengcai.view.MTextView;

public class ModifyPasswordActivity extends BaseActivity implements View.OnClickListener {

    @Override
    public int getContentViewRes() {
        return R.layout.activity_modify_password;
    }

    @Override
    public String getTitleContent() {
        return "修改密码";
    }

    @Override
    public void onFinally(@Nullable Bundle savedInstanceState) {
        initView();
    }

    private void initView(){
        mViewManager.findView(R.id.modifyLoginPassword).setOnClickListener(this);
        mViewManager.findView(R.id.modifyPayPassword).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()){
            case R.id.modifyLoginPassword://登录密码
                bundle.putInt("modifyType",0);
                startActivity(ModifyPasswordTwoActivity.class,bundle);
                break;
            case R.id.modifyPayPassword://支付密码
                bundle.putInt("modifyType",1);
                startActivity(ModifyPasswordTwoActivity.class,bundle);
                break;
        }
    }
}
