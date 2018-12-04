package com.wzdq.fengcai.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.support.v7.widget.AppCompatTextView;

import com.blankj.utilcode.util.LogUtils;
import com.wzdq.fengcai.R;

/**
 * Created by PengSong on 18/11/16.
 */

public class MTextView extends AppCompatTextView {

    private int imageIcon;
    private int imageWidth;
    private int imageHeight;

    private int imageDirection;

    private static final int LEFT = 0;
    private static final int TOP = 1;
    private static final int RIGHT = 2;
    private static final int BOTTOM = 3;


    private int radiusLeftTop;
    private int radiusRightTop;
    private int radiusLeftBottom;
    private int radiusRightBottom;


    //边框
    private int borderWidth;
    private int borderColor;
    private int borderCorner;
    private boolean mFollowTextColor;

    private float[] radiusArray = { 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f };

    //背景
    private static final int DEF = 0;
    private static final int CIRCLE = 1;
    private int backgroundType;
    private int backgroundColor;

    private Paint mPaint;


    //短信提示
    private String afterSendText;
    private int afterSendTextColor;
    private int countDownTime;//倒计时的 时间长度  单位秒
    private int mCountDownTime;

    private CountDownThread mCountDownThread;

    public MTextView(Context context) {
        super(context);
    }

    public MTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MTextView);
            int count = typedArray.getIndexCount();
            for (int i = 0; i < count; i++) {
                int attr = typedArray.getIndex(i);
                switch (attr) {
                    case R.styleable.MTextView_borderWidth:
                        borderWidth = typedArray.getDimensionPixelOffset(attr, 0);
                        break;
                    case R.styleable.MTextView_borderColor:
                        borderColor = typedArray.getColor(attr, Color.BLACK);
                        break;
                    case R.styleable.MTextView_borderCorner:
                        borderCorner = typedArray.getDimensionPixelOffset(attr, 0);
                        break;
                    case R.styleable.MTextView_imageIcon:
                        imageIcon = typedArray.getResourceId(attr, 0);
                        break;
                    case R.styleable.MTextView_imageWidth:
                        imageWidth = typedArray.getDimensionPixelOffset(attr, 0);
                        break;
                    case R.styleable.MTextView_imageHeight:
                        imageHeight = typedArray.getDimensionPixelOffset(attr, 0);
                        break;
                    case R.styleable.MTextView_imageDirection:
                        imageDirection = typedArray.getInt(attr, LEFT);
                        break;
                    case R.styleable.MTextView_followTextColor:
                        mFollowTextColor = typedArray.getBoolean(attr,false);
                        break;

                    case R.styleable.MTextView_afterSendText:
                        afterSendText = typedArray.getString(attr);
                        break;
                    case R.styleable.MTextView_afterSendTextColor:
                        afterSendTextColor = typedArray.getColor(attr,0);
                        break;
                    case R.styleable.MTextView_countDownTime:
                        mCountDownTime = countDownTime = typedArray.getInteger(attr,0);
                        break;
                    case R.styleable.MTextView_backgroundType:
                        backgroundType = typedArray.getInt(attr,DEF);
                        break;
                    case R.styleable.MTextView_backgroundColor:
                        backgroundColor = typedArray.getColor(attr,Color.TRANSPARENT);
                        break;
                    case R.styleable.MTextView_radiusLeftTop:
                        radiusLeftTop = typedArray.getDimensionPixelOffset(attr,0);
                        break;
                    case R.styleable.MTextView_radiusRightTop:
                        radiusRightTop = typedArray.getDimensionPixelOffset(attr,0);
                        break;
                    case R.styleable.MTextView_radiusLeftBottom:
                        radiusLeftBottom = typedArray.getDimensionPixelOffset(attr,0);
                        break;
                    case R.styleable.MTextView_radiusRightBottom:
                        radiusRightBottom = typedArray.getDimensionPixelOffset(attr,0);
                        break;
                }
            }

            if (mFollowTextColor){
                borderColor = getCurrentTextColor();
            }

            if (afterSendTextColor == 0){
                afterSendTextColor = getCurrentTextColor();
            }

            if (borderCorner > 0){
                setRadius(borderColor,borderColor,borderColor,borderColor);
            } else {
                setRadius(radiusLeftTop,radiusRightTop,radiusRightBottom,radiusLeftBottom);
            }

            typedArray.recycle();
        }


        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        drawPicture();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = getMeasuredWidth();
        int heightSize = getMeasuredHeight();

        widthSize += imageWidth + borderWidth;
        heightSize += borderWidth;

        heightSize = Math.max(heightSize,imageHeight);

        if (backgroundType == CIRCLE){

            setMeasuredDimension(widthSize,widthSize);
        } else {
            setMeasuredDimension(widthSize,heightSize);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawBackground(canvas);
        drawBorder(canvas);
        drawPicture();
        super.onDraw(canvas);
    }

    /**
     * 画边框
     *
     * @param canvas
     */
    private void drawBorder(Canvas canvas) {

        //边框的宽度为0  不去绘制
        if (borderWidth == 0){
            return;
        }

        mPaint.reset();
        mPaint.setColor(borderColor);
        mPaint.setStrokeWidth(borderWidth);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);

        if (backgroundType == DEF){
            //边框矩形
            RectF borderRect = new RectF();
            borderRect.left = borderRect.top = 0.5f * borderWidth;
            borderRect.right = getMeasuredWidth() - borderWidth;
            borderRect.bottom = getMeasuredHeight() - borderWidth;

            float corner = Math.min(getHeight() / 2, borderCorner);

//            Path path = new Path();
//            path.addRoundRect(borderRect,radiusArray,Path.Direction.CW);
//            canvas.clipPath(path);
            canvas.drawRoundRect(borderRect, corner, corner, mPaint);
        } else {
            int width = getWidth() - borderWidth;
            int radius = width / 2;
            canvas.drawCircle(radius,radius,radius,mPaint);
        }



    }

    /**
     * 绘制背景
     * @param canvas
     */
    private void drawBackground(Canvas canvas){
        mPaint.reset();
        mPaint.setColor(backgroundColor);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);

        if (backgroundType == DEF){
            //边框矩形
            RectF borderRect = new RectF();
            borderRect.left = borderRect.top = 0.5f * borderWidth;
            borderRect.right = getMeasuredWidth() - borderWidth;
            borderRect.bottom = getMeasuredHeight() - borderWidth;

            float corner = Math.min(getHeight() / 2, borderCorner);
            canvas.drawRoundRect(borderRect, corner, corner, mPaint);

//            Path path = new Path();
//            path.addRoundRect(borderRect,radiusArray,Path.Direction.CW);
//            canvas.clipPath(path);
        } else {
            int width = getWidth() - borderWidth;
            int radius = width / 2;
            canvas.drawCircle(radius,radius,radius,mPaint);
        }
    }

    /**
     * 绘制周边的图片
     */
    private void drawPicture() {
        Bitmap mImage = null;
        if (imageIcon != 0) {
            mImage = BitmapFactory.decodeResource(getResources(), imageIcon);
            if (mImage != null) {
                Drawable mDrawable;
                if (imageWidth != 0 && imageHeight != 0) {
                    mDrawable = new BitmapDrawable(getResources(), getRealBitmap(mImage));
                } else {
                    mDrawable = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(mImage, mImage.getWidth(), mImage.getHeight(), true));
                }
                switch (imageDirection) {
                    case LEFT:
                        this.setCompoundDrawablesWithIntrinsicBounds(mDrawable, null,
                                null, null);
                        break;
                    case TOP:
                        this.setCompoundDrawablesWithIntrinsicBounds(null, mDrawable,
                                null, null);
                        break;
                    case RIGHT:
                        this.setCompoundDrawablesWithIntrinsicBounds(null, null,
                                mDrawable, null);
                        break;
                    case BOTTOM:
                        this.setCompoundDrawablesWithIntrinsicBounds(null, null, null,
                                mDrawable);
                        break;
                }

            }
        } else {
            this.setCompoundDrawablesWithIntrinsicBounds(null, null, null,
                    null);
        }
    }


    /**
     * 设置四个角的圆角半径
     */
    public void setRadius(float leftTop, float rightTop, float rightBottom, float leftBottom) {
        radiusArray[0] = leftTop;
        radiusArray[1] = leftTop;
        radiusArray[2] = rightTop;
        radiusArray[3] = rightTop;
        radiusArray[4] = rightBottom;
        radiusArray[5] = rightBottom;
        radiusArray[6] = leftBottom;
        radiusArray[7] = leftBottom;

        invalidate();
    }

    /**
     * 开始倒计时
     */
    public void startCountDown(){
        if (countDownTime <= 0){
            return;
        }
        setEnabled(false);
        setTextColor(afterSendTextColor);
        mCountDownThread = new CountDownThread();
        mCountDownThread.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mCountDownThread != null){
            mCountDownThread.killThread(true);
        }
    }

    private Bitmap getRealBitmap(Bitmap image) {
        //根据需要Drawable原来的大小和目标宽高进行裁剪（缩放）
        int width = image.getWidth();// 获得图片的宽高
        int height = image.getHeight();
        // 取得想要缩放的matrix参数
        float scaleWidth = (float) imageWidth / width;
        float scaleHeight = (float) imageHeight / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 返回新的Bitmap
        return Bitmap.createBitmap(image, 0, 0, width, height, matrix, true);

    }


    private class CountDownThread extends Thread{

        private boolean killThread = false;

        public void killThread(boolean killThread) {
            this.killThread = killThread;
        }

        @Override
        public void run() {

            while (countDownTime > 0 && !killThread){
                countDownTime--;
                post(new Runnable() {
                    @Override
                    public void run() {
                        setText(afterSendText + "(" + countDownTime + ")");
                    }
                });
                LogUtils.d("当前到计时：" + countDownTime);
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            post(new Runnable() {
                @Override
                public void run() {
                    setEnabled(true);
                    setText(afterSendText);
                    countDownTime = mCountDownTime;
                    mCountDownThread = null;
                }
            });

        }
    }

    public void setImageIcon(int imageIcon) {
        this.imageIcon = imageIcon;
        invalidate();
    }
}
