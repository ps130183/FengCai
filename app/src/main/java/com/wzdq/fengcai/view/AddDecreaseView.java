package com.wzdq.fengcai.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wzdq.fengcai.R;

/**
 * Created by PengSong on 18/11/16.
 */

public class AddDecreaseView extends LinearLayout implements View.OnClickListener {

    private TextView tvNumber;

    private int number = 1;
    private int minNumber = 1;

    private boolean decreaseEnable = true;

    private TextView decrease;


    public AddDecreaseView(Context context) {
        this(context,null);
    }

    public AddDecreaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs){
        View rootView = LayoutInflater.from(context).inflate(R.layout.custom_view_add_decrease,null,false);

        addView(rootView);
        decrease = rootView.findViewById(R.id.decrease);
        tvNumber = rootView.findViewById(R.id.number);
        TextView add = rootView.findViewById(R.id.add);

        setNumber(number);
        decrease.setOnClickListener(this);
        add.setOnClickListener(this);

        refreshDecreaseStyle();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.decrease://减
                decrease();
                break;
            case R.id.add://加
                add();
                break;
        }
    }

    public void setNumber(int number) {
        this.number = number;
        tvNumber.setText(number+"");
        refreshDecreaseStyle();
    }

    private void add(){
        setNumber(++number);
        refreshDecreaseStyle();
        if (valueChangeListener != null){
            valueChangeListener.changeValue(number);
        }
    }

    private void decrease(){
        if (number > minNumber){
            setNumber(--number);
            refreshDecreaseStyle();
            if (valueChangeListener != null){
                valueChangeListener.changeValue(number);
            }
        }
    }

    private void refreshDecreaseStyle(){
        if (number == 1){
            decreaseEnable = false;
            decrease.setEnabled(false);
            decrease.setTextColor(ContextCompat.getColor(getContext(),R.color.colorBlockRgbDDDDDD));
        } else if (number > 1 && !decreaseEnable){
            decreaseEnable = true;
            decrease.setEnabled(true);
            decrease.setTextColor(ContextCompat.getColor(getContext(),R.color.colorBlockRgb7B7B7B));
        }
    }

    private OnValueChangeListener valueChangeListener;
    public interface OnValueChangeListener{
        void changeValue(int number);
    }

    public void setValueChangeListener(OnValueChangeListener valueChangeListener) {
        this.valueChangeListener = valueChangeListener;
    }
}
