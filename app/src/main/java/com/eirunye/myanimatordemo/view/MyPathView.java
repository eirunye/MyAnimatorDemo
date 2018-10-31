package com.eirunye.myanimatordemo.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Author Eirunye
 * Created by on 2018/10/31.
 * Describe
 */
public class MyPathView extends View {

    private final float length;
    private Paint paint;
    private Path path;
    private Path path1;
    private PathMeasure pathMeasure;
    private ValueAnimator valueAnimator;
    private float value;

    public MyPathView(Context context) {
        this(context, null);
    }

    public MyPathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        path = new Path();
        path1 = new Path();
        path.addCircle(400, 400, 150, Path.Direction.CW);
        pathMeasure = new PathMeasure();
        pathMeasure.setPath(path, true);
        length = pathMeasure.getLength();

        startAnimator();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path1.reset();
        path1.lineTo(0, 0);

        float stop = length * value;
        float start = (float) (stop - ((0.5 - Math.abs(value - 0.5)) * length));
        // 从起点开始截取, 路径将越来越长
        pathMeasure.getSegment(start, stop, path1, true); // 截取整个path的任何片段(开始长度 / 结束长度 / 保存截取的路径 / 是否从起点开始截取)
        canvas.drawPath(path1, paint);
    }

    public void startAnimator() {
        valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                value = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        valueAnimator.start();
    }
}
