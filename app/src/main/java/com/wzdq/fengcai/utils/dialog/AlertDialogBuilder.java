package com.wzdq.fengcai.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.wzdq.fengcai.Obserable.IObserver;
import com.wzdq.fengcai.Obserable.Observable;
import com.wzdq.fengcai.R;

/**
 * Created by PengSong on 18/11/26.
 */

public class AlertDialogBuilder {

    private Context mContext;

    private Dialog mDialog;

    private int contentViewRes;

    private String[] mActions;
    private int[] mActionsColor;

    private IObserver[] mActionsClick;

    public AlertDialogBuilder(Context mContext) {
        this.mContext = mContext;
    }

    public AlertDialogBuilder setContentView(int contentViewRes){
        this.contentViewRes = contentViewRes;
        return this;
    }

    public AlertDialogBuilder setActions(String ... actions){
        mActions = actions;
        return this;
    }

    public AlertDialogBuilder setActionsColor(int ... actionsColor){
        mActionsColor = actionsColor;
        return this;
    }

    public AlertDialogBuilder setmActionsClick(IObserver ... actionsClick){
        this.mActionsClick = actionsClick;
        return this;
    }
    public void create(){

        mDialog = new Dialog(mContext, R.style.AlertDialogStyle);
        mDialog.setCanceledOnTouchOutside(false);

        View rootView = LayoutInflater.from(mContext).inflate(R.layout.dialog_baseview,null,false);

        FrameLayout flContentView = rootView.findViewById(R.id.flContentView);
        if (contentViewRes > 0){
            View contentView = LayoutInflater.from(mContext).inflate(contentViewRes,null,false);
            if (convertContentListener != null){
                convertContentListener.convert(contentView);
            }

            flContentView.addView(contentView);
        }


        LinearLayout llAction = rootView.findViewById(R.id.llAction);
        if (mActions.length > 0 && mActions.length < 3
                && mActions.length == mActionsColor.length
                && mActionsClick.length == mActions.length){
            TextView actionFirst = rootView.findViewById(R.id.firstAction);
            TextView actionSecond = rootView.findViewById(R.id.secondAction);
            TextView actionThree = rootView.findViewById(R.id.threeAction);

            switch (mActions.length){
                case 3:
                    actionThree.setVisibility(View.VISIBLE);
                    actionThree.setTextColor(ContextCompat.getColor(mContext,mActionsColor[2]));
                    actionThree.setText(mActions[2]);
                    setClick(actionThree,2);
                case 2:
                    actionSecond.setVisibility(View.VISIBLE);
                    actionSecond.setTextColor(ContextCompat.getColor(mContext,mActionsColor[1]));
                    actionSecond.setText(mActions[1]);
                    setClick(actionSecond,1);
                case 1:
                    actionFirst.setVisibility(View.VISIBLE);
                    actionFirst.setTextColor(ContextCompat.getColor(mContext,mActionsColor[0]));
                    actionFirst.setText(mActions[0]);
                    setClick(actionFirst,0);
                    break;
            }

            if (mActions.length == 1){
                actionFirst.setBackgroundResource(R.drawable.shape_dialog_action_left_right_bottom_radius4);
            } else if (mActions.length == 2){
                actionFirst.setBackgroundResource(R.drawable.shape_dialog_action_left_bottom_radius4);
                actionSecond.setBackgroundResource(R.drawable.shape_dialog_action_right_bottom_radius4);
            } else if (mActions.length == 3){
                actionFirst.setBackgroundResource(R.drawable.shape_dialog_action_left_bottom_radius4);
                actionSecond.setBackgroundResource(R.drawable.shape_dialog_action_default);
                actionThree.setBackgroundResource(R.drawable.shape_dialog_action_right_bottom_radius4);
            } else {
                llAction.setVisibility(View.GONE);
            }
        } else {
            llAction.setVisibility(View.GONE);
        }


        mDialog.setContentView(rootView);
        Window dialogWindow = mDialog.getWindow();
        if (dialogWindow == null) {
            return;
        }
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.CENTER);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = ScreenUtils.getScreenWidth() - ConvertUtils.dp2px(60);
//        lp.height = ScreenUtils.getScreenHeight() / 3 * 2;
////            lp.y = 20;//设置Dialog距离底部的距离
//        //将属性设置给窗体
        dialogWindow.setAttributes(lp);
        mDialog.show();//显示对话框
    }

    private void setClick(TextView target, final int position){
        if (convertContentListener == null){
            return;
        }
        target.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActionsClick[position].subscribe(AlertDialogBuilder.this);
            }
        });
    }

    public void dimiss(){
        if (mDialog != null){
            mDialog.dismiss();
        }
    }


    public interface ConvertContentListener{
        void convert(View contentView);
    }
    private  ConvertContentListener convertContentListener;

    public AlertDialogBuilder setConvertContentListener(ConvertContentListener convertContentListener) {
        this.convertContentListener = convertContentListener;
        return this;
    }


}
