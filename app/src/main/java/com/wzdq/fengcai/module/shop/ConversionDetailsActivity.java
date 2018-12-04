package com.wzdq.fengcai.module.shop;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;

import com.ps.mrcyclerview.BViewHolder;
import com.ps.mrcyclerview.ItemViewConvert;
import com.ps.mrcyclerview.MRecyclerView;
import com.wzdq.fengcai.R;
import com.wzdq.fengcai.base.BaseActivity;
import com.wzdq.fengcai.dto.OrderGoodsDto;

import java.util.ArrayList;
import java.util.List;

public class ConversionDetailsActivity extends BaseActivity {

    @Override
    public int getContentViewRes() {
        return R.layout.activity_conversion_details;
    }

    @Override
    public String getTitleContent() {
        return "兑换详情";
    }

    @Override
    public void onFinally(@Nullable Bundle savedInstanceState) {
        initRecycler();
    }

    private void initRecycler(){
        final MRecyclerView<OrderGoodsDto> mRecyclerView = mViewManager.findView(R.id.recyclerView);
        mRecyclerView.addContentLayout(R.layout.item_order_goods_info, new ItemViewConvert<OrderGoodsDto>() {
            @Override
            public void convert(@NonNull BViewHolder holder, OrderGoodsDto mData, int position, @NonNull List<Object> payloads) {
                if (position == mRecyclerView.getDatasSize() - 1){
                   holder.findView(R.id.divideLine).setVisibility(View.GONE);
                } else {
                    holder.findView(R.id.divideLine).setVisibility(View.VISIBLE);
                }
            }
        }).addHeaderLayout(R.layout.header_conversion_details, new ItemViewConvert() {
            @Override
            public void convert(@NonNull BViewHolder holder, Object mData, int position, @NonNull List payloads) {

            }
        }).addFooterLayout(R.layout.footer_conversion_details, new ItemViewConvert() {
            @Override
            public void convert(@NonNull BViewHolder holder, Object mData, int position, @NonNull List payloads) {

            }
        }).create();

        List<OrderGoodsDto> orderGoodsDtos = new ArrayList<>();
        orderGoodsDtos.add(new OrderGoodsDto(100));
        orderGoodsDtos.add(new OrderGoodsDto(100));
        orderGoodsDtos.add(new OrderGoodsDto(100));
        mRecyclerView.loadDataOfNextPage(orderGoodsDtos);
    }
}
