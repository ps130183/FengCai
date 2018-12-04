package com.wzdq.fengcai.module.mine.service;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;

import com.wzdq.fengcai.R;
import com.wzdq.fengcai.base.BaseActivity;

/**
 * 服务中心   也叫报单中心
 */
public class ServiceCenterActivity extends BaseActivity implements View.OnClickListener {

    @Override
    public int getContentViewRes() {
        return R.layout.activity_service_center;
    }

    @Override
    public String getTitleContent() {
        return "报单中心";
    }

    @Override
    public void onFinally(@Nullable Bundle savedInstanceState) {
        initView();
    }

    private void initView(){
        mViewManager.setClickListener(R.id.memberSystem,this);
        mViewManager.setClickListener(R.id.personExpand,this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.memberSystem:
                startActivity(MemberSystemActivity.class);
                break;
            case R.id.personExpand://股东分润系统申请
                startActivity(ShareholderManagerActivity.class);
                break;
        }
    }
}
