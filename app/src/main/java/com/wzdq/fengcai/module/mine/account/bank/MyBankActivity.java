package com.wzdq.fengcai.module.mine.account.bank;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ps.glidelib.GlideImageView;
import com.ps.mrcyclerview.BViewHolder;
import com.ps.mrcyclerview.ItemViewConvert;
import com.ps.mrcyclerview.MRecyclerView;
import com.ps.mrcyclerview.click.OnClickItemListener;
import com.wzdq.fengcai.R;
import com.wzdq.fengcai.base.BaseActivity;
import com.wzdq.fengcai.dto.BankCardDto;
import com.wzdq.fengcai.dto.BankDto;

import java.util.ArrayList;
import java.util.List;

public class MyBankActivity extends BaseActivity {

    @Override
    public int getContentViewRes() {
        return R.layout.activity_my_bank;
    }

    @Override
    public String getTitleContent() {
        return "我的银行卡";
    }

    @Override
    public void onFinally(@Nullable Bundle savedInstanceState) {
        initRecycler();
    }

    private void initRecycler(){
        MRecyclerView<BankCardDto> mRecyclerView = mViewManager.findView(R.id.recyclerView);
        mRecyclerView.addContentLayout(R.layout.item_bank_card, new ItemViewConvert<BankCardDto>() {
            @Override
            public void convert(@NonNull BViewHolder holder, BankCardDto mData, int position, @NonNull List<Object> payloads) {

            }
        }).addFooterLayout(R.layout.footer_my_bank_card, new ItemViewConvert() {
            @Override
            public void convert(@NonNull BViewHolder holder, Object mData, int position, @NonNull List payloads) {
                GlideImageView bgView = holder.findView(R.id.bgView);
                bgView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(AddBankCardActivity.class);
                    }
                });
            }
        }).create();

        mRecyclerView.addClickItemListener(new OnClickItemListener() {
            @Override
            public void clickItem(Object mData, int position) {
                startActivity(BankCardInfoActivity.class);
            }
        });

        List<BankCardDto> bankCardDtos = new ArrayList<>();
        bankCardDtos.add(new BankCardDto());
        bankCardDtos.add(new BankCardDto());
        bankCardDtos.add(new BankCardDto());
        mRecyclerView.loadDataOfNextPage(bankCardDtos);
    }
}
