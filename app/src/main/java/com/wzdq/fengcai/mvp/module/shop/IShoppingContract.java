package com.wzdq.fengcai.mvp.module.shop;

import com.wzdq.fengcai.dto.GoodsDto;
import com.wzdq.fengcai.mvp.base.IBaseView;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by PengSong on 18/11/30.
 */

public interface IShoppingContract {
    interface IShoppingView extends IBaseView {
        void showGoodsList(List<GoodsDto> goodsDtos);
        void showGoodsBanner(List<String> bannerUrl);
        void addShoppingCardSuccess();
        void openShoppingCard();
        void openGoodsDetail();
    }

    interface IShoppingModel{
        Observable<List<GoodsDto>> getGoodsList(int pageNo);
        Observable<List<String>> getBannerUrls();
        Observable<String> addShoppingCard(String goodsId);
    }

    interface IShoppingPresenter{
        void getGoodsList(int pageNo);
        void getGoodsBanner();
        void addShoppingCard(String goodsId);
        void openShoppingCard();
        void openGoodsDetail();
    }
}
