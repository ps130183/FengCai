package com.wzdq.fengcai.dto;

import com.ps.mrcyclerview.delegate.ItemDelegate;
import com.wzdq.fengcai.R;

/**
 * Created by PengSong on 18/11/27.
 */

public class AccountChangeDetailDto implements ItemDelegate {
    @Override
    public int getItemViewRes() {
        return R.layout.item_account_change_detail;
    }
}
