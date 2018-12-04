package com.wzdq.fengcai.dto;

import com.ps.mrcyclerview.delegate.ItemDelegate;
import com.wzdq.fengcai.R;

/**
 * Created by PengSong on 18/11/28.
 */

public class MessageTypeDto implements ItemDelegate{

    private String typeName;
    private int iconRes;

    public MessageTypeDto(String typeName, int iconRes) {
        this.typeName = typeName;
        this.iconRes = iconRes;
    }

    public int getIconRes() {
        return iconRes;
    }

    public String getTypeName() {
        return typeName;
    }

    @Override
    public int getItemViewRes() {
        return R.layout.item_message_center;
    }
}
