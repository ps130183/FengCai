package com.wzdq.fengcai.mvp.module.shop.convert;

import com.wzdq.fengcai.dto.AddressDto;
import com.wzdq.fengcai.dto.OrderGoodsDto;
import com.wzdq.fengcai.mvp.base.BaseModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by PengSong on 18/12/3.
 */

public class ConvertModelImpl extends BaseModel implements IConvertContract.IConvertModel {
    @Override
    public Observable<String> doConversionGoods(List<OrderGoodsDto> orderGoodsDtos) {
        return Observable.just("兑换成功");
    }

    @Override
    public Observable<AddressDto> getDefaultAddress() {
        return Observable.just(new AddressDto());
    }
}
