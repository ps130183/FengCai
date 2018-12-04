package com.wzdq.fengcai.dto;

import com.ps.mrcyclerview.SubData;
import com.ps.mrcyclerview.delegate.NestedItemDelegate;
import com.wzdq.fengcai.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PengSong on 18/11/21.
 */

public class ConversionRecordDto implements NestedItemDelegate {

    List<SubData> goodsDtos;

    public ConversionRecordDto() {
        goodsDtos = new ArrayList<>();
        goodsDtos.add(new OrderGoodsDto(100));
        goodsDtos.add(new OrderGoodsDto(100));
        goodsDtos.add(new OrderGoodsDto(100));
    }

    @Override
    public int getItemViewRes() {
        return R.layout.item_conversion_record;
    }

    @Override
    public List<SubData> getSubItemDatas() {
        return goodsDtos;
    }
}
