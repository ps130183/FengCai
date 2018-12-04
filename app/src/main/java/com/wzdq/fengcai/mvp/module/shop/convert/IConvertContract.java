package com.wzdq.fengcai.mvp.module.shop.convert;

import com.wzdq.fengcai.dto.AddressDto;
import com.wzdq.fengcai.dto.GoodsDto;
import com.wzdq.fengcai.dto.OrderGoodsDto;
import com.wzdq.fengcai.mvp.base.IBaseView;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by PengSong on 18/12/3.
 */

public interface IConvertContract {
    interface IConvertView  extends IBaseView{
        void showOrderGoods(List<OrderGoodsDto> orderGoodsDtos);
        void startSelectAddress();
        void showDefaultAddress(AddressDto addressDto);

        void showConversionGoodsResult(boolean isSuccess);
        void showTotalMoney(int totalMoney);
    }

    interface IConvertModel{
        Observable<String> doConversionGoods(List<OrderGoodsDto> orderGoodsDtos);
        Observable<AddressDto> getDefaultAddress();
    }

    interface IConvertPresenter{
        void doSelectAddress();
        void getDefaultAddress();
        void doConvertGoodsToOrder(List<GoodsDto> goodsDtos);
        void doConversionGoods(List<OrderGoodsDto> orderGoodsDtos);
        void doSetTotalMoney(int totalMoney);
    }
}
