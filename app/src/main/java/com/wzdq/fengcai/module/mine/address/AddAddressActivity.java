package com.wzdq.fengcai.module.mine.address;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.wzdq.fengcai.R;
import com.wzdq.fengcai.base.BaseActivity;
import com.wzdq.fengcai.utils.dialog.city.CityBean;
import com.wzdq.fengcai.utils.dialog.city.CityDialog;
import com.wzdq.fengcai.view.MTextView;

import java.util.ArrayList;
import java.util.List;

public class AddAddressActivity extends BaseActivity implements View.OnClickListener {

    private CityDialog mCityDialog;

    @Override
    public int getContentViewRes() {
        return R.layout.activity_add_address;
    }

    @Override
    public String getTitleContent() {
        return "收货地址";
    }

    @Override
    public void onFinally(@Nullable Bundle savedInstanceState) {

        mViewManager.findView(R.id.selectArea).setOnClickListener(this);

        mCityDialog = new CityDialog(mInstance);
        List<CityBean> cityBeans = new ArrayList<>();
        cityBeans.add(new CityBean("北京市",CityBean.PROVINCE));
        cityBeans.add(new CityBean("北京市",CityBean.CITY));
        cityBeans.add(new CityBean("东城区",CityBean.AREA));

        cityBeans.add(new CityBean("河北省",CityBean.PROVINCE));
        cityBeans.add(new CityBean("石家庄市",CityBean.CITY));
        cityBeans.add(new CityBean("晋州市",CityBean.AREA));

        cityBeans.add(new CityBean("河北省",CityBean.PROVINCE));
        cityBeans.add(new CityBean("沧州市",CityBean.CITY));
        cityBeans.add(new CityBean("运河区",CityBean.AREA));

        mCityDialog.addCityList(cityBeans);

        mCityDialog.setOnSelectedFinished(new CityDialog.OnSelectedFinished() {
            @Override
            public void finish(CityBean[] cityBeans) {
                StringBuffer area = new StringBuffer();
                for (CityBean cityBean : cityBeans){
                    area.append(cityBean.getCityName());
                }
                LogUtils.d("省市区选择完毕：\n" + area.toString());

                MTextView selectArea = mViewManager.findView(R.id.selectArea);
                selectArea.setText(area.toString());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.selectArea:
                mCityDialog.show();
                break;
        }
    }
}
