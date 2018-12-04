package com.wzdq.fengcai.dto;

import com.ps.mrcyclerview.delegate.ItemDelegate;
import com.wzdq.fengcai.R;

/**
 * Created by PengSong on 18/11/22.
 */

public class RaiseSalaryDetailsDto implements ItemDelegate {
    @Override
    public int getItemViewRes() {
        return R.layout.item_raise_salary_info;
    }
}
