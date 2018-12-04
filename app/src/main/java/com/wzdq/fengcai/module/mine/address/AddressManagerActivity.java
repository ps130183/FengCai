package com.wzdq.fengcai.module.mine.address;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ps.mrcyclerview.BViewHolder;
import com.ps.mrcyclerview.ItemViewConvert;
import com.ps.mrcyclerview.MRecyclerView;
import com.wzdq.fengcai.Obserable.IObserver;
import com.wzdq.fengcai.R;
import com.wzdq.fengcai.base.BaseActivity;
import com.wzdq.fengcai.dto.AddressDto;
import com.wzdq.fengcai.utils.dialog.AlertDialogBuilder;
import com.wzdq.fengcai.utils.dialog.DialogUtils;

import java.util.ArrayList;
import java.util.List;

public class AddressManagerActivity extends BaseActivity {

    @Override
    public int getContentViewRes() {
        return R.layout.activity_address_manager;
    }

    @Override
    public String getTitleContent() {
        return "收货地址";
    }

    @Override
    public void onFinally(@Nullable Bundle savedInstanceState) {
        initRecycler();
    }

    private void initRecycler(){
        MRecyclerView<AddressDto> mRecyclerView = mViewManager.findView(R.id.recyclerView);
        mRecyclerView.addContentLayout(R.layout.item_address_info, new ItemViewConvert<AddressDto>() {
            @Override
            public void convert(@NonNull BViewHolder holder, AddressDto mData, int position, @NonNull List<Object> payloads) {
                holder.findView(R.id.delete).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogUtils.getInstance().showAlertDialog(mInstance, "是否删除收货地址？", new IObserver() {
                            @Override
                            public void subscribe(Object... object) {
                                AlertDialogBuilder builder = (AlertDialogBuilder) object[0];
                                builder.dimiss();
                                showToast("确定删除");
                            }
                        });
                    }
                });
            }
        }).addFooterLayout(R.layout.footer_address_manager, new ItemViewConvert() {
            @Override
            public void convert(@NonNull BViewHolder holder, Object mData, int position, @NonNull List payloads) {
                holder.findView(R.id.addAddress).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(AddAddressActivity.class);
                    }
                });
            }
        }).create();

        List<AddressDto> addressDtos = new ArrayList<>();
        addressDtos.add(new AddressDto());
        addressDtos.add(new AddressDto());
        addressDtos.add(new AddressDto());
        addressDtos.add(new AddressDto());
        mRecyclerView.loadDataOfNextPage(addressDtos);
    }
}
