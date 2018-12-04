package com.wzdq.fengcai.mvp.module.phone;

import com.wzdq.fengcai.mvp.base.BaseModel;

import io.reactivex.Observable;

/**
 * Created by PengSong on 18/12/4.
 */

public class PhoneModelImpl extends BaseModel implements IPhoneContract.IPhoneModel {


    @Override
    public Observable<String> getSmsCode(String phone) {
        return Observable.just("111");
    }
}
