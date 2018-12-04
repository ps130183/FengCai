package com.wzdq.fengcai.module.mine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ps.glidelib.GlideImageView;
import com.ps.glidelib.GlideUtils;
import com.ps.mrcyclerview.BViewHolder;
import com.ps.mrcyclerview.ItemViewConvert;
import com.ps.mrcyclerview.MRecyclerView;
import com.ps.mrcyclerview.click.OnClickItemListener;
import com.ruffian.library.widget.RTextView;
import com.wzdq.fengcai.R;
import com.wzdq.fengcai.base.BaseFragment;
import com.wzdq.fengcai.entity.MinePermissionEntity;
import com.wzdq.fengcai.module.mine.account.AccountInfoActivity;
import com.wzdq.fengcai.module.mine.address.AddressManagerActivity;
import com.wzdq.fengcai.module.mine.expand.PersonExpandActivity;
import com.wzdq.fengcai.module.mine.message.MessageCenterActivity;
import com.wzdq.fengcai.module.mine.provide.ProvideForAgedActivity;
import com.wzdq.fengcai.module.mine.service.ServiceCenterActivity;
import com.wzdq.fengcai.module.mine.setting.SettingActivity;
import com.wzdq.fengcai.module.mine.shareholder.ApplyBecomeShareholderOfUserActivity;
import com.wzdq.fengcai.module.mine.userinfo.EditorUserInfoActivity;
import com.wzdq.fengcai.module.shop.ConversionRecordActivity;
import com.wzdq.fengcai.module.shop.ShoppingCartActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PengSong on 18/11/14.
 */

public class MineFragment extends BaseFragment {

    private String[] permissions = {"市场拓展","股东分润","养老补贴","购物车","收货地址","兑换记录","报单管理"};
    private int[] permissionImages = {R.mipmap.icon_user_permission_1,
            R.mipmap.icon_user_permission_2,
            R.mipmap.icon_user_permission_3,
            R.mipmap.icon_user_permission_4,
            R.mipmap.icon_user_permission_5,
            R.mipmap.icon_user_permission_6,
            R.mipmap.icon_user_permission_7,};

    public static MineFragment newInstance(Bundle bundle) {
        MineFragment fragment = new MineFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_mine;
    }

    @Override
    public void onCreateView(@Nullable Bundle savedInstanceState) {
        initRecyclerView();
    }

    private void initRecyclerView(){
        MRecyclerView<MinePermissionEntity> mRecyclerView = mViewManager.findView(R.id.recyclerView);
        mRecyclerView.addContentLayout(R.layout.item_mine_permission, new ItemViewConvert<MinePermissionEntity>() {
            @Override
            public void convert(@NonNull BViewHolder holder, MinePermissionEntity mData, int position, @NonNull List<Object> payloads) {
                holder.setText(R.id.permissionName,mData.getPermissionName());
                GlideImageView imageView = holder.findView(R.id.imageView);
                GlideUtils.loadImageByRes(imageView,mData.getIcon());
            }
        }).addHeaderLayout(R.layout.header_mine, new ItemViewConvert() {
            @Override
            public void convert(@NonNull BViewHolder holder, Object mData, int position, @NonNull List payloads) {

                TextView jjMoney = holder.findView(R.id.jjMoney);
                TextView gwMoney = holder.findView(R.id.gwMoney);
                TextView bdMoney = holder.findView(R.id.bdMoney);
                jjMoney.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("moneyType",0);
                        startActivity(AccountInfoActivity.class,bundle);
                    }
                });

                gwMoney.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("moneyType",1);
                        startActivity(AccountInfoActivity.class,bundle);
                    }
                });

                bdMoney.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("moneyType",2);
                        startActivity(AccountInfoActivity.class,bundle);
                    }
                });

                //设置
                ImageView setting = holder.findView(R.id.setting);
                setting.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(SettingActivity.class);
                    }
                });

                //头像
                GlideImageView userPortrait = holder.findView(R.id.userPortrait);
                userPortrait.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(EditorUserInfoActivity.class);
                    }
                });

                //加入分润网提示
                RTextView addShareholderSystem = holder.findView(R.id.addShareholderSystem);
                addShareholderSystem.setText(Html.fromHtml("您可以加入股东系统啦，<u>立即加入</u>"));

                //消息
                holder.findView(R.id.message).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(MessageCenterActivity.class);
                    }
                });

                //申请成为股东分润
                holder.setClickListener(R.id.addShareholderSystem, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(ApplyBecomeShareholderOfUserActivity.class);
                    }
                });
            }
        }).create();

        mRecyclerView.addClickItemListener(new OnClickItemListener() {
            @Override
            public void clickItem(Object mData, int position) {
                MinePermissionEntity entity = (MinePermissionEntity) mData;
                switch (entity.getPermissionName()){
                    case "兑换记录":
                        startActivity(ConversionRecordActivity.class);
                        break;
                    case "养老补贴":
                        startActivity(ProvideForAgedActivity.class);
                        break;
                    case "购物车":
                        startActivity(ShoppingCartActivity.class);
                        break;
                    case "收货地址":
                        startActivity(AddressManagerActivity.class);
                        break;
                    case "报单管理":
                        startActivity(ServiceCenterActivity.class);
                        break;
                    case "市场拓展":
                        startActivity(PersonExpandActivity.class);
                        break;
                }
            }
        });

        List<MinePermissionEntity> minePermissionEntities = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++){
            minePermissionEntities.add(new MinePermissionEntity(permissions[i],permissionImages[i]));
        }

        mRecyclerView.loadDataOfNextPage(minePermissionEntities);
    }
}
