package com.wzdq.fengcai.entity;

import com.ps.mrcyclerview.delegate.ItemDelegate;
import com.wzdq.fengcai.R;

/**
 * Created by PengSong on 18/11/19.
 */

public class WindowBottomDialogEntity implements ItemDelegate {

    private String name;

    public WindowBottomDialogEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getItemViewRes() {
        return R.layout.item_custom_share;
    }
}
