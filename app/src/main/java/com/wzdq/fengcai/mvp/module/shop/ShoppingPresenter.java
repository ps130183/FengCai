package com.wzdq.fengcai.mvp.module.shop;

import com.wzdq.fengcai.dto.GoodsDto;
import com.wzdq.fengcai.mvp.base.BasePresenter;
import com.wzdq.fengcai.mvp.base.ModelManager;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by PengSong on 18/11/30.
 */

public class ShoppingPresenter extends BasePresenter<IShoppingContract.IShoppingView> implements IShoppingContract.IShoppingPresenter {

    private ShoppingModel mShoppingMode;

    public ShoppingPresenter(IShoppingContract.IShoppingView mView) {
        super(mView);
        mShoppingMode = (ShoppingModel) getModel(ModelManager.Token.MODEL_SHOPPING);
    }

    @Override
    public void getGoodsList(int pageNo) {


        mShoppingMode.getGoodsList(pageNo)
                .subscribe(newSubscriber(new Consumer<List<GoodsDto>>() {
                    @Override
                    public void accept(List<GoodsDto> goodsDtos) throws Exception {
                        getView().showGoodsList(goodsDtos);
                    }
                }));
    }

    @Override
    public void getGoodsBanner() {
        mShoppingMode.getBannerUrls()
                .subscribe(newSubscriber(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> strings) throws Exception {
                        getView().showGoodsBanner(strings);
                    }
                }));

    }

    @Override
    public void addShoppingCard(String goodsId) {
        mShoppingMode.addShoppingCard(goodsId)
                .subscribe(newSubscriber(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        getView().addShoppingCardSuccess();
                    }
                }));
    }

    @Override
    public void openShoppingCard() {
        getView().openShoppingCard();
    }

    @Override
    public void openGoodsDetail() {
        getView().openGoodsDetail();
    }
}
