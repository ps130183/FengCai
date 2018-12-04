package com.wzdq.fengcai.module.mine.userinfo;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.flyco.tablayout.widget.MsgView;
import com.ruffian.library.widget.RTextView;
import com.wzdq.fengcai.R;
import com.wzdq.fengcai.base.BaseActivity;
import com.wzdq.fengcai.utils.dialog.WindowBottomDialog;

public class EditorUserInfoActivity extends BaseActivity implements View.OnClickListener {

    private WindowBottomDialog mSelectPictureDialog;
    private WindowBottomDialog mSelectSexDialog;

    @Override
    public int getContentViewRes() {
        return R.layout.activity_editor_user_info;
    }

    @Override
    public String getTitleContent() {
        return "编辑资料";
    }

    @Override
    public void onFinally(@Nullable Bundle savedInstanceState) {
        initView();
    }

    private void initView(){
        mViewManager.findView(R.id.userIdCard).setOnClickListener(this);
        mViewManager.findView(R.id.userEmail).setOnClickListener(this);
        mViewManager.findView(R.id.userQQ).setOnClickListener(this);
        mViewManager.findView(R.id.userAddress).setOnClickListener(this);
        mViewManager.findView(R.id.userSex).setOnClickListener(this);
        mViewManager.findView(R.id.userPortrait).setOnClickListener(this);

        mSelectPictureDialog = new WindowBottomDialog(mInstance,"取消","拍照上传","从相册选择");
        mSelectPictureDialog.setOnClickShareDialog(new WindowBottomDialog.OnClickShareDialog() {
            @Override
            public void clickShareDialog(String itemName, int i) {
                mSelectPictureDialog.dimiss();
                switch (itemName){
                    case "拍照上传":
                        break;
                    case "从相册选择":
                        break;
                }
            }
        });

        mSelectSexDialog = new WindowBottomDialog(mInstance,"取消","男","女");
        mSelectSexDialog.setOnClickShareDialog(new WindowBottomDialog.OnClickShareDialog() {
            @Override
            public void clickShareDialog(String itemName, int i) {
                mSelectSexDialog.dimiss();
                RTextView userSex = mViewManager.findView(R.id.userSex);
                switch (itemName){
                    case "男":
                        userSex.setText("男");
                        break;
                    case "女":
                        userSex.setText("女");
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()){
            case R.id.userIdCard:
                bundle.putInt("editorType",0);
                startActivity(EditorUserDetailInfoActivity.class,bundle);
                break;
            case R.id.userEmail:
                bundle.putInt("editorType",1);
                startActivity(EditorUserDetailInfoActivity.class,bundle);
                break;
            case R.id.userQQ:
                bundle.putInt("editorType",2);
                startActivity(EditorUserDetailInfoActivity.class,bundle);
                break;
            case R.id.userAddress:
                bundle.putInt("editorType",3);
                startActivity(EditorUserDetailInfoActivity.class,bundle);
                break;
            case R.id.userSex:
                mSelectSexDialog.show();
                break;
            case R.id.userPortrait:
                mSelectPictureDialog.show();
                break;
        }
    }
}
