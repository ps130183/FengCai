package com.wzdq.fengcai.module.mine.provide;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;

import com.ps.mrcyclerview.BViewHolder;
import com.ps.mrcyclerview.ItemViewConvert;
import com.ps.mrcyclerview.MRecyclerView;
import com.wzdq.fengcai.R;
import com.wzdq.fengcai.base.BaseActivity;
import com.wzdq.fengcai.dto.RaiseSalaryDetailsDto;

import java.util.ArrayList;
import java.util.List;

public class RaiseSalaryDetailsActivity extends BaseActivity {

    @Override
    public int getContentViewRes() {
        return R.layout.activity_raise_salary_details;
    }

    @Override
    public String getTitleContent() {
        return "涨薪详情";
    }

    @Override
    public void onFinally(@Nullable Bundle savedInstanceState) {
        initRecycler();
    }

    private void initRecycler(){
        MRecyclerView<RaiseSalaryDetailsDto> mRecyclerView = mViewManager.findView(R.id.recyclerView);
        mRecyclerView.addContentLayout(R.layout.item_raise_salary_info, new ItemViewConvert<RaiseSalaryDetailsDto>() {
            @Override
            public void convert(@NonNull BViewHolder holder, RaiseSalaryDetailsDto mData, int position, @NonNull List<Object> payloads) {

            }
        }).create();

        List<RaiseSalaryDetailsDto> raiseSalaryDetailsDtos = new ArrayList<>();
        raiseSalaryDetailsDtos.add(new RaiseSalaryDetailsDto());
        raiseSalaryDetailsDtos.add(new RaiseSalaryDetailsDto());
        raiseSalaryDetailsDtos.add(new RaiseSalaryDetailsDto());
        raiseSalaryDetailsDtos.add(new RaiseSalaryDetailsDto());
        mRecyclerView.loadDataOfNextPage(raiseSalaryDetailsDtos);
    }
}
