package com.wzdq.fengcai.mvp.module.shop.convert;

import com.wzdq.fengcai.dto.AddressDto;
import com.wzdq.fengcai.dto.GoodsDto;
import com.wzdq.fengcai.dto.OrderGoodsDto;
import com.wzdq.fengcai.mvp.base.BasePresenter;
import com.wzdq.fengcai.mvp.base.IBaseView;
import com.wzdq.fengcai.mvp.base.ModelManager;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by PengSong on 18/12/3.
 */

public class ConvertPresenterImpl extends BasePresenter<IConvertContract.IConvertView> implements IConvertContract.IConvertPresenter {

    IConvertContract.IConvertModel convertModel;

    public ConvertPresenterImpl(IConvertContract.IConvertView mView) {
        super(mView);
        convertModel = (IConvertContract.IConvertModel) getModel(ModelManager.Token.MODEL_CONFIRM_CONVERSION_GOODS);
    }

    @Override
    public void doSelectAddress() {
        getView().startSelectAddress();
    }

    @Override
    public void getDefaultAddress() {
        convertModel.getDefaultAddress()
                .subscribe(newSubscriber(new Consumer<AddressDto>() {
                    @Override
                    public void accept(AddressDto addressDto) throws Exception {
                        getView().showDefaultAddress(addressDto);
                    }
                }));
    }

    @Override
    public void doConvertGoodsToOrder(List<GoodsDto> goodsDtos) {
        List<OrderGoodsDto> orderGoodsDtos = new ArrayList<>();
        for (GoodsDto goodsDto : goodsDtos){
            OrderGoodsDto orderGoodsDto = new OrderGoodsDto(goodsDto.getPrice());
            orderGoodsDto.setNumber(goodsDto.getNumber());
            orderGoodsDtos.add(orderGoodsDto);
        }
        getView().showOrderGoods(orderGoodsDtos);
    }

    @Override
    public void doConversionGoods(List<OrderGoodsDto> orderGoodsDtos) {
        convertModel.doConversionGoods(orderGoodsDtos)
                .subscribe(newSubscriber(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        getView().showConversionGoodsResult(true);
                    }
                }));
    }

    @Override
    public void doSetTotalMoney(int totalMoney) {
        getView().showTotalMoney(totalMoney);
    }
}
