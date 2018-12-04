package com.wzdq.fengcai.module.mine.account;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ps.mrcyclerview.BViewHolder;
import com.ps.mrcyclerview.ItemViewConvert;
import com.ps.mrcyclerview.MRecyclerView;
import com.wzdq.fengcai.R;
import com.wzdq.fengcai.base.BaseActivity;
import com.wzdq.fengcai.dto.AccountChangeDetailDto;

import java.util.ArrayList;
import java.util.List;

/**
 * 金额变化明细
 */
public class AccountChangeDetailActivity extends BaseActivity {

    @Override
    public int getContentViewRes() {
        return R.layout.activity_account_change_detail;
    }

    @Override
    public String getTitleContent() {
        return "账单明细";
    }

    @Override
    public void onFinally(@Nullable Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        MRecyclerView<AccountChangeDetailDto> mRecyclerView = mViewManager.findView(R.id.recyclerView);
        mRecyclerView.addContentLayout(R.layout.item_account_change_detail, new ItemViewConvert<AccountChangeDetailDto>() {
            @Override
            public void convert(@NonNull BViewHolder holder, AccountChangeDetailDto mData, int position, @NonNull List<Object> payloads) {

            }

        }).create();

        List<AccountChangeDetailDto> accountChangeDetailDtos = new ArrayList<>();
        accountChangeDetailDtos.add(new AccountChangeDetailDto());
        accountChangeDetailDtos.add(new AccountChangeDetailDto());
        accountChangeDetailDtos.add(new AccountChangeDetailDto());
        accountChangeDetailDtos.add(new AccountChangeDetailDto());
        accountChangeDetailDtos.add(new AccountChangeDetailDto());
        mRecyclerView.loadDataOfNextPage(accountChangeDetailDtos);
    }
}
