package com.wzdq.fengcai.dto;

import com.ps.mrcyclerview.delegate.ItemDelegate;
import com.wzdq.fengcai.R;

/**
 * Created by PengSong on 18/11/30.
 */

public class ApplyPersonDto implements ItemDelegate {
    @Override
    public int getItemViewRes() {
        return R.layout.item_apply_person;
    }
}
