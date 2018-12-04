package com.wzdq.fengcai.base;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.CrashUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.Utils;
import com.hss01248.dialog.ActivityStackManager;
import com.hss01248.dialog.StyledDialog;

import java.io.File;

import kr.co.namee.permissiongen.PermissionGen;

/**
 * Created by PengSong on 17/12/22.
 */

public class BaseApplication extends Application {

    private static BaseApplication mInstance;

//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(base);
//        MultiDex.install(this);
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
//        CrashHandler.get Instance().init(this);
        initCrash();
//        initBaiDuMap();
        //初始化极光推送
//        JPushInterface.init(this);
        Utils.init(this);
        initDialogUtils();
//        UmengShareUtils.initUmengShare(this);
        initDb();
    }

    public static BaseApplication getInstance() {
        return mInstance;
    }


    private void initCrash() {
        final String crashLogPath = Environment.getExternalStorageDirectory().getPath() + File.separator + "FengCai" + File.separator + "Crash" + File.separator;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        CrashUtils.init(crashLogPath, new CrashUtils.OnCrashListener() {
            @Override
            public void onCrash(String crashInfo, Throwable e) {

            }
        });
    }

    /**
     * 初始化数据库配置
     */
    private void initDb() {
//        GreenDaoManager.getInstance().initDb(getInstance());
    }


    /**
     * 初始化百度地图
     */
    private void initBaiDuMap(){
        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
//        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
//        SDKInitializer.setCoordType(CoordType.BD09LL);
    }

    private void initDialogUtils(){
        //在Application的oncreate方法里:传入context
        StyledDialog.init(this);
//
//        //在activity生命周期callback中拿到顶层activity引用:
        registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                ActivityStackManager.getInstance().addActivity(activity);

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                ActivityStackManager.getInstance().removeActivity(activity);
            }
        });
    }
}
