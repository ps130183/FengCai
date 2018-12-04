package com.wzdq.fengcai.utils.dialog.city;

import android.content.Context;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 省市区 数据整理  来自assets 中的province  city  area  三个txt
 * Created by kamangkeji on 17/3/22.
 */

public class CityPickData {
    public static List<Province> provinceEntities;
    private static List<City> cityEntities;
    private static List<Area> areaEntities;

    public static List<List<City>> resultCitys;
    public static List<List<List<City>>> resultAreas;

    public static ArrayList<ProvinceBean> options1Items = new ArrayList<ProvinceBean>();
    public static ArrayList<ArrayList<String>> options2Items = new ArrayList<ArrayList<String>>();
    public static ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<ArrayList<ArrayList<String>>>();

    /**
     * 初始化
     * @param mContext
     */
    public static void initData(Context mContext) {
        Gson gson = new Gson();
        Type provinceType = new TypeToken<ArrayList<Province>>(){}.getType();
        Type cityType = new TypeToken<ArrayList<City>>(){}.getType();
        Type areaType = new TypeToken<ArrayList<Area>>(){}.getType();

        String provinceJson = getStringFromAssert(mContext,"province.txt");
        String cityJson = getStringFromAssert(mContext,"city.txt");
        String areaJson = getStringFromAssert(mContext,"area.txt");
//        Logger.d(province);
        provinceEntities = gson.fromJson(provinceJson,provinceType);
        cityEntities = gson.fromJson(cityJson,cityType);
        areaEntities = gson.fromJson(areaJson,areaType);


        new Thread(new Runnable() {
            @Override
            public void run() {
                options1Items = getProviinceBean();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                setAreaForCity();
                getCitysAndAreas();
            }
        }).start();
    }

    /**
     * 获取省的信息
     * @return
     */
    private static ArrayList<ProvinceBean> getProviinceBean(){
        ArrayList<ProvinceBean> provinceBeenList = new ArrayList<>();
        for (Province entity : provinceEntities){
//            Logger.d(entity.getName());
            provinceBeenList.add(new ProvinceBean(entity.getProID(),entity.getName(),"",""));
        }
        return provinceBeenList;
    }

    /**
     * 获取 市 和 区
     */
    private static void getCitysAndAreas(){
        ArrayList<ArrayList<ArrayList<String>>> proList = new ArrayList<ArrayList<ArrayList<String>>>();
        ArrayList<ArrayList<String>> cityList = new ArrayList<>();
        for (Province proEntity : provinceEntities){
            ArrayList<String> citys = new ArrayList<>();
            ArrayList<ArrayList<String>> areaList = new ArrayList<ArrayList<String>>();
            for (City cityEntity : cityEntities){
                if (proEntity.getProID() == cityEntity.getProID()){//获取对应市
                    citys.add(cityEntity.getName());
                    areaList.add(cityEntity.getSubArea());
                }
            }
            cityList.add(citys);
            proList.add(areaList);
        }
        options2Items = cityList;
        options3Items = proList;
    }

    /**
     * 将区的信息整合到 对应的市中
     */
    private static void setAreaForCity(){
        ArrayList<String> areaList = new ArrayList<>();

        int curCityId = 1;
        for (Area areaEntity : areaEntities){
            if (curCityId < areaEntity.getCityID()){
                City cityEntity = cityEntities.get(curCityId - 1);
                cityEntity.setSubArea(areaList);
                curCityId = areaEntity.getCityID();//获取当前市的id
                areaList = new ArrayList<>();
            }
            areaList.add(areaEntity.getName());
        }

        for (City cityEntity : cityEntities){
            if (cityEntity.getSubArea() == null){
                ArrayList<String> areas = new ArrayList<>();
                areas.add("");
                cityEntity.setSubArea(areas);
            }
        }
    }

    /**
     * 从资源库assets 中获取文件的数据
     * @param mContext
     * @param fileName
     * @return
     */
    private static String getStringFromAssert(Context mContext, String fileName) {
        String content = null; //结果字符串
        StringBuffer buffer = new StringBuffer();
        try {
            InputStream is = mContext.getAssets().open(fileName); //打开文件
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);

            while ((content = br.readLine()) != null){
                buffer.append(content);
            }
            return buffer.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            LogUtils.e(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            LogUtils.e(e.getMessage());
        }
        return "";
    }
}
