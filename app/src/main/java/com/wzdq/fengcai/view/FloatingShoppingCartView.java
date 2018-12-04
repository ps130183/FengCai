package com.wzdq.fengcai.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.nineoldandroids.animation.IntEvaluator;
import com.nineoldandroids.animation.ValueAnimator;
import com.wzdq.fengcai.R;

/**
 * Created by PengSong on 18/11/15.
 */

public class FloatingShoppingCartView extends View {

    private @DrawableRes int imageSrc;

    private int hintNum = 0;

    private int circleRadius = 60;
    private float shadowRaidus = 0;

    private int shadowColor;

    private Paint mPaintCircle;
    private Paint mPaintOther;
//    private Paint hintPaint;

    //提示小圆圈的半径
    private int hintCircleRadius = 25;

    public FloatingShoppingCartView(Context context) {
        this(context,null);
    }

    public FloatingShoppingCartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FloatingShoppingCartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs){
        if (attrs != null){
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.FloatingShoppingCartView);
            imageSrc = ta.getResourceId(R.styleable.FloatingShoppingCartView_image_src,0);
            circleRadius = ta.getDimensionPixelOffset(R.styleable.FloatingShoppingCartView_circle_radius,dp2px(context,60));
            shadowRaidus = ta.getFloat(R.styleable.FloatingShoppingCartView_shadow_radius,0);
            shadowColor = ta.getColor(R.styleable.FloatingShoppingCartView_shadow_color,Color.BLACK);
            ta.recycle();//回收资源
        }

        mPaintCircle = new Paint(Paint.ANTI_ALIAS_FLAG);


        mPaintOther = new Paint(Paint.ANTI_ALIAS_FLAG);
        setLayerType(View.LAYER_TYPE_SOFTWARE,null);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST || heightMode == MeasureSpec.AT_MOST){
            setMeasuredDimension((int) (circleRadius + shadowRaidus), (int) (circleRadius + shadowRaidus));
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        int centerX = width / 2;
        int centerY = height / 2;
        int radius = circleRadius / 2 - 10;


        //1、首先绘制阴影部分  如果有的话
        if (shadowRaidus > 0){
            mPaintOther.reset();
            mPaintOther.setColor(shadowColor);
            mPaintOther.setStyle(Paint.Style.FILL);
            mPaintOther.setMaskFilter(new BlurMaskFilter(shadowRaidus, BlurMaskFilter.Blur.OUTER));
            canvas.drawCircle(centerX,centerY,radius, mPaintOther);
        }


        //2、绘制主体圆的
        mPaintCircle.reset();
        mPaintCircle.setAntiAlias(true);
        mPaintCircle.setColor(Color.WHITE);
        mPaintCircle.setStyle(Paint.Style.FILL);
        mPaintCircle.setStrokeWidth(5);
        canvas.drawCircle(centerX,centerY,radius, mPaintCircle);


        //3、绘制主体圆的边框
        mPaintOther.reset();
        mPaintOther.setAntiAlias(true);
        mPaintOther.setColor(0xaa000000);
        mPaintOther.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(centerX,centerY,radius, mPaintOther);

        //4、绘制购物车图标
        int length = (int) Math.sqrt(radius * radius / 2);
        if (imageSrc != 0){
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),imageSrc);
            RectF imageRect = new RectF(centerX - length,centerY - length,centerX + length,centerY + length);
            canvas.drawBitmap(bitmap,null,imageRect, mPaintOther);
        }



        //5、购物车有商品时，绘制提示红色圆
        if (hintNum > 0){
            //绘制红色的圆形提示框
            mPaintCircle.reset();
            mPaintCircle.setAntiAlias(true);
            mPaintCircle.setColor(0xffff0000);
            mPaintCircle.setStyle(Paint.Style.FILL_AND_STROKE);

            int hintCircleX = centerX + length;
            int hintCircleY = centerY - length;
            canvas.drawCircle(hintCircleX,hintCircleY,hintCircleRadius, mPaintCircle);

            //绘制提示数
            mPaintOther.reset();
            mPaintOther.setAntiAlias(true);
            mPaintOther.setColor(Color.WHITE);
            mPaintOther.setStyle(Paint.Style.FILL);
            mPaintOther.setTextSize(sp2px(getContext(),8));
            mPaintOther.setStrokeWidth(3);
            mPaintOther.setTextAlign(Paint.Align.CENTER);

//            Paint.FontMetrics fontMetrics = mPaintOther.getFontMetrics();
            int textBaseLine = (int) (hintCircleY + (Math.abs(mPaintOther.ascent()) - mPaintOther.descent()) / 2);

            canvas.drawText(hintNum > 99 ? "99+" : hintNum+"",hintCircleX,textBaseLine,mPaintOther);
        }

    }

    /**
     * 开启动画
     */
    public void startAnimator(){
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,25);
        valueAnimator.setDuration(300);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.setEvaluator(new IntEvaluator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                setHintCircleRadius(value);
                postInvalidate();
            }
        });
        valueAnimator.start();
    }

    /**
     * 给购物车加数据
     * @param num
     */
    public void addShopCart(int num){
        setHintNum(hintNum + num);
        if (hintNum == 1){
            startAnimator();
        } else {
            postInvalidate();
        }
    }

    public int getHintNum() {
        return hintNum;
    }

    public void setHintNum(int hintNum) {
        this.hintNum = hintNum;
    }

    public int getHintCircleRadius() {
        return hintCircleRadius;
    }

    public void setHintCircleRadius(int hintCircleRadius) {
        this.hintCircleRadius = hintCircleRadius;
    }

    /**
     * dp转换成px
     */
    private int dp2px(Context context,float dpValue){
        float scale=context.getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale+0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     * @param spValue
     * @return
     */
    private int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
