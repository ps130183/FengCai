package com.wzdq.fengcai.utils.statusBar;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;

import java.util.HashMap;

/**
 * Created by PengSong on 18/11/15.
 */

public class StatusManager {

    public static final int DEFAULT_BG_COLOR = 0xffffff;

    private StatusManager(){
        map = new HashMap<>();
    }
    private static StatusManager instance;
    public static StatusManager getInstance(){
        if (instance == null){
            instance = new StatusManager();
        }
        return instance;
    }

    private HashMap<Activity,StatusThemeEntity> map;

    public void initStatusTheme(Activity activity){
        //获取状态栏的主题
        StatusThemeEntity entity = getStatusTheme(activity);

        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(activity,entity.isFitSystemWindows());
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(activity);
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(activity, entity.isDark())) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(activity,entity.getBgColor());
        }
        StatusBarUtil.setStatusBarColor(activity,entity.getBgColor());
    }

    public void initStatusTheme(Activity activity,int bgColor){
        StatusThemeEntity entity = getStatusTheme(activity);
        initStatusTheme(activity,entity.isFitSystemWindows(),entity.isDark(),bgColor);
    }

    public void initStatusTheme(Activity activity,boolean isDark){
        StatusThemeEntity entity = getStatusTheme(activity);
        initStatusTheme(activity,entity.isFitSystemWindows(),isDark,entity.getBgColor());
    }

    public void initStatusTheme(Activity activity,boolean isFitSystemWindows,boolean isDark){
        StatusThemeEntity entity = getStatusTheme(activity);
        initStatusTheme(activity,isFitSystemWindows,isDark,entity.getBgColor());
    }

    /**
     * 动态修改状态栏的主题
     * 只有isDark为true时 bgColor才会生效
     * isFitSystemWindows 设置 true 时，会在屏幕最上方预留出状态栏高度的 padding,因此 为false时会出现沉浸式的状态栏效果
     * @param activity
     * @param isFitSystemWindows
     * @param isDark
     * @param bgColor
     */
    public void initStatusTheme(Activity activity,boolean isFitSystemWindows,boolean isDark,int bgColor){
        StatusThemeEntity entity = getStatusTheme(activity);
        entity.setFitSystemWindows(isFitSystemWindows);
        entity.setDark(isDark);
        entity.setBgColor(bgColor);
        map.put(activity,entity);
        initStatusTheme(activity);
    }

    /**
     * 根据activity获取对应的状态栏主题
     * @param activity
     * @return
     */
    private StatusThemeEntity getStatusTheme(Activity activity){
        StatusThemeEntity entity = null;
        if (map.containsKey(activity)){
            entity = map.get(activity);
        } else {
            entity = new StatusThemeEntity(true,true,DEFAULT_BG_COLOR);
        }
        return entity;
    }


}
