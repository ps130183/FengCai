package com.wzdq.fengcai.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.blankj.utilcode.util.ConvertUtils;
import com.wzdq.fengcai.R;

import java.util.Map;

/**
 * Created by PengSong on 18/11/28.
 */

public class DivideLine extends View {

    //分割线粗细
    private int divideStroke = dp2px(getContext(),1);

    //线的颜色
    private int divideColor = Color.BLACK;

    //方向  垂直 或 水平
    private int divideDirector;
    private static final  int Vertical = 0;
    private static final  int Horizontal = 1;

    //线的间隔  实线 为0   虚线 为大于0 的值
    private int divideInterval;
    private int dashedLength;

    private Paint mPaint;

    public DivideLine(Context context) {
        super(context);
    }

    public DivideLine(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DivideLine(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDivide(context,attrs);
    }

    private void initDivide(Context context, @Nullable AttributeSet attrs){

        if (attrs != null){
            TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.DivideLine);
            for (int i = 0; i < ta.getIndexCount(); i++){
                int attr = ta.getIndex(i);
                switch (attr){
                    case R.styleable.DivideLine_divideStroke:
                        divideStroke = ta.getDimensionPixelOffset(attr,dp2px(context,1));
                        break;
                    case R.styleable.DivideLine_divideColor:
                        divideColor = ta.getColor(attr,Color.TRANSPARENT);
                        break;
                    case R.styleable.DivideLine_divideDirector:
                        divideDirector = ta.getInteger(attr,Vertical);
                        break;
                    case R.styleable.DivideLine_divideInterval:
                        divideInterval = ta.getDimensionPixelOffset(attr,0);
                        break;
                    case R.styleable.DivideLine_dashedLength:
                        dashedLength = ta.getDimensionPixelOffset(attr,-1);
                        break;
                }
            }

            ta.recycle();
        }

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(divideColor);
        mPaint.setStrokeWidth(divideStroke);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setAntiAlias(true);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int withSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (divideDirector == Vertical){
            setMeasuredDimension(withSize,divideStroke);
        } else {
            setMeasuredDimension(divideStroke,heightSize);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (divideDirector == Vertical){
            drawVerticalLine(canvas);
        } else {
            drawHorizontalLine(canvas);
        }

    }

    private void drawVerticalLine(Canvas canvas){

        int startX = 0;
        int startY = getHeight() / 2;

        int endX = getWidth();
        int entY = getHeight() / 2;

        Path path = new Path();
        path.moveTo(startX,startY);
        if (divideInterval > 0 && dashedLength > 0){
            endX = startX;
            while (endX <= getWidth()){
                endX += dashedLength;
                path.lineTo(endX,entY);

                endX +=divideInterval;
                path.moveTo(endX,entY);
            }

            path.setLastPoint(getWidth(),getHeight() / 2);
        } else {//实线
            path.lineTo(endX,entY);

        }
        canvas.drawPath(path,mPaint);

    }

    private void drawHorizontalLine(Canvas canvas){
        int startX = getWidth()/2;
        int startY = 0;

        int endX = getWidth() / 2;
        int entY = getHeight();

        Path path = new Path();
        path.moveTo(startX,startY);
        if (divideInterval > 0 && dashedLength > 0){
            entY = startY;
            while (entY <= getHeight()){
                entY += dashedLength;
                path.lineTo(endX,entY);

                entY +=divideInterval;
                path.moveTo(endX,entY);
            }
            path.setLastPoint(getWidth() / 2,getHeight());
        } else {//实线
            path.lineTo(endX,entY);

        }
        canvas.drawPath(path,mPaint);
    }

    /**
     * dp转换成px
     */
    private int dp2px(Context context,float dpValue){
        float scale=context.getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale+0.5f);
    }

}
