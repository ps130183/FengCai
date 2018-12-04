package com.wzdq.fengcai.dto;

import com.ps.mrcyclerview.SubData;
import com.ps.mrcyclerview.delegate.ItemDelegate;
import com.wzdq.fengcai.R;

/**
 * Created by PengSong on 18/11/21.
 */

public class OrderGoodsDto extends GoodsDto implements ItemDelegate,SubData {

    public OrderGoodsDto(int price) {
        super(price);
    }

    @Override
    public int getItemViewRes() {
        return R.layout.item_order_goods_info;
    }
}
