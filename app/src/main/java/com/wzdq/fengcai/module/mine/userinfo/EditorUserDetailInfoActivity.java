package com.wzdq.fengcai.module.mine.userinfo;

import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Group;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.wzdq.fengcai.R;
import com.wzdq.fengcai.base.BaseActivity;
import com.wzdq.fengcai.utils.TextUtil;

public class EditorUserDetailInfoActivity extends BaseActivity {

    private boolean etContent1Finish;
    private boolean etContent2Finish;

    private int type = -1;

    private Button btnConfirm;

    @Override
    public int getContentViewRes() {
        return R.layout.activity_editor_user_detail_info;
    }

    @Override
    public void onFinally(@Nullable Bundle savedInstanceState) {
        initView();
    }

    private void  initView(){

        type = getIntent().getIntExtra("editorType",-1);
        TextView simpleTitle = mViewManager.findView(R.id.simple_tb_title_name);
        TextView subTitle1 = mViewManager.findView(R.id.subTitle1);
//        TextView subTitle2 = mViewManager.findView(R.id.subTitle2);

        final EditText etContent1 = mViewManager.findView(R.id.etContent1);
        EditText etContent2 = mViewManager.findView(R.id.etContent2);

//        View divideLine = mViewManager.findView(R.id.divideLine);
        View bgView = mViewManager.findView(R.id.bgView);
        ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams) bgView.getLayoutParams();

        Group group = mViewManager.findView(R.id.group);

        btnConfirm = mViewManager.findView(R.id.confirm);
        if (type > 0){
            group.setVisibility(View.INVISIBLE);
            lp.height = ConvertUtils.dp2px(50);
            ConstraintLayout.LayoutParams subTitle1Lp = (ConstraintLayout.LayoutParams) subTitle1.getLayoutParams();
            subTitle1Lp.topToTop = R.id.bgView;
            subTitle1Lp.bottomToBottom = R.id.bgView;
            subTitle1Lp.bottomToTop = -1;
            subTitle1.setLayoutParams(subTitle1Lp);
            etContent1.setText("");
        } else {
            etContent1Finish = TextUtils.isEmpty(etContent1.getText());
        }

        String title = "";
        switch (type){
            case 0://身份证
                title = "绑定身份证号";
                etContent2.setHint("请输入身份证号");
                etContent1.setHint("请输入姓名");
                break;
            case 1://邮箱
                title = "绑定邮箱";
                subTitle1.setText("邮        箱");
                etContent1.setHint("请输入邮箱");
                break;
            case 2://QQ
                title = "绑定QQ账号";
                subTitle1.setText("QQ账号");
                etContent1.setHint("请输入QQ账号");
                break;
            case 3://居住地址
                title = "编辑居住地址";
                subTitle1.setText("居住地址");
                etContent1.setHint("请输入居住地址");
                break;
        }

        simpleTitle.setText(title);

        etContent1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                switch (type){
                    case 0://身份证
                    case 2://QQ
                    case 3://居住地址
                        etContent1Finish = !TextUtils.isEmpty(s);
                        break;
                    case 1://邮箱
                        etContent1Finish = RegexUtils.isEmail(s);
                        break;
                }

                if (type > 0 && etContent1Finish){
                    btnConfirm.setEnabled(true);
                } else if (type == 0 && etContent2Finish && etContent1Finish){
                    btnConfirm.setEnabled(true);
                } else {
                    btnConfirm.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etContent2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (type == 0){
                    etContent2Finish = !TextUtils.isEmpty(s) && RegexUtils.isIDCard18(s);
                }
                if (type == 0 && etContent2Finish && etContent1Finish){
                    btnConfirm.setEnabled(true);
                } else {
                    btnConfirm.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
