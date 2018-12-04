package com.wzdq.fengcai.utils;

import android.graphics.Paint;
import android.widget.TextView;

/**
 * Created by PengSong on 18/11/14.
 */

public class TextUtil {

    private TextUtil(){}
    private static TextUtil INSTANCE;
    public static TextUtil getInstance(){
        if (INSTANCE == null){
            INSTANCE = new TextUtil();
        }
        return INSTANCE;
    }

    public void addDeleteLineForTextView(TextView tv){
        tv.setPaintFlags(tv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }
}
