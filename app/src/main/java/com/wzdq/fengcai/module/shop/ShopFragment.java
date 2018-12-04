package com.wzdq.fengcai.module.shop;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.ps.glidelib.GlideImageView;
import com.ps.glidelib.GlideUtils;
import com.ps.mrcyclerview.BViewHolder;
import com.ps.mrcyclerview.ItemViewConvert;
import com.ps.mrcyclerview.MRecyclerView;
import com.ps.mrcyclerview.click.OnClickItemListener;
import com.ps.mrcyclerview.utils.RefreshUtils;
import com.wzdq.fengcai.Obserable.IObserver;
import com.wzdq.fengcai.R;
import com.wzdq.fengcai.base.BaseFragment;
import com.wzdq.fengcai.dto.GoodsDto;
import com.wzdq.fengcai.event.ChangeStatusBarEvent;
import com.wzdq.fengcai.mvp.module.shop.IShoppingContract;
import com.wzdq.fengcai.mvp.module.shop.ShoppingPresenter;
import com.wzdq.fengcai.utils.BannerImageLoader;
import com.wzdq.fengcai.utils.TextUtil;
import com.wzdq.fengcai.utils.dialog.AlertDialogBuilder;
import com.wzdq.fengcai.utils.dialog.DialogUtils;
import com.wzdq.fengcai.utils.statusBar.StatusManager;
import com.wzdq.fengcai.view.FloatingShoppingCartView;
import com.youth.banner.Banner;
import com.youth.banner.loader.DefaultImageLoader;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PengSong on 18/11/14.
 */

public class ShopFragment extends BaseFragment implements IShoppingContract.IShoppingView {

    private MRecyclerView<GoodsDto> mRecyclerView;
    private FloatingShoppingCartView shoppingCartView;
    private String[] imageUrls = {"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3711690120,1162131576&fm=27&gp=0.jpg", "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1274844101,636774309&fm=27&gp=0.jpg", "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1185573334,16415454&fm=27&gp=0.jpg", "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2536078587,1520810066&fm=27&gp=0.jpg"};
    //当前状态栏的状态
    private boolean dark;

    private Banner mBanner;

    private IShoppingContract.IShoppingPresenter mShoppingPresenter;


    public static ShopFragment newInstance(Bundle bundle) {
        ShopFragment fragment = new ShopFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void bindingPresenter() {
        addPresenter(new ShoppingPresenter(this));

        mShoppingPresenter = (ShoppingPresenter) getPresenter(ShoppingPresenter.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_shop;
    }

    @Override
    public void onCreateView(@Nullable Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        mRecyclerView = mViewManager.findView(R.id.recyclerView);
        mRecyclerView.addContentLayout(R.layout.item_goods_info, new ItemViewConvert<GoodsDto>() {
            @Override
            public void convert(@NonNull BViewHolder holder, GoodsDto mData, int position, @NonNull List<Object> payloads) {
                Group shoppingGroup = holder.findView(R.id.shoppingGroup);
                shoppingGroup.setVisibility(View.VISIBLE);

                TextView priceOld = holder.findView(R.id.price_old);
                TextUtil.getInstance().addDeleteLineForTextView(priceOld);

                holder.setClickListener(R.id.addShoppingCart, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mShoppingPresenter.addShoppingCard("11");
                    }
                });
            }

        }).addHeaderLayout(R.layout.header_shop, new ItemViewConvert() {
            @Override
            public void convert(@NonNull BViewHolder holder, Object mData, int position, @NonNull List payloads) {
                if (mBanner == null){
                    mBanner = holder.findView(R.id.banner);
                    mBanner.isAutoPlay(true)
                            .setDelayTime(3000)
                            .setImageLoader(new BannerImageLoader() {
                                @Override
                                public void displayImage(Context context, Object path, GlideImageView imageView) {
                                    GlideUtils.loadImageByRes(imageView,R.drawable.bg_banner);
                                }

                            }).start();
                    mShoppingPresenter.getGoodsBanner();
                }
            }
        }).create();
        mRecyclerView.addClickItemListener(new OnClickItemListener() {
            @Override
            public void clickItem(Object mData, int position) {
                mShoppingPresenter.openGoodsDetail();
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                LinearLayoutManager llm = (LinearLayoutManager) recyclerView.getLayoutManager();

                int firstVisibleItemPosition = llm.findFirstCompletelyVisibleItemPosition();
                changeStatus(firstVisibleItemPosition > 0);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        mRecyclerView.addRefreshListener(new RefreshUtils.OnRefreshListener() {
            @Override
            public void refresh() {
                LogUtils.d("------  shopping   refresh ------");
            }
        });

        mRecyclerView.startRefresh();


        shoppingCartView = mViewManager.findView(R.id.shoppingCard);
        shoppingCartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShoppingPresenter.openShoppingCard();
            }
        });


        mShoppingPresenter.getGoodsList(1);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeStatus(ChangeStatusBarEvent event){
        changeStatus(dark);
    }


    private void changeStatus(boolean status){
        if (status) {
            dark = true;
            StatusManager.getInstance().initStatusTheme(getActivity(),false,true, ContextCompat.getColor(getContext(), R.color.colorWhite));
        } else {
            dark = false;
            StatusManager.getInstance().initStatusTheme(getActivity(),false,false,StatusManager.DEFAULT_BG_COLOR);
        }
    }


    public boolean isDark() {
        return dark;
    }

    @Override
    public void showGoodsList(List<GoodsDto> goodsDtos) {
        mRecyclerView.loadDataOfNextPage(goodsDtos);
    }

    @Override
    public void showGoodsBanner(List<String> bannerUrl) {
        mBanner.update(bannerUrl);
    }

    @Override
    public void addShoppingCardSuccess() {
        shoppingCartView.addShopCart(1);
    }

    @Override
    public void openShoppingCard() {
        startActivity(ShoppingCartActivity.class);
    }

    @Override
    public void openGoodsDetail() {
        startActivity(GoodsInfoActivity.class);
    }
}
