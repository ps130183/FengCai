package com.wzdq.fengcai.module.mine.account.bank;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.wzdq.fengcai.R;
import com.wzdq.fengcai.base.BaseFragment;
import com.wzdq.fengcai.dto.BankDto;
import com.wzdq.fengcai.dto.WithDrawAccountDto;
import com.wzdq.fengcai.event.StepEvent;

import org.greenrobot.eventbus.EventBus;


/**
 * 添加银行卡第二布
 */
public class AddBankCardTwoFragment extends BaseFragment {


    private BankDto mCheckedBank;

    public AddBankCardTwoFragment() {
        // Required empty public constructor
    }

    public static AddBankCardTwoFragment newInstance(Bundle bundle) {
        AddBankCardTwoFragment fragment = new AddBankCardTwoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_add_bank_card_two;
    }

    @Override
    public void onCreateView(@Nullable Bundle savedInstanceState) {
        initView();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initView() {
        Button nextStep = mViewManager.findView(R.id.nextStep);
        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                EditText bankPhone = mViewManager.findView(R.id.bankPhone);
//                String phone = bankPhone.getText().toString();
//                if (mCheckedBank == null){
//                    showToast("请选择所属银行！");
//                    return;
//                } else if (TextUtils.isEmpty(phone) || !RegexUtils.isMobileExact(phone)){
//                    showToast("请填写正确的手机号！");
//                    return;
//                }
                WithDrawAccountDto withDrawAccountDto = new WithDrawAccountDto();
                withDrawAccountDto.setBankId("");
                withDrawAccountDto.setWithdrawPhone("");
                EventBus.getDefault().post(new StepEvent(1,withDrawAccountDto));
            }
        });


    }

}
