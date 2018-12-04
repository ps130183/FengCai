package com.wzdq.fengcai.module.mine.setting;

import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.LogUtils;
import com.wzdq.fengcai.R;
import com.wzdq.fengcai.base.BaseActivity;
import com.wzdq.fengcai.view.MTextView;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    @Override
    public int getContentViewRes() {
        return R.layout.activity_setting;
    }

    @Override
    public String getTitleContent() {
        return "设置";
    }

    @Override
    public void onFinally(@Nullable Bundle savedInstanceState) {
        initView();
    }

    private void initView(){
        MTextView bindPhone = mViewManager.findView(R.id.bindPhone);
        bindPhone.setOnClickListener(this);
        mViewManager.findView(R.id.modifyPassword).setOnClickListener(this);
        mViewManager.setClickListener(R.id.contactUs,this);
        mViewManager.setClickListener(R.id.aboutUs,this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bindPhone:
//                TextView noBindPhone = mViewManager.findView(R.id.noBindPhone);
//                MTextView bindPhone = (MTextView) v;
//                noBindPhone.setText("18888888888");
//                noBindPhone.setTextColor(ContextCompat.getColor(mInstance,R.color.colorBlockRgb888888));
//                ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams) noBindPhone.getLayoutParams();
//                lp.setMargins(0,0, ConvertUtils.dp2px(15),0);
//                bindPhone.setImageIcon(0);

                startActivity(BindPhoneActivity.class);

                break;
            case R.id.modifyPassword://修改密码
                startActivity(ModifyPasswordActivity.class);
                break;
            case R.id.contactUs://联系我们
                startActivity(ServiceActivity.class);
                break;
            case R.id.aboutUs:
                startActivity(AboutUsActivity.class);
                LogUtils.d("关于我们");
                break;
        }
    }
}
