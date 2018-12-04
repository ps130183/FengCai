package com.wzdq.fengcai.utils.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.wzdq.fengcai.Obserable.IObserver;
import com.wzdq.fengcai.Obserable.Observable;
import com.wzdq.fengcai.R;

/**
 * Created by PengSong on 18/11/27.
 */

public class TransactionDialog extends BaseDialog implements View.OnClickListener {

    private String withdrawMoney;
    private Observable mObservable;

    public TransactionDialog(Context context) {
        super(context, R.style.AlertDialogStyle);
        mObservable = new Observable();
    }

    @Override
    int setDialogView() {
        return R.layout.dialog_transaction;
    }

    @Override
    void createDialogView(View dialogView) {
        mViewManager.findView(R.id.ivClose).setOnClickListener(this);
        mViewManager.findView(R.id.btnConfirm).setOnClickListener(this);
        mViewManager.findView(R.id.transPassword).requestFocus();
    }

    @Override
    boolean isCanceledOnTouchOutside() {
        return false;
    }

    @Override
    int setDialogShowLocation() {
        return Gravity.CENTER;
    }

    @Override
    void detachWindow() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivClose://关闭
                dismiss();
                break;
            case R.id.btnConfirm://确定按钮
                EditText transPassword = mViewManager.findView(R.id.transPassword);
                mObservable.dispatchObserver(withdrawMoney,transPassword.getText().toString());
                dismiss();
                break;
        }
    }

    public void showDialog(String money){
        this.withdrawMoney = money;
        TextView tvWithdrawMoney = mViewManager.findView(R.id.withdrawMoney);
        tvWithdrawMoney.setText(money);
        show();
    }

    public void addConfimWithdrawListener(IObserver confirmLisenter){
        mObservable.registerObserver(confirmLisenter);
    }


}
