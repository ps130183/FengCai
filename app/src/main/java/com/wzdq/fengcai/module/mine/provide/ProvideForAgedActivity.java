package com.wzdq.fengcai.module.mine.provide;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;

import com.ps.mrcyclerview.BViewHolder;
import com.ps.mrcyclerview.ItemViewConvert;
import com.ps.mrcyclerview.MRecyclerView;
import com.wzdq.fengcai.R;
import com.wzdq.fengcai.base.BaseActivity;
import com.wzdq.fengcai.base.BaseTitleBar;
import com.wzdq.fengcai.dto.ProvideForAgedDetailDto;
import com.wzdq.fengcai.titleBar.SimpleTitleBar;

import java.util.ArrayList;
import java.util.List;

public class ProvideForAgedActivity extends BaseActivity {

    @Override
    public int getContentViewRes() {
        return R.layout.activity_provide_for_aged;
    }

    @Override
    protected void onCreateTitleBar(BaseTitleBar titleBar) {
        SimpleTitleBar simpleTitleBar = (SimpleTitleBar) titleBar;
        simpleTitleBar.setTitleContent("养老系统");
        simpleTitleBar.setRightMenuContent("涨薪详情");
        simpleTitleBar.setRightMenuClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(RaiseSalaryDetailsActivity.class);
            }
        });
    }

    @Override
    public void onFinally(@Nullable Bundle savedInstanceState) {
        initRecycler();
    }

    private void initRecycler(){
        MRecyclerView<ProvideForAgedDetailDto> mRecyclerView = mViewManager.findView(R.id.recyclerView);
        mRecyclerView.addContentLayout(R.layout.item_provide_for_aged_detail, new ItemViewConvert<ProvideForAgedDetailDto>() {
            @Override
            public void convert(@NonNull BViewHolder holder, ProvideForAgedDetailDto mData, int position, @NonNull List<Object> payloads) {

            }

        }).create();

        List<ProvideForAgedDetailDto> provideForAgedDetailDtos = new ArrayList<>();
        provideForAgedDetailDtos.add(new ProvideForAgedDetailDto());
        provideForAgedDetailDtos.add(new ProvideForAgedDetailDto());
        provideForAgedDetailDtos.add(new ProvideForAgedDetailDto());
        provideForAgedDetailDtos.add(new ProvideForAgedDetailDto());
        provideForAgedDetailDtos.add(new ProvideForAgedDetailDto());
        provideForAgedDetailDtos.add(new ProvideForAgedDetailDto());
        provideForAgedDetailDtos.add(new ProvideForAgedDetailDto());
        provideForAgedDetailDtos.add(new ProvideForAgedDetailDto());
        provideForAgedDetailDtos.add(new ProvideForAgedDetailDto());
        provideForAgedDetailDtos.add(new ProvideForAgedDetailDto());
        mRecyclerView.loadDataOfNextPage(provideForAgedDetailDtos);
    }
}
