package com.wzdq.fengcai.entity;

import com.ps.mrcyclerview.delegate.ItemDelegate;
import com.wzdq.fengcai.R;

/**
 * Created by PengSong on 18/11/15.
 */

public class MinePermissionEntity implements ItemDelegate {

    private String permissionName;
    private int icon;

    public MinePermissionEntity(String permissionName, int icon) {
        this.permissionName = permissionName;
        this.icon = icon;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    @Override
    public int getItemViewRes() {
        return R.layout.item_mine_permission;
    }
}
