package com.wzdq.fengcai.dto;

import com.ps.mrcyclerview.delegate.ItemDelegate;
import com.wzdq.fengcai.R;

/**
 * Created by PengSong on 18/12/4.
 */

public class ShareholderAccountDto implements ItemDelegate {
    @Override
    public int getItemViewRes() {
        return R.layout.item_shareholder_system;
    }
}
