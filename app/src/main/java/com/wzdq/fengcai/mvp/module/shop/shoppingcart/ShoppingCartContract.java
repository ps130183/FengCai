package com.wzdq.fengcai.mvp.module.shop.shoppingcart;

import com.wzdq.fengcai.dto.GoodsDto;
import com.wzdq.fengcai.mvp.base.IBaseView;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by PengSong on 18/12/3.
 */

public interface ShoppingCartContract {
    interface IShoppingCartView extends IBaseView{
        void goodsNumberUpdateResult(boolean isSuccess);
        void showGoodsList(List<GoodsDto> goodsDtos);
        void startGoodsOrderPage();
        void computeGoodsTotalMoneyResult(int totalMoney,boolean isConvert);

        void deleteGoods(GoodsDto goodsDto);
    }

    interface IShoppingCartModel{
        Observable<Integer> goodsNumberUpdate(GoodsDto goodsDto, int position);
        Observable<List<GoodsDto>> doGetGoodsList();

        Observable<String> doDeleteGoods(GoodsDto goodsDto);
    }

    interface IShoppingCartPresenter {
        void goodsNumberUpdate(GoodsDto goodsDto,int position);
        void doGetGoodsList();
        void doConvert();
        void doComputeGoodsTotalMoney(List<GoodsDto> goodsDtos,int convertNumberLimit);

        void doDeleteGoods(GoodsDto goodsDto);
    }
}
