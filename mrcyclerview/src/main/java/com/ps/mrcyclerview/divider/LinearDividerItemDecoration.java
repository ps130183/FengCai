package com.ps.mrcyclerview.divider;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ps.mrcyclerview.IAdapter;
import com.ps.mrcyclerview.RecyclerAdapter;
import com.ps.mrcyclerview.SRecyclerAdapter;

/**
 * Created by PengSong on 18/6/9.
 */

public class LinearDividerItemDecoration extends Y_DividerItemDecoration {

    private int dividerWidth;
    private @ColorInt
    int dividerColor;
    private int orientation = LinearLayoutManager.VERTICAL;
    private RecyclerView mRecyclerView;
    private int divideLocation;
    public static final int DIVIDE_LOCATION_LEFT = 0;
    public static final int DIVIDE_LOCATION_TOP = 1;
    public static final int DIVIDE_LOCATION_RIGHT = 2;
    public static final int DIVIDE_LOCATION_BOTTOM = 3;

    public LinearDividerItemDecoration(Context context) {
        super(context);
    }

    public LinearDividerItemDecoration(RecyclerView recyclerView, int dividerWidth, int dividerColor,int divideLocation) {
        super(recyclerView.getContext());
        mRecyclerView = recyclerView;
        this.dividerWidth = dividerWidth;
        this.dividerColor = dividerColor;
        LinearLayoutManager llm = (LinearLayoutManager) recyclerView.getLayoutManager();
        this.orientation = llm.getOrientation();
    }

    @Nullable
    @Override
    public Y_Divider getDivider(int itemPosition) {
        Y_Divider divider = null;
        IAdapter adapter = (IAdapter) mRecyclerView.getAdapter();
        if (adapter.isContentView(itemPosition) && itemPosition < adapter.getDataSize()) {
            if (orientation == LinearLayoutManager.VERTICAL) {
                if (divideLocation == DIVIDE_LOCATION_BOTTOM){
                    divider = new Y_DividerBuilder()
                            .setBottomSideLine(dividerWidth != 0, dividerColor, dividerWidth, 0, 0)
                            .create();
                } else {
                    divider = new Y_DividerBuilder()
                            .setTopSideLine(dividerWidth != 0, dividerColor, dividerWidth, 0, 0)
                            .create();
                }

            } else {
                if (divideLocation == DIVIDE_LOCATION_RIGHT){
                    divider = new Y_DividerBuilder()
                            .setRightSideLine(dividerWidth != 0, dividerColor, dividerWidth, 0, 0)
                            .create();
                } else {
                    divider = new Y_DividerBuilder()
                            .setLeftSideLine(dividerWidth != 0, dividerColor, dividerWidth, 0, 0)
                            .create();
                }

            }
        }

        return divider;
    }
}
