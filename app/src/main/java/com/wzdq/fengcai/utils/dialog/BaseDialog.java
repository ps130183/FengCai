package com.wzdq.fengcai.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.wzdq.fengcai.Obserable.Observable;
import com.wzdq.fengcai.base.ViewManager;

/**
 * Created by PengSong on 18/11/27.
 */

public abstract class BaseDialog<T> extends Dialog {
    protected ViewManager mViewManager;

    public BaseDialog(@NonNull Context context) {
        super(context);
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        createDialog(context);
    }

    protected BaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    private final void createDialog(Context context){
        mViewManager = new ViewManager(context,setDialogView());
        View dialogView = mViewManager.getContentView();

        createDialogView(dialogView);

        setContentView(dialogView);
        setCanceledOnTouchOutside(isCanceledOnTouchOutside());

        //判断当前的窗口是否可用
        Window dialogWindow = this.getWindow();
        if (dialogWindow == null){
            return;
        }

        dialogWindow.setGravity(setDialogShowLocation());

        //获得窗体的属性 更改dialog的显示大小
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = ScreenUtils.getScreenWidth() - ConvertUtils.dp2px(60);
        resetWindowSize(lp);
//        lp.height = ScreenUtils.getScreenHeight() / 3 * 2;
////            lp.y = 20;//设置Dialog距离底部的距离
//        //将属性设置给窗体
        dialogWindow.setAttributes(lp);
    }

    abstract @LayoutRes int setDialogView();

    abstract void createDialogView(View dialogView);

    abstract boolean isCanceledOnTouchOutside();

    /**
     * 参考Gravity
     * @return
     */
    abstract int setDialogShowLocation();


    public void resetWindowSize(WindowManager.LayoutParams params){

    }

    abstract void detachWindow();
}
