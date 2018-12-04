package com.wzdq.fengcai.module.shop;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Group;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.ps.glidelib.GlideImageView;
import com.ps.glidelib.GlideUtils;
import com.ps.mrcyclerview.BViewHolder;
import com.ps.mrcyclerview.ItemViewConvert;
import com.ps.mrcyclerview.MRecyclerView;
import com.wzdq.fengcai.R;
import com.wzdq.fengcai.base.BaseActivity;
import com.wzdq.fengcai.dto.AddressDto;
import com.wzdq.fengcai.dto.GoodsDto;
import com.wzdq.fengcai.dto.OrderGoodsDto;
import com.wzdq.fengcai.mvp.module.shop.convert.ConvertPresenterImpl;
import com.wzdq.fengcai.mvp.module.shop.convert.IConvertContract;

import java.util.ArrayList;
import java.util.List;

public class ConfirmConversionActivity extends BaseActivity implements IConvertContract.IConvertView, View.OnClickListener {

    IConvertContract.IConvertPresenter convertPresenter;

    //用户的收货地址
    private Group userAddressGroup;
    private TextView selectAddress;
    private GlideImageView ivLoadtion;
    private TextView userNamePhone;
    private TextView userAddress;

    @Override
    public int getContentViewRes() {
        return R.layout.activity_confirm_conversion;
    }

    @Override
    public String getTitleContent() {
        return "确认兑换";
    }

    @Override
    protected void bindingPresenter() {
        addPresenter(new ConvertPresenterImpl(this));
        convertPresenter = (IConvertContract.IConvertPresenter) getPresenter(ConvertPresenterImpl.class);
    }

    @Override
    public void onFinally(@Nullable Bundle savedInstanceState) {
        initRecycler();
        mViewManager.setClickListener(R.id.btnConvert,this);

        int totalMoney = getIntent().getIntExtra("totalMoney",0);
        convertPresenter.doSetTotalMoney(totalMoney);
    }

    private void initRecycler() {
        final MRecyclerView<OrderGoodsDto> mRecyclerView = mViewManager.findView(R.id.recyclerView);
        mRecyclerView.addContentLayout(R.layout.item_order_goods_info, new ItemViewConvert() {
            @Override
            public void convert(@NonNull BViewHolder holder, Object mData, int position, @NonNull List payloads) {
                GlideImageView goodsImage = holder.findView(R.id.goodsImage);
                ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams) goodsImage.getLayoutParams();
                lp.width = ConvertUtils.dp2px(95);
                lp.height = ConvertUtils.dp2px(95);

                if (position == mRecyclerView.getDatasSize() - 1) {
                    holder.findView(R.id.divideLine).setVisibility(View.GONE);
                } else {
                    holder.findView(R.id.divideLine).setVisibility(View.GONE);
                }

            }
        }).addHeaderLayout(R.layout.header_confirm_conversion, new ItemViewConvert() {
            @Override
            public void convert(@NonNull BViewHolder holder, Object mData, int position, @NonNull List payloads) {
                userAddressGroup = holder.findView(R.id.userAddressGroup);
                ivLoadtion = holder.findView(R.id.ivLocation);
                userNamePhone = holder.findView(R.id.userNamePhone);
                userAddress = holder.findView(R.id.userAddress);
                selectAddress = holder.findView(R.id.selectAddress);

                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //选择收货地址
                        convertPresenter.doSelectAddress();
                    }
                });

                convertPresenter.getDefaultAddress();
            }
        }).addFooterLayout(R.layout.footer_confirm_conversion, new ItemViewConvert() {
            @Override
            public void convert(@NonNull BViewHolder holder, Object mData, int position, @NonNull List payloads) {

            }
        }).create();

        List<GoodsDto> goodsDtos = getIntent().getParcelableArrayListExtra("orderGoods");
        convertPresenter.doConvertGoodsToOrder(goodsDtos);
    }

    @Override
    public void showOrderGoods(List<OrderGoodsDto> orderGoodsDtos) {
        MRecyclerView<OrderGoodsDto> mRecyclerView = mViewManager.findView(R.id.recyclerView);
        mRecyclerView.loadDataOfNextPage(orderGoodsDtos);
    }

    @Override
    public void startSelectAddress() {
        showToast("选择收货地址");
    }

    @Override
    public void showDefaultAddress(AddressDto addressDto) {
        if (addressDto != null) {
            userAddressGroup.setVisibility(View.VISIBLE);
            selectAddress.setVisibility(View.GONE);

            GlideUtils.loadImageByRes(ivLoadtion, R.mipmap.icon_location_gray);
            userNamePhone.setText("宋宝杰   188888888");
            userAddress.setText("北京市东城区左安门内大街左安溪园甲2-9");

        } else {
            userAddressGroup.setVisibility(View.GONE);
            selectAddress.setVisibility(View.VISIBLE);

            GlideUtils.loadImageByRes(ivLoadtion, R.mipmap.icon_location_yellow);
        }
    }

    @Override
    public void showConversionGoodsResult(boolean isSuccess) {
        if (isSuccess) {
            showToast("兑换成功");
        }
    }

    @Override
    public void showTotalMoney(int totalMoney) {
        mViewManager.setText(R.id.totalMoney,"¥ " + totalMoney);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnConvert://去兑换
                MRecyclerView<OrderGoodsDto> mRecyclerView = mViewManager.findView(R.id.recyclerView);
                convertPresenter.doConversionGoods(mRecyclerView.getAllDatas());
                break;
        }
    }
}
