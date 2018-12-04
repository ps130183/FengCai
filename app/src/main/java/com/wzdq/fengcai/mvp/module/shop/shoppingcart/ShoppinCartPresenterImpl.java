package com.wzdq.fengcai.mvp.module.shop.shoppingcart;

import com.wzdq.fengcai.dto.GoodsDto;
import com.wzdq.fengcai.mvp.base.BasePresenter;
import com.wzdq.fengcai.mvp.base.ModelManager;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by PengSong on 18/12/3.
 */

public class ShoppinCartPresenterImpl extends BasePresenter<ShoppingCartContract.IShoppingCartView> implements ShoppingCartContract.IShoppingCartPresenter {

    private ShoppingCartContract.IShoppingCartModel shoppingCartModel;

    public ShoppinCartPresenterImpl(ShoppingCartContract.IShoppingCartView mView) {
        super(mView);
        shoppingCartModel = (ShoppingCartContract.IShoppingCartModel) getModel(ModelManager.Token.MODEL_SHOPPING_CART);
    }

    @Override
    public void goodsNumberUpdate(GoodsDto goodsDto, int position) {
        shoppingCartModel.goodsNumberUpdate(goodsDto,position)
                .subscribe(newSubscriber(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        getView().goodsNumberUpdateResult(true);
                    }
                }));
    }

    @Override
    public void doGetGoodsList() {
        shoppingCartModel.doGetGoodsList()
                .subscribe(newSubscriber(new Consumer<List<GoodsDto>>() {
                    @Override
                    public void accept(List<GoodsDto> goodsDtos) throws Exception {
                        getView().showGoodsList(goodsDtos);
                    }
                }));
    }

    @Override
    public void doConvert() {
        getView().startGoodsOrderPage();
    }

    @Override
    public void doComputeGoodsTotalMoney(List<GoodsDto> goodsDtos, int convertNumberLimit) {
        int totalMoney = 0;
        for (GoodsDto goodsDto : goodsDtos){
            totalMoney += goodsDto.getNumber() * goodsDto.getPrice();
        }
        boolean isConvert = convertNumberLimit > 0 ? (totalMoney == convertNumberLimit ? true : false) : true;

        getView().computeGoodsTotalMoneyResult(totalMoney,isConvert);
    }

    @Override
    public void doDeleteGoods(final GoodsDto goodsDto) {
        shoppingCartModel.doDeleteGoods(goodsDto)
                .subscribe(newSubscriber(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        getView().deleteGoods(goodsDto);
                    }
                }));
    }
}
