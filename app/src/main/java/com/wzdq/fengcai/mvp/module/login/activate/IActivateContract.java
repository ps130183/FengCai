package com.wzdq.fengcai.mvp.module.login.activate;

import com.wzdq.fengcai.mvp.base.IBaseView;

import io.reactivex.Observable;

/**
 * Created by PengSong on 18/12/4.
 */

public interface IActivateContract {
    interface IActivateView extends IBaseView{
        /**
         * 激活结果  默认是成功
         * @param result
         */
        void activateAccountResult(String result);

    }

    interface IActivateModel{

        /**
         * 激活账号
         * @param account
         * @param phone
         * @param smsCode
         * @param loginPwd
         * @param payPwd
         * @return
         */
        Observable<String> doActivateAccount(String account,String phone,String smsCode,String loginPwd,String payPwd);
    }

    interface IActivatePresenter{

        /**
         * 检测是否可提交激活
         * @param account
         * @param phone
         * @param smsCode
         * @param loginPwd
         * @param payPwd
         * @return
         */
        boolean isSubmitActivate(String account,String phone,String smsCode,String loginPwd,String payPwd);

        /**
         * 激活账号
         * @param account
         * @param phone
         * @param smsCode
         * @param loginPwd
         * @param payPwd
         */
        void doActivateAcount(String account,String phone,String smsCode,String loginPwd,String payPwd);

    }
}
