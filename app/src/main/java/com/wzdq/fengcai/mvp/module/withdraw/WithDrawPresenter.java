package com.wzdq.fengcai.mvp.module.withdraw;

import com.wzdq.fengcai.dto.WithDrawAccountDto;
import com.wzdq.fengcai.mvp.base.BasePresenter;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by PengSong on 18/1/25.
 */

public class WithDrawPresenter extends BasePresenter<IWithDrawView> {

    private WithDrawModel withDrawModel;

    public WithDrawPresenter(IWithDrawView view) {
        super(view);
        withDrawModel = new WithDrawModel();
    }



    public void getUserBalance() {
//        getMvpModel().getUserAccountBalance()
//                .subscribe(newSubscriber(new Consumer<UserBalanceDto>() {
//                    @Override
//                    public void accept(@NonNull UserBalanceDto userBalanceDto) throws Exception {
////                        getView().showBalance(userBalanceDto);
//                    }
//                }));
    }

    public void submitWithdraw(WithDrawAccountDto withDrawAccountDto, final String money) {
//        getView().showLoading();
//        withDrawModel.submitWithDraw(withDrawAccountDto,money)
//                .subscribe(newSubscriber(new Consumer() {
//                    @Override
//                    public void accept(@NonNull Object o) throws Exception {
//                        getView().withdrawSuccess(money);
//                    }
//                }));

        getView().withdrawSuccess(money);
    }

    public void createWithDrawAccount(WithDrawAccountDto withDrawAccountDto) {
        withDrawModel.createWithDrawAccount(withDrawAccountDto)
                .subscribe(newSubscriber(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        getView().creatOrUpdateSuccess();
                    }
                }));
    }

    public void updateWithDrawAccount(WithDrawAccountDto withDrawAccountDto) {
        withDrawModel.updateWithDrawAccount(withDrawAccountDto)
                .subscribe(newSubscriber(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        getView().creatOrUpdateSuccess();
                    }
                }));
    }

    public void getWithDrawList() {
//        getView().showLoading();
        withDrawModel.getWithDrawAccount()
                .subscribe(newSubscriber(new Consumer<List<WithDrawAccountDto>>() {
                    @Override
                    public void accept(@NonNull List<WithDrawAccountDto> withDrawAccountDtos) throws Exception {
                        getView().showWithDrawList(withDrawAccountDtos);
                    }

                }));
    }

    public void deleteWithdrawAccount(final WithDrawAccountDto withDrawAccountDto) {
        withDrawModel.deleteWithdrawAccount(withDrawAccountDto.getId())
                .subscribe(newSubscriber(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        getView().deleteSuccess(withDrawAccountDto);
                    }
                }));
    }
}
