package com.wzdq.fengcai.module.mine.account;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.blankj.utilcode.util.LogUtils;
import com.wzdq.fengcai.Obserable.IObserver;
import com.wzdq.fengcai.R;
import com.wzdq.fengcai.base.BaseActivity;
import com.wzdq.fengcai.utils.dialog.TransactionDialog;

import org.w3c.dom.Text;

public class TransferAccountActivity extends BaseActivity implements View.OnClickListener {

    private static final int MESSAGE_INPUT_ACCOUNT_FINISHED = 0;
    private static final int LEAD_TIME = 500;

    private boolean isInputMoney;
    private boolean isInputAccount;
    private boolean isInputName;

    private TransactionDialog mTransactionDialog;

    private long preChangeTime = -1;

    private Handler mHandler;

    @Override
    public int getContentViewRes() {
        return R.layout.activity_tranfer_account;
    }

    @Override
    public String getTitleContent() {
        String title = "";
        int type = getIntent().getIntExtra("moneyType",-1);
        if (type == 0){
            title = "转让奖金币";
        } else if (type == 2){
            title = "转让报单币";
        }
        return title;
    }

    @Override
    public void onFinally(@Nullable Bundle savedInstanceState) {
        initView();

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == MESSAGE_INPUT_ACCOUNT_FINISHED){//判断账号是否输入完成
                    long curTime = System.currentTimeMillis();
                    if (curTime - preChangeTime >= LEAD_TIME && !TextUtils.isEmpty((CharSequence) msg.obj)){//输入间隔大于2秒 默认输入完成
                        LogUtils.d("发送请求，查找账号：" + msg.obj + "  对应的用户姓名");
                        EditText etName = mViewManager.findView(R.id.etName);
                        etName.setText("宋宝杰");
                    } else {
                        LogUtils.d("leadTime ====  " + (curTime - preChangeTime));
                    }
                }
            }
        };
    }

    private void initView(){
        mTransactionDialog = new TransactionDialog(mInstance);
        mTransactionDialog.addConfimWithdrawListener(new IObserver() {
            @Override
            public void subscribe(Object... params) {
                showToast("确定");
            }
        });

        EditText etMoney = mViewManager.findView(R.id.etMoney);
        EditText etAccount = mViewManager.findView(R.id.etAccount);
        EditText etName = mViewManager.findView(R.id.etName);

        final Button btnConfirm = mViewManager.findView(R.id.btnConfirm);
        btnConfirm.setOnClickListener(this);

        etMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isInputMoney = !TextUtils.isEmpty(s);
                btnConfirm.setEnabled(isInputMoney&&isInputAccount&&isInputName);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isInputAccount = !TextUtils.isEmpty(s);
                btnConfirm.setEnabled(isInputMoney&&isInputAccount&&isInputName);

                preChangeTime = System.currentTimeMillis();
                Message message = Message.obtain();
                message.what = MESSAGE_INPUT_ACCOUNT_FINISHED;
                message.obj = s;
                mHandler.sendMessageDelayed(message,LEAD_TIME);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isInputName = !TextUtils.isEmpty(s);
                btnConfirm.setEnabled(isInputMoney&&isInputAccount&&isInputName);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnConfirm){//确定
            EditText etMoney = mViewManager.findView(R.id.etMoney);
            mTransactionDialog.showDialog(etMoney.getText().toString());
        }
    }
}
