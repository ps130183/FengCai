package com.wzdq.fengcai.mvp.module.shop;

import com.wzdq.fengcai.dto.GoodsDto;
import com.wzdq.fengcai.mvp.base.BaseModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by PengSong on 18/11/30.
 */

public class ShoppingModel extends BaseModel implements IShoppingContract.IShoppingModel {

    @Override
    public Observable<List<GoodsDto>> getGoodsList(int pageNo) {
        List<GoodsDto> goodsDtos = new ArrayList<>();
        for(int i = (pageNo - 1) * 10; i < pageNo * 10; i++) {
            goodsDtos.add(new GoodsDto(200 * (i + 1)));
        }
        return Observable.just(goodsDtos);
    }

    @Override
    public Observable<List<String>> getBannerUrls() {
        List<String> bannerUrl = new ArrayList<>();
        bannerUrl.add("");
        bannerUrl.add("");
        bannerUrl.add("");
        bannerUrl.add("");
        bannerUrl.add("");
        return Observable.just(bannerUrl);
    }

    @Override
    public Observable<String> addShoppingCard(String goodsId) {
        return Observable.just("1");
    }
}
