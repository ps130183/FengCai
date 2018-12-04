package com.wzdq.fengcai.module.mine.account.withdraw;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ps.glidelib.GlideImageView;
import com.ps.glidelib.GlideUtils;
import com.ps.mrcyclerview.BViewHolder;
import com.ps.mrcyclerview.ItemViewConvert;
import com.ps.mrcyclerview.MRecyclerView;
import com.wzdq.fengcai.Obserable.IObserver;
import com.wzdq.fengcai.R;
import com.wzdq.fengcai.base.BaseActivity;
import com.wzdq.fengcai.dto.WithDrawAccountDto;
import com.wzdq.fengcai.module.mine.account.bank.AddBankCardActivity;
import com.wzdq.fengcai.mvp.module.withdraw.IWithDrawView;
import com.wzdq.fengcai.utils.dialog.TransactionDialog;
import com.wzdq.fengcai.utils.dialog.WindowBottomDialog;
import com.wzdq.fengcai.utils.inputfilter.InputFilterUtils;

import java.util.List;


public class WithdrawDepositActivity extends BaseActivity implements IWithDrawView, View.OnClickListener {

//    private EditText withdrawMoney;

    private WindowBottomDialog mSelectBankDialog;

    private MRecyclerView<WithDrawAccountDto> mBankRecycler;
//    private TextView balance;
    private WithDrawAccountDto mCheckedAccount;

    private TransactionDialog mTransactionDialog;

    private String userBalance;
    @Override
    public int getContentViewRes() {
        return R.layout.activity_withdraw_deposit;
    }

    @Override
    public String getTitleContent() {
        return "提现";
    }


    @Override
    public void onFinally(@Nullable Bundle savedInstanceState) {
        initView();
        initSelectBankDialog();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    private void initView(){
        final Button btnWithdraw = mViewManager.findView(R.id.btnWithdraw);
        btnWithdraw.setOnClickListener(this);
        EditText etWithdrawMoney = mViewManager.findView(R.id.etWithdrawMoney);
        etWithdrawMoney.setFilters(InputFilterUtils.filters2);
        etWithdrawMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnWithdraw.setEnabled(!TextUtils.isEmpty(s));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mTransactionDialog = new TransactionDialog(mInstance);
        mTransactionDialog.addConfimWithdrawListener(new IObserver() {
            @Override
            public void subscribe(Object... params) {
                showToast("提现金额：" + params[0] + "\n 提现密码：" + params[1]);
            }
        });
    }

    private void initSelectBankDialog(){
        mSelectBankDialog = new WindowBottomDialog(mInstance, R.layout.dialog_bottom_select_bank_card, new WindowBottomDialog.CustomViewConvert() {
            @Override
            public void convert(View contentView) {

                ImageView close = contentView.findViewById(R.id.ivClose);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mSelectBankDialog.dimiss();
                    }
                });


                mBankRecycler = contentView.findViewById(R.id.bankRecycler);
                mBankRecycler.addContentLayout(R.layout.item_select_bank_card, new ItemViewConvert<WithDrawAccountDto>() {
                    @Override
                    public void convert(@NonNull BViewHolder holder, final WithDrawAccountDto mData, final int position, @NonNull List<Object> payloads) {
                        if (payloads.size() == 0){
                            boolean noBank = mData.getTypeName() == null;
                            holder.setText(R.id.bankName,noBank ? mData.getBankName() : mData.getTypeName());
                            holder.setText(R.id.bankTailNumber,"尾号"+mData.getWithdrawNumber().substring(mData.getWithdrawNumber().length()-4,mData.getWithdrawNumber().length()));
                            GlideImageView bankIcon = holder.findView(R.id.bank_icon);
                            GlideUtils.loadImageOnPregress(bankIcon,mData.getBankLogo(),null);
                        }
                        CheckBox mCheckBox = holder.findView(R.id.checkbox);
                        mCheckBox.setVisibility(mData.isChecked() ? View.VISIBLE : View.GONE);
                        mCheckBox.setChecked(mData.isChecked());


                        holder.findView(R.id.rlMainContent).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                WithDrawAccountDto mAccount = (WithDrawAccountDto) mData;
                                int prePosition = -1;
                                List<WithDrawAccountDto> withDrawAccountDtos = mBankRecycler.getAllDatas();
                                for (int i = 0; i < withDrawAccountDtos.size(); i++) {
                                    WithDrawAccountDto withDrawAccountDto = withDrawAccountDtos.get(i);
                                    if (withDrawAccountDto.isChecked()) {
                                        withDrawAccountDto.setChecked(false);
                                        prePosition = i;
                                        break;
                                    }
                                }
                                mAccount.setChecked(true);
                                mBankRecycler.update(mAccount, position,"1");
                                if (prePosition >= 0) {
                                    mBankRecycler.update(withDrawAccountDtos.get(prePosition), prePosition,"1");
                                }

//                                mViewManager.findView(R.id.unCheckedAccount).setVisibility(View.GONE);
//                                mViewManager.findView(R.id.checkedAccount).setVisibility(View.VISIBLE);
                                GlideImageView bankIcon = mViewManager.findView(R.id.bank_icon);
                                GlideUtils.loadImageOnPregress(bankIcon, mAccount.getBankLogo(), null);
                                mViewManager.setText(R.id.bankName, mAccount.getBankName());
                                mViewManager.setText(R.id.bankTailNumber, "尾号"+mAccount.getWithdrawNumber().substring(mAccount.getWithdrawNumber().length()-4,mAccount.getWithdrawNumber().length()));
                                mCheckedAccount = mAccount;
                                mSelectBankDialog.dimiss();
                            }
                        });


                    }

                }).addFooterLayout(R.layout.footer_select_bank, new ItemViewConvert() {
                    @Override
                    public void convert(@NonNull BViewHolder holder, Object mData, int position, @NonNull List payloads) {
                        LinearLayout addBankCard = holder.findView(R.id.addBankCard);
                        addBankCard.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(AddBankCardActivity.class);
                            }
                        });
                    }
                }).create();
            }
        });

        //选择银行卡 弹出框 显示
//        FrameLayout selectBank = mViewManager.findView(R.id.selectBank);
//        selectBank.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mSelectBankDialog.show();
//            }
//        });
    }


//    @Override
//    public void showBalance(UserBalanceDto userBalanceDto) {
//        userBalance = userBalanceDto.getBalance();
//        balance.setText(userBalance);
//    }

    @Override
    public void withdrawSuccess(String money) {
        Bundle bundle = new Bundle();
        bundle.putString("withdrawMoney",money);
        finish();
        startActivity(WithdrawHintActivity.class,bundle);
    }

    @Override
    public void creatOrUpdateSuccess() {

    }

    @Override
    public void showWithDrawList(List<WithDrawAccountDto> withDrawAccountDtos) {
        mBankRecycler.clear();
        if (mCheckedAccount != null){
            for (WithDrawAccountDto accountDto : withDrawAccountDtos){
                if (accountDto.getId().equals(mCheckedAccount.getId())){
                    accountDto.setChecked(true);
                }
            }
        }
        mBankRecycler.loadDataOfNextPage(withDrawAccountDtos);
    }

    @Override
    public void deleteSuccess(WithDrawAccountDto withDrawAccountDto) {
        mBankRecycler.delete(withDrawAccountDto);
    }

    /**
     * 提现所有余额
     * @param view
     */
    public void withdrawAllMoney(View view) {
//        EditText withdrawMoney = mViewManager.findView(R.id.withdraw_money);
//        withdrawMoney.setText(userBalance);
//        withdrawMoney.setSelection(userBalance.length());
    }

    /**
     * 提交提现
     * @param view
     */
    public void submitWithdraw(View view) {
//        EditText withdrawMoney = mViewManager.findView(R.id.withdraw_money);
//        String money = withdrawMoney.getText().toString();
//        if (mCheckedAccount == null){
//            showToast("请选择要提现的银行卡！");
//            return;
//        }
//        if (TextUtils.isEmpty(money)){
//            showToast("请填写提现金额");
//            return;
//        }
//
//        getPresenter().submitWithdraw(mCheckedAccount,money);
    }

    /**
     * 选择银行卡
     * @param view
     */
    public void selectBankCard(View view) {
        if (mSelectBankDialog != null){
            mSelectBankDialog.show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnWithdraw:
                EditText etWithdrawMoney = mViewManager.findView(R.id.etWithdrawMoney);
                mTransactionDialog.showDialog(etWithdrawMoney.getText().toString());
                break;
        }
    }
}
