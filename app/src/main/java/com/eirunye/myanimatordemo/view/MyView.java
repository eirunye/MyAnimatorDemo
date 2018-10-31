package com.eirunye.myanimatordemo.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Author Eirunye
 * Created by on 2018/10/31.
 * Describe
 */
public class MyView extends View {

    private Paint paint;
    private Path path;
    private PathMeasure pathMeasure;

    private ObjectAnimator objectAnimator;
    private ValueAnimator valueAnimator;
    private int dx = 200;
    private int dy = 300;
    private int des = 200;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        path = new Path();
        pathMeasure = new PathMeasure();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        paint.setStyle(Paint.Style.STROKE);
//        paint.setColor(Color.BLUE);
//        path.reset();
//        path.moveTo(200, 300);
//        path.lineTo(dx, 300);
////        path.lineTo(700, 500);
////        path.lineTo(200, 500);
////        path.close();
//        canvas.drawPath(path, paint);
//
//        paint.setStyle(Paint.Style.FILL);
//        paint.setColor(Color.GREEN);
//        canvas.drawCircle(dx, dy, 20, paint);

        canvas.save();
        for (int i = 0; i < 100; i++) {
            canvas.drawLine(200 / 2, 0, 200 / 2, 20, paint);
            canvas.rotate(3.6f, 200 / 2, 200 / 2);
        }
        canvas.restore();


    }

    public void startAnimator() {
        valueAnimator = ValueAnimator.ofInt(200, 700);
        valueAnimator.setDuration(4000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        valueAnimator.setEvaluator(new MyEvaluator());
        valueAnimator.start();
    }
}
