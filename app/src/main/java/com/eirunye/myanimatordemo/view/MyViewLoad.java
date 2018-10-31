package com.eirunye.myanimatordemo.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.eirunye.myanimatordemo.DensityUtil;
import com.eirunye.myanimatordemo.R;

/**
 * Author Eirunye
 * Created by on 2018/10/31.
 * Describe
 */
public class MyViewLoad extends View {
    private int textColor;
    private int loadSize;
    private int loadColor;
    private Paint paint;
    private int mWidth;
    private Context context;
    private int mHeight;
    private int progress = 10;
    private ValueAnimator valueAnimator;

    public MyViewLoad(Context context) {
        this(context, null);
    }

    public MyViewLoad(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyViewLoad(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyViewLoad);
        loadColor = array.getColor(R.styleable.MyViewLoad_load_color, Color.GRAY);
        loadSize = array.getInt(R.styleable.MyViewLoad_load_size, 5);
        textColor = array.getColor(R.styleable.MyViewLoad_text_color, Color.RED);
        array.recycle();
        paint = new Paint();
        paint.setStrokeWidth(loadSize);
        paint.setAntiAlias(true);
//        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(loadColor);
        startAnimator();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        } else {
            mWidth = DensityUtil.dp2px(context, 120);
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else {
            mHeight = DensityUtil.dp2px(context, 120);
        }

        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();

        for (int i = 0; i < 100; i++) {
            if (progress > i) {
                paint.setColor(Color.GREEN);
            } else {
                paint.setColor(loadColor);
            }
            canvas.drawLine(mWidth / 2, 0, mHeight / 2, DensityUtil.dp2px(context, 20), paint);
            canvas.rotate(3.6f, mWidth / 2, mHeight / 2);
        }

        canvas.restore();

    }

    public void startAnimator() {

        valueAnimator = ValueAnimator.ofInt(0, 100);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setDuration(2000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progress = (int) animation.getAnimatedValue();
                invalidate();
            }
        });

        valueAnimator.start();
    }

    public void cancelAnimator(){
        if (valueAnimator!=null&&valueAnimator.isRunning())valueAnimator.cancel();
    }

}
