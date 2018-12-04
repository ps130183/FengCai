package com.wzdq.fengcai.module.mine.account;

import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.wzdq.fengcai.R;
import com.wzdq.fengcai.base.BaseActivity;
import com.wzdq.fengcai.module.mine.account.bank.MyBankActivity;
import com.wzdq.fengcai.module.mine.account.withdraw.WithdrawDepositActivity;
import com.wzdq.fengcai.view.MTextView;

/**
 * 账户信息 包括奖金币、购物币、报单币等等
 */
public class AccountInfoActivity extends BaseActivity implements View.OnClickListener {

    private int moneyType = -1;

    @Override
    public int getContentViewRes() {
        return R.layout.activity_account_info;
    }

    @Override
    public void onFinally(@Nullable Bundle savedInstanceState) {
        initView();
    }

    private void initView(){
        //银行卡
        MTextView myBank = mViewManager.findView(R.id.myBank);
        TextView noBankCard = mViewManager.findView(R.id.noBankCard);
        myBank.setOnClickListener(this);
        noBankCard.setOnClickListener(this);

        //转账 和 提现 按钮
        Button btnConvertMoney =  mViewManager.findView(R.id.btnConvertMoney);
        Button btnWidthdraw = mViewManager.findView(R.id.btnWithdraw);
        btnConvertMoney.setOnClickListener(this);
        btnWidthdraw.setOnClickListener(this);

        //标题和金额提示
        TextView tvTitle = mViewManager.findView(R.id.simple_tb_title_name);
        TextView tvMoneyHint = mViewManager.findView(R.id.tvMoneyHint);

        //账户明细
        MTextView accountDetails = mViewManager.findView(R.id.accountDetail);
        accountDetails.setOnClickListener(this);
        accountDetails.setText("账单明细");
        ConstraintLayout.LayoutParams accountDetailLp = (ConstraintLayout.LayoutParams) accountDetails.getLayoutParams();

        View bgView2 = mViewManager.findView(R.id.bgView2);
        ConstraintLayout.LayoutParams bgView2Lp = (ConstraintLayout.LayoutParams) bgView2.getLayoutParams();

        moneyType = getIntent().getIntExtra("moneyType",-1);
        String title = "";
        switch (moneyType){
            case 0:
                title = "奖金币";
                tvMoneyHint.setText("奖金余额");

                break;
            case 2:
                title = "报单币";
                tvMoneyHint.setText("余额");
//                accountDetails.setText("报单明细");

                myBank.setVisibility(View.GONE);
                noBankCard.setVisibility(View.GONE);
                bgView2Lp.height = ConvertUtils.dp2px(50);
                accountDetailLp.bottomToTop = R.id.divideLine3;
                mViewManager.findView(R.id.divideLine).setVisibility(View.GONE);

                btnWidthdraw.setVisibility(View.GONE);
                break;
            case 1:
                title = "购物币";
                tvMoneyHint.setText("余额");
                btnConvertMoney.setVisibility(View.GONE);
//                accountDetails.setText("消费明细");
                myBank.setVisibility(View.GONE);
                noBankCard.setVisibility(View.GONE);
                bgView2Lp.height = ConvertUtils.dp2px(50);
                accountDetailLp.bottomToTop = R.id.divideLine3;
                mViewManager.findView(R.id.divideLine).setVisibility(View.GONE);
                btnWidthdraw.setVisibility(View.GONE);
                break;
        }

        tvTitle.setText(title);

    }

    @Override
    public void onClick(View v) {
        Bundle bundle = getIntent().getExtras();
        switch (v.getId()){
            case R.id.myBank://银行卡
            case R.id.noBankCard://银行卡
                startActivity(MyBankActivity.class);
                break;

            case R.id.btnConvertMoney://转币
                startActivity(TransferAccountActivity.class,bundle);
                break;
            case R.id.btnWithdraw://提现
                startActivity(WithdrawDepositActivity.class);
                break;
            case R.id.accountDetail://账单明细、消费明细、报单明细
                startActivity(AccountChangeDetailActivity.class,bundle);
                break;
        }
    }
}
