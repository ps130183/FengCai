package com.wzdq.fengcai.mvp.module.withdraw;

import com.wzdq.fengcai.dto.WithDrawAccountDto;
import com.wzdq.fengcai.mvp.base.BaseModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by PengSong on 18/1/25.
 */

public class WithDrawModel extends BaseModel {
    /**
     * 获取账户余额
     * @return
     */
    public Observable<String> getUserAccountBalance(){
//        return getService().getUserAccountBalance(Constant.userLoginInfo.getToken()).compose(this.<UserBalanceDto>applySchedulers());
        return null;
    }

    /**
     * 提现
     * @param withDrawAccountDto
     * @param money
     * @return
     */
    public Observable<String> submitWithDraw(WithDrawAccountDto withDrawAccountDto, String money){
//        return getService().submitWithDraw(Constant.userLoginInfo.getToken(),withDrawAccountDto.getId(),money)
//                .compose(this.<String>applySchedulers());
        return null;
    }

    /**
     * 新增提现账户
     * @param withDrawAccountDto
     * @return
     */
    public Observable<String> createWithDrawAccount(WithDrawAccountDto withDrawAccountDto){
//        return getService().createWithDrawAccount(Constant.userLoginInfo.getToken(),
//                withDrawAccountDto.getName(),withDrawAccountDto.getWithdrawPhone(),
//                withDrawAccountDto.getTypeName(),withDrawAccountDto.getWithdrawNumber())
//                .compose(this.<String>applySchedulers());
        return null;
    }

    /**
     * 删除提现账户
     * @param id
     * @return
     */
    public Observable<String> deleteWithdrawAccount(String id){
//        return getService().deleteWithDrawAccount(Constant.userLoginInfo.getToken(),id,1)
//                .compose(this.<String>applySchedulers());
        return null;
    }

    /**
     * 编辑提现账户
     * @param withDrawAccountDto
     * @return
     */
    public Observable<String> updateWithDrawAccount(WithDrawAccountDto withDrawAccountDto){
//        return getService().updateWithDrawAccount(Constant.userLoginInfo.getToken(),
//                withDrawAccountDto.getId(),
//                withDrawAccountDto.getName(),withDrawAccountDto.getWithdrawPhone(),
//                withDrawAccountDto.getTypeName(),withDrawAccountDto.getWithdrawNumber())
//                .compose(this.<String>applySchedulers());
        return null;
    }

    /**
     * 获取提现账户列表
     * @return
     */
    public Observable<List<WithDrawAccountDto>> getWithDrawAccount(){
//        return getService().getWithDrawAccount(Constant.userLoginInfo.getToken())
//                .compose(this.<List<WithDrawAccountDto>>applySchedulers());
        return null;
    }
}
