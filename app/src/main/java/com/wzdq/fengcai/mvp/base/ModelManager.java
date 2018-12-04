package com.wzdq.fengcai.mvp.base;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by PengSong on 18/11/29.
 */

public class ModelManager {

    private static ModelManager instance;
    private ModelManager(){}

    public static ModelManager getInstance(){
        if (instance == null){
            instance = new ModelManager();
        }
        return instance;
    }

    /**
     * 通过反射获取model的实例
     * @param classPackage
     * @return
     */
    public Object getModel(String classPackage){
        Object object = null;
        try {
            object = Class.forName(classPackage).getConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }

    public static class Token{
        private static final String MODEL_DEFAULT_ROOT = "com.wzdq.fengcai.mvp.module";
        public static final String MODEL_HOME = MODEL_DEFAULT_ROOT + ".home.HomeModel";
        public static final String MODEL_WITHDRAW = MODEL_DEFAULT_ROOT + ".withdraw.WithDrawModel";

        //登录
        public static final String MODEL_LOGIN = MODEL_DEFAULT_ROOT + ".login.LoginModelImpl";
        //激活账号
        public static final String MODEL_ACTIVATE_ACCOUNT = MODEL_DEFAULT_ROOT + ".login.activate.ActivateModelImpl";
        //首页商城
        public static final String MODEL_SHOPPING = MODEL_DEFAULT_ROOT + ".shop.ShoppingModel";
        //购物车
        public static final String MODEL_SHOPPING_CART = MODEL_DEFAULT_ROOT + ".shop.shoppingcart.ShoppingCartModelImpl";
        //确认兑换商品
        public static final String MODEL_CONFIRM_CONVERSION_GOODS = MODEL_DEFAULT_ROOT + ".shop.convert.ConvertModelImpl";
    }

}
