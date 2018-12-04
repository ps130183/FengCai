package com.wzdq.fengcai.module.mine.message;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ps.mrcyclerview.BViewHolder;
import com.ps.mrcyclerview.ItemViewConvert;
import com.ps.mrcyclerview.MRecyclerView;
import com.wzdq.fengcai.R;
import com.wzdq.fengcai.base.BaseActivity;
import com.wzdq.fengcai.dto.MessageDetailDto;

import java.util.ArrayList;
import java.util.List;

public class MessageDetailActivity extends BaseActivity{

    @Override
    public int getContentViewRes() {
        return R.layout.activity_message_detail;
    }

    @Override
    public String getTitleContent() {
        return "账户消息";
    }

    @Override
    public void onFinally(@Nullable Bundle savedInstanceState) {
        initRecycler();
    }

    private void initRecycler(){
        MRecyclerView<MessageDetailDto> mRecyclerView = mViewManager.findView(R.id.recyclerView);
        mRecyclerView.addContentLayout(R.layout.item_message_content, new ItemViewConvert<MessageDetailDto>() {
            @Override
            public void convert(@NonNull BViewHolder holder, MessageDetailDto mData, int position, @NonNull List<Object> payloads) {

            }

        }).create();

        List<MessageDetailDto> messageDetailDtos = new ArrayList<>();
        messageDetailDtos.add(new MessageDetailDto());
        messageDetailDtos.add(new MessageDetailDto());
        messageDetailDtos.add(new MessageDetailDto());
        messageDetailDtos.add(new MessageDetailDto());
        messageDetailDtos.add(new MessageDetailDto());
        mRecyclerView.loadDataOfNextPage(messageDetailDtos);
    }
}
