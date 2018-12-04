package com.wzdq.fengcai.mvp.module.shop.shoppingcart;

import com.wzdq.fengcai.dto.GoodsDto;
import com.wzdq.fengcai.mvp.base.BaseModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by PengSong on 18/12/3.
 */

public class ShoppingCartModelImpl extends BaseModel implements ShoppingCartContract.IShoppingCartModel {

    @Override
    public Observable<Integer> goodsNumberUpdate(GoodsDto goodsDto, int position) {
        return Observable.just(goodsDto.getNumber());
    }

    @Override
    public Observable<List<GoodsDto>> doGetGoodsList() {
        List<GoodsDto> goodsDtos = new ArrayList<>();
        for (int i = 0; i < 10;i++){
            goodsDtos.add(new GoodsDto(100 * (i+1)));
        }
        return Observable.just(goodsDtos);
    }

    @Override
    public Observable<String> doDeleteGoods(GoodsDto goodsDto) {
        return Observable.just("1");
    }
}
