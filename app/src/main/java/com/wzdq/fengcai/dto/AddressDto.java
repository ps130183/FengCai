package com.wzdq.fengcai.dto;

import com.ps.mrcyclerview.delegate.ItemDelegate;
import com.wzdq.fengcai.R;

/**
 * Created by PengSong on 18/11/22.
 */

public class AddressDto implements ItemDelegate {
    @Override
    public int getItemViewRes() {
        return R.layout.item_address_info;
    }
}
