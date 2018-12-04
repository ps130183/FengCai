package com.wzdq.fengcai.module.mine.service;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;

import com.ps.mrcyclerview.BViewHolder;
import com.ps.mrcyclerview.ItemViewConvert;
import com.ps.mrcyclerview.MRecyclerView;
import com.wzdq.fengcai.R;
import com.wzdq.fengcai.base.BaseActivity;
import com.wzdq.fengcai.dto.ApplyPersonDto;

import java.util.ArrayList;
import java.util.List;

/**
 * 股东系统管理  主要管理下级会员 的申请 审核
 */
public class ShareholderManagerActivity extends BaseActivity {

    @Override
    public int getContentViewRes() {
        return R.layout.activity_shareholder_manager;
    }

    @Override
    public String getTitleContent() {
        return "股东系统申请列表";
    }

    @Override
    public void onFinally(@Nullable Bundle savedInstanceState) {
        initRecycler();
    }

    private void initRecycler(){
        MRecyclerView<ApplyPersonDto> mRecyclerView = mViewManager.findView(R.id.recyclerView);
        mRecyclerView.addContentLayout(R.layout.item_apply_person, new ItemViewConvert<ApplyPersonDto>() {
            @Override
            public void convert(@NonNull BViewHolder holder, ApplyPersonDto mData, int position, @NonNull List<Object> payloads) {

            }
        }).create();

        List<ApplyPersonDto> applyPersonDtos = new ArrayList<>();
        applyPersonDtos.add(new ApplyPersonDto());
        applyPersonDtos.add(new ApplyPersonDto());
        applyPersonDtos.add(new ApplyPersonDto());
        applyPersonDtos.add(new ApplyPersonDto());
        applyPersonDtos.add(new ApplyPersonDto());
        mRecyclerView.loadDataOfNextPage(applyPersonDtos);
    }
}
