package com.wzdq.fengcai.utils.dialog.city;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.ps.mrcyclerview.BViewHolder;
import com.ps.mrcyclerview.ItemViewConvert;
import com.ps.mrcyclerview.MRecyclerView;
import com.ps.mrcyclerview.click.OnClickItemListener;
import com.wzdq.fengcai.R;
import com.wzdq.fengcai.view.MTextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by PengSong on 18/11/22.
 */

public class CityDialog {

    private Context mContext;
    private View contentView;
    private Dialog mDialog;

    //缓存
    private Map<View, SparseArray<View>> mViewCache;
    private SparseArray<View> mSelectedViewCache;
    private CityBean[] mSelectedCityCache;

    private int curCityLevel = CityBean.PROVINCE;

    public CityDialog(Context mContext) {
        this.mContext = mContext;
        init(mContext);
    }

    private void init(Context context) {
        mViewCache = new HashMap<>();
        mSelectedCityCache = new CityBean[3];
        mSelectedViewCache = new SparseArray<>();
        contentView = LayoutInflater.from(context).inflate(R.layout.dialog_bottom_city, null, false);

        refreshSelectedState();

        final TextView cancel = (TextView) findView(contentView, R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dimiss();
            }
        });

        MRecyclerView<CityBean> mRecyclerView = (MRecyclerView<CityBean>) findView(contentView, R.id.itemCity);
        mRecyclerView.addContentLayout(R.layout.item_city, new ItemViewConvert<CityBean>() {
            @Override
            public void convert(@NonNull BViewHolder holder, CityBean mData, int position, @NonNull List<Object> payloads) {
                MTextView cityName = holder.findView(R.id.cityName);
                cityName.setText(mData.getCityName());
            }
        }).create();

        mRecyclerView.addClickItemListener(new OnClickItemListener() {
            @Override
            public void clickItem(Object mData, int position) {

                CityBean cityBean = (CityBean) mData;

//                LogUtils.d("curLevel = " + curCityLevel + "   selectedCityLevel = " + cityBean.getCityLevel());
                if (cityBean.getCityLevel() != curCityLevel) {
                    return;
                }

                putSelectedCity(cityBean);
                if (curCityLevel < mSelectedCityCache.length - 1) {
                    curCityLevel++;
                    for (int i = curCityLevel; i < mSelectedCityCache.length; i++) {
                        removeCity(i);
                    }
                }
                refreshSelectedState();

                if (selectedFinish()){
                    dimiss();
                    if (onSelectedFinished != null){
                        onSelectedFinished.finish(mSelectedCityCache);
                    }
                }

            }
        });

    }


    /**
     * 选择完毕
     * @return
     */
    private boolean selectedFinish(){
        boolean finished = true;
        for (int i = 0; i < mSelectedCityCache.length; i++){
            if (mSelectedCityCache[i] == null){
                finished = false;
                break;
            }
        }
        return finished;
    }

    /**
     * 添加选中的城市
     *
     * @param cityBean
     */
    private void putSelectedCity(final CityBean cityBean) {
        if (cityBean == null) {
            return;
        }
        mSelectedCityCache[cityBean.getCityLevel()] = cityBean;
    }

    /**
     * 刷新选中状态
     */
    private void refreshSelectedState() {

        for (int i = 0; i < mSelectedCityCache.length; i++) {
            final CityBean cityBean = mSelectedCityCache[i];

            View itemView = mSelectedViewCache.get(i);
            if (itemView == null) {
                itemView = LayoutInflater.from(mContext).inflate(R.layout.item_selected_cities, null, false);
                mSelectedViewCache.put(i, itemView);


                LinearLayout content = (LinearLayout) findView(contentView, R.id.selectedCities);
                content.addView(itemView);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cityBean != null) {
                        curCityLevel = cityBean.getCityLevel();
                        refreshSelectedState();
                    }
                }
            });

            TextView cityName = (TextView) findView(itemView, R.id.selectedCity);
            cityName.setText(cityBean == null ? "请选择" : cityBean.getCityName());

            findView(itemView, R.id.divideLine).setVisibility(curCityLevel == i ? View.VISIBLE : View.INVISIBLE);
        }
    }

    /**
     * 查找view  并缓存
     *
     * @param rootView
     * @param id
     * @return
     */
    private View findView(View rootView, int id) {
        View subView = null;
        if (mViewCache.containsKey(rootView)) {//存在rootView  key
            subView = mViewCache.get(rootView).get(id);
            if (subView == null) {
                subView = rootView.findViewById(id);
                if (subView == null) {
                    throw new Resources.NotFoundException("找不到 id :" + id + "  对应的view");
                }
                mViewCache.get(rootView).put(id, subView);
            }
        } else {//rootView不存在
            SparseArray<View> subViews = new SparseArray<>();
            subView = rootView.findViewById(id);
            if (subView == null) {
                throw new Resources.NotFoundException("找不到 id :" + id + "  对应的view");
            }
            subViews.put(id, subView);
            mViewCache.put(rootView, subViews);
        }
        return subView;
    }


    public void addCityList(List<CityBean> cityBeans) {
        MRecyclerView<CityBean> mRecyclerView = (MRecyclerView<CityBean>) findView(contentView, R.id.itemCity);
        mRecyclerView.clear();
        mRecyclerView.loadDataOfNextPage(cityBeans);
    }


    /**
     * 移除选中的城市
     *
     * @param level
     */
    private void removeCity(int level) {
        mSelectedCityCache[level] = null;
    }

    public void show() {
        if (mDialog == null) {
            mDialog = new Dialog(mContext, R.style.ActionSheetDialogStyle);
            mDialog.setContentView(contentView);
            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow == null) {
                return;
            }
            //设置Dialog从窗体底部弹出
            dialogWindow.setGravity(Gravity.BOTTOM);
            //获得窗体的属性
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = ScreenUtils.getScreenWidth();
            lp.height = ScreenUtils.getScreenHeight() / 3 * 2;
//            lp.y = 20;//设置Dialog距离底部的距离
            //将属性设置给窗体
            dialogWindow.setAttributes(lp);
            mDialog.show();//显示对话框
        } else {
            mDialog.show();
        }


    }

    public void dimiss() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

    public interface OnSelectedFinished{
        void finish(CityBean[] cityBeans);
    }
    private OnSelectedFinished onSelectedFinished;

    public void setOnSelectedFinished(OnSelectedFinished onSelectedFinished) {
        this.onSelectedFinished = onSelectedFinished;
    }
}
