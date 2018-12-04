package com.wzdq.fengcai.mvp.module.login;

import com.wzdq.fengcai.dto.LoginResultDto;
import com.wzdq.fengcai.mvp.base.BaseModel;

import io.reactivex.Observable;

/**
 * Created by PengSong on 18/11/29.
 */

public class LoginModelImpl extends BaseModel implements ILoginContract.ILoginModel {
    @Override
    public Observable<LoginResultDto> login(String account, String password) {
        return getService().login(account,password).compose(this.<LoginResultDto>applySchedulers());
    }
}
