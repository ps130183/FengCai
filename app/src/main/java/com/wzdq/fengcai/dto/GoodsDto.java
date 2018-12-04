package com.wzdq.fengcai.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.ps.mrcyclerview.SubData;
import com.ps.mrcyclerview.delegate.ItemDelegate;
import com.wzdq.fengcai.R;

/**
 * Created by PengSong on 18/11/14.
 */

public class GoodsDto implements ItemDelegate, Parcelable {

    public GoodsDto(int price) {
        this.price = price;
        setNumber(1);
    }

    private int price;
    private int number;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public int getItemViewRes() {
        return R.layout.item_goods_info;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.price);
        dest.writeInt(this.number);
    }

    protected GoodsDto(Parcel in) {
        this.price = in.readInt();
        this.number = in.readInt();
    }

    public static final Parcelable.Creator<GoodsDto> CREATOR = new Parcelable.Creator<GoodsDto>() {
        @Override
        public GoodsDto createFromParcel(Parcel source) {
            return new GoodsDto(source);
        }

        @Override
        public GoodsDto[] newArray(int size) {
            return new GoodsDto[size];
        }
    };
}
