package com.wzdq.fengcai.mvp.module.login;

import com.wzdq.fengcai.dto.LoginResultDto;
import com.wzdq.fengcai.mvp.base.BaseModel;
import com.wzdq.fengcai.mvp.base.BasePresenter;
import com.wzdq.fengcai.mvp.base.IBaseView;

import io.reactivex.Observable;

/**
 * Created by PengSong on 18/11/29.
 */

public interface ILoginContract {
    interface ILoginView extends IBaseView{
        void loginSuccess(int pageType);
        void startForgetPassword();
        void startActivateAccount();

        void showInputResult(int inputType,boolean isFinished);
    }

    interface ILoginModel {
        Observable<LoginResultDto> login(String account, String password);
    }

    interface ILoginPresenter{
        void doLogin(String account,String password);
        void doForgetPassword();
        void doActivateAccount();

        void checkInputFinish(String content,int inputType);
    }
}
