package com.wzdq.fengcai.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;


import com.hss01248.dialog.StyledDialog;
import com.ps.mrcyclerview.BViewHolder;
import com.ps.mrcyclerview.ItemViewConvert;
import com.ps.mrcyclerview.MRecyclerView;
import com.ps.mrcyclerview.click.OnClickItemListener;
import com.wzdq.fengcai.R;
import com.wzdq.fengcai.entity.WindowBottomDialogEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PengSong on 18/6/14.
 */
public class WindowBottomDialog {
    private Context mContext;
    private OnClickShareDialog onClickShareDialog;
    private View contentView;
    private Dialog mDialog;

    //自定义 底部弹出框
    private @LayoutRes int bottomLayoutRes;
    private CustomViewConvert customViewConvert;

    public WindowBottomDialog(Context mContext, String bottomName, String... shareDates) {
        this.mContext = mContext;
        init(mContext, bottomName, shareDates);
    }


    private void init(Context context, final String bottomName, String... shareDates) {
        contentView = LayoutInflater.from(context).inflate(R.layout.dialog_bottom_share, null, false);
        List<WindowBottomDialogEntity> shareNames = new ArrayList<>();
        for (String name : shareDates) {
            shareNames.add(new WindowBottomDialogEntity(name));
        }
        MRecyclerView<WindowBottomDialogEntity> shareRecycler = contentView.findViewById(R.id.shareRecycler);
        shareRecycler.addContentLayout(R.layout.item_custom_share, new ItemViewConvert<WindowBottomDialogEntity>() {
            @Override
            public void convert(@NonNull BViewHolder holder, WindowBottomDialogEntity mData, int position, @NonNull List<Object> payloads) {
                holder.setText(R.id.shareDate, mData.getName());
            }
        }).create();

        shareRecycler.loadDataOfNextPage(shareNames);
        shareRecycler.addClickItemListener(new OnClickItemListener() {
            @Override
            public void clickItem(Object mData, int position) {
                WindowBottomDialogEntity entity = (WindowBottomDialogEntity) mData;
                if (onClickShareDialog != null) {
                    onClickShareDialog.clickShareDialog(entity.getName(), position);
                }
            }
        });

        TextView cancel = contentView.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickShareDialog != null) {
                    onClickShareDialog.clickShareDialog(bottomName, -1);
                }
            }
        });
    }


    public WindowBottomDialog(Context mContext, int bottomLayoutRes, CustomViewConvert customViewConvert) {
        this.mContext = mContext;
        this.bottomLayoutRes = bottomLayoutRes;
        initCustomBottom(bottomLayoutRes,customViewConvert);
    }

    private void initCustomBottom(@LayoutRes int bottomLayoutRes,CustomViewConvert customViewConvert){
        contentView = LayoutInflater.from(mContext).inflate(bottomLayoutRes,null,false);
        if (customViewConvert != null){
            customViewConvert.convert(contentView);
        }
    }

    public void show() {
        if (mDialog == null) {
            BottomSheetDialog bsd = (BottomSheetDialog) StyledDialog.buildCustomBottomSheet(contentView).show();
            bsd.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
            mDialog = bsd;
        } else {
            mDialog.show();
        }
    }

    public void show(int width,int height) {
        if (mDialog == null) {
            BottomSheetDialog bsd = (BottomSheetDialog) StyledDialog.buildCustomBottomSheet(contentView).show();
            bsd.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
            mDialog = bsd;
        } else {
            mDialog.show();
        }

        WindowManager.LayoutParams p = mDialog.getWindow().getAttributes(); // 获取对话框当前的参数值
        p.height = height; // 高度设置为屏幕的0.5
        p.width = width; // 宽度设置为屏幕的0.8
        mDialog.getWindow().setAttributes(p);
    }

    public void dimiss() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }


    public interface OnClickShareDialog {
        void clickShareDialog(String itemName, int i);
    }

    public void setOnClickShareDialog(OnClickShareDialog onClickShareDialog) {
        this.onClickShareDialog = onClickShareDialog;
    }

    public interface CustomViewConvert{
        void convert(View contentView);
    }

    public void setCustomViewConvert(CustomViewConvert customViewConvert) {
        this.customViewConvert = customViewConvert;
    }
}
