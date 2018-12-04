package com.wzdq.fengcai.module.shop;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.ps.mrcyclerview.BViewHolder;
import com.ps.mrcyclerview.ItemViewConvert;
import com.ps.mrcyclerview.MRecyclerView;
import com.ps.mrcyclerview.NestedItemViewConvert;
import com.ps.mrcyclerview.NestedRecyclerAdapter;
import com.ps.mrcyclerview.SubData;
import com.ps.mrcyclerview.click.OnClickItemListener;
import com.wzdq.fengcai.R;
import com.wzdq.fengcai.base.BaseActivity;
import com.wzdq.fengcai.dto.ConversionRecordDto;

import java.util.ArrayList;
import java.util.List;

public class ConversionRecordActivity extends BaseActivity {


    @Override
    public int getContentViewRes() {
        return R.layout.activity_conversion_record;
    }

    @Override
    public String getTitleContent() {
        return "兑换记录";
    }

    @Override
    public void onFinally(@Nullable Bundle savedInstanceState) {
        initRecycler();
    }

    private void initRecycler(){
        MRecyclerView<ConversionRecordDto> mRecyclerView = mViewManager.findView(R.id.recyclerView);
        mRecyclerView.addAdapter(new NestedRecyclerAdapter(this))
                .addContentLayout(R.layout.item_conversion_record, new NestedItemViewConvert<ConversionRecordDto>() {
                    @Override
                    public void itemConvert(@NonNull BViewHolder holder, ConversionRecordDto mData, int position, @NonNull List<Object> payloads) {

                    }

                    @Override
                    public int getSubContentView() {
                        return R.id.subContent;
                    }

                    @Override
                    protected View subConvert(Context context,SubData data, int position) {
                        View view = LayoutInflater.from(context).inflate(R.layout.item_order_goods_info,null,false);
                        return view;
                    }

                }).create();

        mRecyclerView.addClickItemListener(new OnClickItemListener() {
            @Override
            public void clickItem(Object mData, int position) {
                startActivity(ConversionDetailsActivity.class);
            }
        });
        List<ConversionRecordDto> conversionRecordDtos = new ArrayList<>();
        conversionRecordDtos.add(new ConversionRecordDto());
        conversionRecordDtos.add(new ConversionRecordDto());
        conversionRecordDtos.add(new ConversionRecordDto());
        mRecyclerView.loadDataOfNextPage(conversionRecordDtos);
    }
}
