package com.wzdq.fengcai.utils.dialog.city;

import com.ps.mrcyclerview.delegate.ItemDelegate;
import com.wzdq.fengcai.R;

/**
 * Created by PengSong on 18/11/23.
 */

public class CityBean implements ItemDelegate,Cloneable {

    public static final int PROVINCE = 0;
    public static final int CITY = 1;
    public static final int AREA = 2;

    private String cityName;
    private boolean selected;
    private int cityLevel;

    public CityBean(String cityName) {
        this.cityName = cityName;
        this.cityLevel = -1;
    }

    public CityBean(String cityName, int cityLevel) {
        this(cityName);
        this.cityLevel = cityLevel;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getCityLevel() {
        return cityLevel;
    }

    public void setCityLevel(int cityLevel) {
        this.cityLevel = cityLevel;
    }

    @Override
    public int getItemViewRes() {
        return R.layout.item_city;
    }

    @Override
    public String toString() {
        return "CityBean{" +
                "cityName='" + cityName + '\'' +
                ", selected=" + selected +
                ", cityLevel=" + cityLevel +
                '}';
    }

    @Override
    protected CityBean clone() {

        try {
            return (CityBean) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();

        }
        return null;
    }
}
