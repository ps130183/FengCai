package com.wzdq.fengcai.module.mine.shareholder;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ps.mrcyclerview.BViewHolder;
import com.ps.mrcyclerview.ItemViewConvert;
import com.ps.mrcyclerview.MRecyclerView;
import com.wzdq.fengcai.R;
import com.wzdq.fengcai.base.BaseActivity;
import com.wzdq.fengcai.dto.ShareholderAccountDto;

import java.util.ArrayList;
import java.util.List;

public class ShareholderSystemActivity extends BaseActivity {


    @Override
    public int getContentViewRes() {
        return R.layout.activity_shareholder_system;
    }

    @Override
    public String getTitleContent() {
        return "股东分润系统";
    }

    @Override
    public void onFinally(@Nullable Bundle savedInstanceState) {

        initRecycler();
    }

    private void initRecycler(){
        MRecyclerView<ShareholderAccountDto> mRecyclerView = mViewManager.findView(R.id.recyclerView);
        mRecyclerView.addContentLayout(R.layout.item_shareholder_system, new ItemViewConvert<ShareholderAccountDto>() {
            @Override
            public void convert(@NonNull BViewHolder holder, ShareholderAccountDto mData, int position, @NonNull List<Object> payloads) {

            }
        }).addHeaderLayout(R.layout.header_shareholder_system, new ItemViewConvert() {
            @Override
            public void convert(@NonNull BViewHolder holder, Object mData, int position, @NonNull List payloads) {

            }
        }).create();

        List<ShareholderAccountDto> shareholderAccountDtos = new ArrayList<>();
        shareholderAccountDtos.add(new ShareholderAccountDto());
        shareholderAccountDtos.add(new ShareholderAccountDto());
        shareholderAccountDtos.add(new ShareholderAccountDto());
        mRecyclerView.loadDataOfNextPage(shareholderAccountDtos);
    }
}
