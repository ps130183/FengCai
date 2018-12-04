package com.wzdq.fengcai.module.mine.setting;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.wzdq.fengcai.R;
import com.wzdq.fengcai.base.BaseActivity;
import com.wzdq.fengcai.utils.Constant;
import com.wzdq.fengcai.utils.dialog.DialogUtils;


public class ServiceActivity extends BaseActivity {

    @Override
    public int getContentViewRes() {
        return R.layout.activity_service;
    }

    @Override
    public String getTitleContent() {
        return "联系客服";
    }

    @Override
    public void onFinally(@Nullable Bundle savedInstanceState) {
        mViewManager.setText(R.id.tel_service,Constant.SERVICE_KEFU_PHONE);
        mViewManager.setText(R.id.tel_technology, Constant.SERVICE_JISHU_PHONE);
    }

    public void callService(View view) {
        TextView telService = mViewManager.findView(R.id.tel_service);
        final String phone = telService.getText().toString();
        DialogUtils.showDefaultAlertDialog("是否拨打客服电话：" + phone + "?", new DialogUtils.ClickListener() {
            @Override
            public void clickConfirm() {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + phone);
                intent.setData(data);
                startActivity(intent);
            }
        });
    }

    public void callTechnology(View view) {
        TextView telService = mViewManager.findView(R.id.tel_technology);
        final String phone = telService.getText().toString();
        DialogUtils.showDefaultAlertDialog("是否拨打技术电话：" + phone + "?", new DialogUtils.ClickListener() {
            @Override
            public void clickConfirm() {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + phone);
                intent.setData(data);
                startActivity(intent);
            }
        });
    }
}
