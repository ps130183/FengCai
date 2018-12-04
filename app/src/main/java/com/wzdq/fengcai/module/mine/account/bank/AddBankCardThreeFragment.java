package com.wzdq.fengcai.module.mine.account.bank;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.wzdq.fengcai.R;
import com.wzdq.fengcai.base.BaseFragment;
import com.wzdq.fengcai.dto.WithDrawAccountDto;
import com.wzdq.fengcai.event.StepEvent;
import com.wzdq.fengcai.view.MTextView;

import org.greenrobot.eventbus.EventBus;

/**
 * 添加银行卡第三步
 * 获取手机号验证码
 * 使用登录的接口 presenter  只是为了使用其 获取短信验证码的接口
 */
public class AddBankCardThreeFragment extends BaseFragment implements View.OnClickListener {

//    private TextView tvSmsCode;
    private int waitTime = 60;
    private boolean isSendCode = false;
    private String phone;

    public AddBankCardThreeFragment() {
        // Required empty public constructor
    }


    public static AddBankCardThreeFragment newInstance(Bundle bundle) {
        AddBankCardThreeFragment fragment = new AddBankCardThreeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_add_bank_card_three;
    }

    @Override
    public void onCreateView(@Nullable Bundle savedInstanceState) {
        phone = getArguments().getString("phone");
        MTextView sendVerificationCode = mViewManager.findView(R.id.sendVerificationCode);
        sendVerificationCode.setOnClickListener(this);
//        getPresenter().getSmsCode(phone);
    }


    public void startSendVerificationCode(View view){
        MTextView textView = (MTextView) view;
        textView.startCountDown();
    }

    /**
     * 完成
     * @param view
     */
    public void nextStep(View view){
        EditText smsCode = mViewManager.findView(R.id.smsCode);
        String code = smsCode.getText().toString().replace(" ","");
        if (TextUtils.isEmpty(code) || code.length() < 6){
            showToast("请填写正确的验证码！");
            return;
        }
        WithDrawAccountDto accountDto = new WithDrawAccountDto();
        accountDto.setSmsCode(code);
        EventBus.getDefault().post(new StepEvent(1,accountDto));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sendVerificationCode:
                startSendVerificationCode(v);
                break;
        }
    }
}
