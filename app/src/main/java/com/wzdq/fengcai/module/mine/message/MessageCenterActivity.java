package com.wzdq.fengcai.module.mine.message;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ps.glidelib.GlideImageView;
import com.ps.glidelib.GlideUtils;
import com.ps.mrcyclerview.BViewHolder;
import com.ps.mrcyclerview.ItemViewConvert;
import com.ps.mrcyclerview.MRecyclerView;
import com.ps.mrcyclerview.click.OnClickItemListener;
import com.wzdq.fengcai.R;
import com.wzdq.fengcai.base.BaseActivity;
import com.wzdq.fengcai.dto.MessageTypeDto;

import java.util.ArrayList;
import java.util.List;

public class MessageCenterActivity extends BaseActivity {

    @Override
    public int getContentViewRes() {
        return R.layout.activity_message_center;
    }

    @Override
    public String getTitleContent() {
        return "消息中心";
    }

    @Override
    public void onFinally(@Nullable Bundle savedInstanceState) {
        initRecycler();

    }

    private void initRecycler(){
        MRecyclerView<MessageTypeDto> mRecyclerView = mViewManager.findView(R.id.recyclerView);
        mRecyclerView.addContentLayout(R.layout.item_message_center, new ItemViewConvert<MessageTypeDto>() {
            @Override
            public void convert(@NonNull BViewHolder holder, MessageTypeDto mData, int position, @NonNull List<Object> payloads) {
                holder.setText(R.id.messageTypeName,mData.getTypeName());
                GlideImageView imageView = holder.findView(R.id.messageTypeIcon);
                GlideUtils.loadImageByRes(imageView,mData.getIconRes());
            }
        }).create();

        mRecyclerView.addClickItemListener(new OnClickItemListener() {
            @Override
            public void clickItem(Object mData, int position) {
                startActivity(MessageDetailActivity.class);
            }
        });

        List<MessageTypeDto> messageTypeDtos = new ArrayList<>();
        messageTypeDtos.add(new MessageTypeDto("账户消息",R.mipmap.icon_message_type_account));
        messageTypeDtos.add(new MessageTypeDto("通知消息",R.mipmap.icon_message_type_notice));
        messageTypeDtos.add(new MessageTypeDto("兑换消息",R.mipmap.icon_message_type_convert));
        mRecyclerView.loadDataOfNextPage(messageTypeDtos);
    }
}
