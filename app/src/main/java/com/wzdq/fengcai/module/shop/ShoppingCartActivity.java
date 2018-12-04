package com.wzdq.fengcai.module.shop;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.LogUtils;
import com.ps.mrcyclerview.BViewHolder;
import com.ps.mrcyclerview.ItemViewConvert;
import com.ps.mrcyclerview.MRecyclerView;
import com.ps.mrcyclerview.click.OnLongClickItemListener;
import com.wzdq.fengcai.Obserable.IObserver;
import com.wzdq.fengcai.R;
import com.wzdq.fengcai.base.BaseActivity;
import com.wzdq.fengcai.dto.GoodsDto;
import com.wzdq.fengcai.mvp.module.shop.shoppingcart.ShoppinCartPresenterImpl;
import com.wzdq.fengcai.mvp.module.shop.shoppingcart.ShoppingCartContract;
import com.wzdq.fengcai.utils.TextUtil;
import com.wzdq.fengcai.utils.dialog.AlertDialogBuilder;
import com.wzdq.fengcai.utils.dialog.DialogUtils;
import com.wzdq.fengcai.view.AddDecreaseView;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartActivity extends BaseActivity implements View.OnClickListener, ShoppingCartContract.IShoppingCartView {

    private TextView tvTotalMoney;

    private static final  int isConvertGoodsNumberList = 10000;
    private ShoppingCartContract.IShoppingCartPresenter shoppinCartPresenter;
    @Override
    public int getContentViewRes() {
        return R.layout.activity_shopping_cart;
    }

    @Override
    public String getTitleContent() {
        return "购物车";
    }

    @Override
    protected void bindingPresenter() {
        addPresenter(new ShoppinCartPresenterImpl(this));

        shoppinCartPresenter = (ShoppinCartPresenterImpl) getPresenter(ShoppinCartPresenterImpl.class);
    }

    @Override
    public void onFinally(@Nullable Bundle savedInstanceState) {
        initView();
        initRecycler();
    }


    private void initView(){
        Button btnConvert = mViewManager.findView(R.id.convert);
        btnConvert.setOnClickListener(this);
        btnConvert.setEnabled(false);
        tvTotalMoney = mViewManager.findView(R.id.totalMoney);
    }
    private void initRecycler(){
        final MRecyclerView<GoodsDto> mRecyclerView = mViewManager.findView(R.id.recyclerView);
        mRecyclerView.addContentLayout(R.layout.item_goods_info, new ItemViewConvert<GoodsDto>() {
            @Override
            public void convert(@NonNull BViewHolder holder, final GoodsDto mData, final int position, @NonNull List<Object> payloads) {
                TextView priceNew = holder.findView(R.id.price_new);
                AddDecreaseView addDecreaseView = holder.findView(R.id.addDecrease);
                if (payloads.size() == 0){
                    addDecreaseView.setVisibility(View.VISIBLE);
                    addDecreaseView.setValueChangeListener(new AddDecreaseView.OnValueChangeListener() {
                        @Override
                        public void changeValue(int number) {
                            mData.setNumber(number);
                            shoppinCartPresenter.goodsNumberUpdate(mData,position);
                        }
                    });

                    //调整活动价 和 原价的显示位置
                    TextView priceOld = holder.findView(R.id.price_old);
                    ConstraintLayout.LayoutParams lpPriceNew  = (ConstraintLayout.LayoutParams) priceNew.getLayoutParams();
                    ConstraintLayout.LayoutParams lpPriceOld = (ConstraintLayout.LayoutParams) priceOld.getLayoutParams();

                    //给原价 加删除线
                    TextUtil.getInstance().addDeleteLineForTextView(priceOld);

                    //调整原价的显示位置
                    lpPriceOld.leftToRight = R.id.goodsImage;
                    lpPriceOld.bottomToBottom = R.id.goodsImage;
                    lpPriceOld.baselineToBaseline = -1;
                    lpPriceOld.setMargins(ConvertUtils.dp2px(10),0,0,0);

                    //调整活动价的 显示位置
                    lpPriceNew.bottomToTop = R.id.price_old;
                    lpPriceNew.setMargins(ConvertUtils.dp2px(10),0,0,0);

                    priceNew.setText("¥ " + mData.getPrice());
                    addDecreaseView.setNumber(mData.getNumber());
                    LogUtils.d("leftToRight :   ----- " + lpPriceNew.leftToRight);
                }

            }
        }).create();

        mRecyclerView.addLongClickItemListener(new OnLongClickItemListener() {
            @Override
            public void longClickItem(final Object mData, int position) {
                DialogUtils.getInstance().showAlertDialog(mInstance, "是否删除该商品？", new IObserver() {
                    @Override
                    public void subscribe(Object... params) {
                        AlertDialogBuilder builder = (AlertDialogBuilder) params[0];
                        builder.dimiss();

                        shoppinCartPresenter.doDeleteGoods((GoodsDto) mData);
                    }
                });

            }
        });


        shoppinCartPresenter.doGetGoodsList();
    }

    /**
     * 获取购物车商品
     */
    private List<GoodsDto> getShoppinCartGoods(){
        MRecyclerView<GoodsDto> mRecyclerView = mViewManager.findView(R.id.recyclerView);
        return mRecyclerView.getAllDatas();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.convert:
                shoppinCartPresenter.doConvert();
                break;
        }
    }

    @Override
    public void goodsNumberUpdateResult(boolean isSuccess) {
        if (isSuccess){
            shoppinCartPresenter.doComputeGoodsTotalMoney(getShoppinCartGoods(),isConvertGoodsNumberList);
        }
    }

    @Override
    public void showGoodsList(List<GoodsDto> goodsDtos) {
        MRecyclerView<GoodsDto> mRecyclerView = mViewManager.findView(R.id.recyclerView);
        mRecyclerView.loadDataOfNextPage(goodsDtos);
        shoppinCartPresenter.doComputeGoodsTotalMoney(getShoppinCartGoods(),isConvertGoodsNumberList);
    }

    @Override
    public void startGoodsOrderPage() {
        Bundle bundle = new Bundle();
        ArrayList<GoodsDto> goodsDtos = (ArrayList<GoodsDto>) getShoppinCartGoods();
        bundle.putParcelableArrayList("orderGoods",goodsDtos);
        bundle.putInt("totalMoney",isConvertGoodsNumberList);
        startActivity(ConfirmConversionActivity.class,bundle);
    }

    @Override
    public void computeGoodsTotalMoneyResult(int totalMoney, boolean isConvert) {
        tvTotalMoney.setText("¥ " + totalMoney);
        //检测选中的商品总价值 是否可以兑换条件
        Button btnConvert = mViewManager.findView(R.id.convert);
        btnConvert.setEnabled(isConvert);
    }

    @Override
    public void deleteGoods(GoodsDto goodsDto) {
        MRecyclerView<GoodsDto> mRecyclerView = mViewManager.findView(R.id.recyclerView);
        mRecyclerView.delete(goodsDto);
        shoppinCartPresenter.doComputeGoodsTotalMoney(getShoppinCartGoods(),isConvertGoodsNumberList);
    }
}
