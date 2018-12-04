package com.wzdq.fengcai.module.mine.account.bank;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;


import com.wzdq.fengcai.R;
import com.wzdq.fengcai.base.BaseActivity;
import com.wzdq.fengcai.dto.BankDto;
import com.wzdq.fengcai.dto.WithDrawAccountDto;
import com.wzdq.fengcai.event.StepEvent;
import com.wzdq.fengcai.view.step.StepContent;
import com.wzdq.fengcai.view.step.StepView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class AddBankCardActivity extends BaseActivity {

    private FrameLayout flContent;
    private List<Fragment> fragments;
    private WithDrawAccountDto mWithDrawAccount;

    private int fromPage = -1;


    @Override
    public int getContentViewRes() {
        return R.layout.activity_add_bank_card;
    }


    @Override
    public String getTitleContent() {
        return "添加银行卡";
    }

    @Override
    public void onFinally(@Nullable Bundle savedInstanceState) {
        fromPage = getIntent().getIntExtra("fromPage",-1);
//        getPresenter().getBankList();
        setOnClickBackLisenter(new OnClickBackLisenter() {
            @Override
            public boolean onClickBack() {
                EventBus.getDefault().post(new StepEvent(-1));
                return false;
            }
        });
        initStep(new ArrayList<BankDto>());
    }

    @Override
    public View.OnClickListener getLeftClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new StepEvent(-1));
            }
        };
    }

    private void initStep(List<BankDto> bankDtos) {
        StepView stepView = mViewManager.findView(R.id.stepView);
        List<StepContent> stepContentList = new ArrayList<>();
        stepContentList.add(new StepContent("1", "输入卡号"));
        stepContentList.add(new StepContent("2", "银行验证信息"));
        stepContentList.add(new StepContent("3", "验证码"));
        stepView.addStepContent(stepContentList);

        flContent = mViewManager.findView(R.id.flContent);
        FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
        fragments = new ArrayList<>();
        fragments.add(AddBankCardOneFragment.newInstance(null));
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("bankList", (ArrayList<? extends Parcelable>) bankDtos);
        fragments.add(AddBankCardTwoFragment.newInstance(bundle));
        fm.replace(R.id.flContent, fragments.get(0));
        fm.commit();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onStepChange(StepEvent event) {
        StepView stepView = mViewManager.findView(R.id.stepView);
        FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
        int position = stepView.getCurStep() + event.getStep();
        if (position < 0) {
            finish();
            return;
        }
        if (stepView.getCurStep() < position) {
            stepView.nextStep();
        } else {
            stepView.preStep();
        }

        switch (position){
            case 0:
            case 1://输入卡号完成
                if (mWithDrawAccount == null){
                    mWithDrawAccount = new WithDrawAccountDto();
                }
                if (event.getWithDrawAccount() != null){
                    mWithDrawAccount.setName(event.getWithDrawAccount().getName());
                    mWithDrawAccount.setWithdrawNumber(event.getWithDrawAccount().getWithdrawNumber());
                }
                fm.replace(R.id.flContent, fragments.get(position)).commit();
                break;
            case 2://银行验证信息 完成
                mWithDrawAccount.setBankId(event.getWithDrawAccount().getBankId());
                mWithDrawAccount.setWithdrawPhone(event.getWithDrawAccount().getWithdrawPhone());

                Bundle bundle = new Bundle();
                bundle.putString("phone",event.getWithDrawAccount().getWithdrawPhone());
                fm.replace(R.id.flContent, AddBankCardThreeFragment.newInstance(bundle)).commit();
                break;
            case 3://完成 最后提交验证码
                mWithDrawAccount.setSmsCode(event.getWithDrawAccount().getSmsCode());
//                getPresenter().saveBankCard(mWithDrawAccount);
                break;
        }

    }

//    @Override
//    public void showBankList(List<BankDto> bankDtos) {
//        initStep(bankDtos);
//    }
//
//    @Override
//    public void saveBankCardSuccess() {
//        if (fromPage == 0){//来源于我的银行卡页面
//            startActivity(MyBankCardListActivity.class);
//        } else {//来源于 提现页面
//            startActivity(WithdrawDepositActivity.class);
//        }
//
//    }
}
