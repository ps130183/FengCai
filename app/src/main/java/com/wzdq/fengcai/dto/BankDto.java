package com.wzdq.fengcai.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.ps.mrcyclerview.delegate.ItemDelegate;
import com.wzdq.fengcai.R;

/**
 * Created by PengSong on 18/7/3.
 */

public class BankDto implements ItemDelegate, Parcelable {


    /**
     * bankLogo : http://47.93.184.121:8080/wzdq/Aiyg/aiygImage/2018/06/d0045fd828c74a6684f44f9fade3418e.png
     * bankName : 中信实业银行
     * id : 1
     */

    private String bankLogo;
    private String bankName;
    private String id;

    private boolean checked;


    private String bankId;	//银行表ID
    private String userName;	//申报员编号（账号）用户账号（编号）
    private String realName;		//持卡人,用户真实姓名
    private String bankNo;		//卡号
    private String phone;		//预留手机
    private String bank;	//银行名称
    private long updateTime;		//更新时间
    private long createTime;		//创建时间
    public String getBankId() {
        return bankId;
    }
    public void setBankId(String bankId) {
        this.bankId = bankId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getRealName() {
        return realName;
    }
    public void setRealName(String realName) {
        this.realName = realName;
    }
    public String getBankNo() {
        return bankNo;
    }
    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getBank() {
        return bank;
    }
    public void setBank(String bank) {
        this.bank = bank;
    }
    public long getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
    public long getCreateTime() {
        return createTime;
    }
    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Override
    public int getItemViewRes() {
        return R.layout.item_select_bank;
    }

    public String getBankLogo() {
        return bankLogo;
    }

    public void setBankLogo(String bankLogo) {
        this.bankLogo = bankLogo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public BankDto() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.bankLogo);
        dest.writeString(this.bankName);
        dest.writeString(this.id);
        dest.writeByte(this.checked ? (byte) 1 : (byte) 0);
    }

    protected BankDto(Parcel in) {
        this.bankLogo = in.readString();
        this.bankName = in.readString();
        this.id = in.readString();
        this.checked = in.readByte() != 0;
    }

    public static final Creator<BankDto> CREATOR = new Creator<BankDto>() {
        @Override
        public BankDto createFromParcel(Parcel source) {
            return new BankDto(source);
        }

        @Override
        public BankDto[] newArray(int size) {
            return new BankDto[size];
        }
    };
}
