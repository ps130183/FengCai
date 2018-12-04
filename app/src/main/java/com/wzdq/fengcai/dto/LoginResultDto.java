package com.wzdq.fengcai.dto;

import android.text.TextUtils;

/**
 * Created by PengSong on 18/11/29.
 */

public class LoginResultDto {
    private String userName;
    private int age;

    public LoginResultDto() {
    }

    public LoginResultDto(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isEmpty(){
        if (TextUtils.isEmpty(userName)){
            return true;
        }
        return false;
    }
}
