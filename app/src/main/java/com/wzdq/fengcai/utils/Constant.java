package com.wzdq.fengcai.utils;


import java.io.File;
import java.util.List;

/**
 * Created by PengSong on 18/1/18.
 */

public class Constant {
//    public static UserLoginDto userLoginInfo;
//    public static UserInfoDto userInfo;

//    public static List<ContractDto> mBindingContractList;
//    public static List<ContractDto> mUnBindingContractList;
    public static boolean isAllowUserCard = true;

    //客服电话
    public final static String SERVICE_JISHU_PHONE = "13699231246";
    public final static String SERVICE_KEFU_PHONE = "18401438716";

    //获取名片 接口
//    public static final String QRCODE_URL = ApiConstant.API_BASE_URL +  "/user/saoUserCard/info/send?mobilePhone=";

    public static final String SAVE_PATH_DEFAULT = File.separator + "FengCai" + File.separator + "app" + File.separator;

    static {
//        if (userLoginInfo == null){
//            userLoginInfo = new UserLoginDto();
//            userLoginInfo.getDataFromSp();
//        }
    }
}
