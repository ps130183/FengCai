package com.wzdq.fengcai.mvp.module.phone;

import android.text.TextUtils;

import com.blankj.utilcode.util.RegexUtils;
import com.wzdq.fengcai.mvp.base.BasePresenter;
import com.wzdq.fengcai.mvp.base.ModelManager;

import io.reactivex.functions.Consumer;

/**
 * Created by PengSong on 18/12/4.
 */

public class PhonePresenterImpl extends BasePresenter<IPhoneContract.IPhoneView> implements IPhoneContract.IPhonePresenter {

    IPhoneContract.IPhoneModel phoneModel;

    public PhonePresenterImpl(IPhoneContract.IPhoneView mView) {
        super(mView);
        phoneModel = (IPhoneContract.IPhoneModel) getModel(ModelManager.Token.MODEL_PHONE);
    }

    @Override
    public void getSmsCode(String phone) {
        if (TextUtils.isEmpty(phone) || !RegexUtils.isMobileExact(phone)){
            getView().showError("请填写正确格式的手机号！");
            return;
        }
        showLoading();

        phoneModel.getSmsCode(phone)
                .subscribe(newSubscriber(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        getView().requestSmsCodeResult(s);
                    }
                }));
    }
}
