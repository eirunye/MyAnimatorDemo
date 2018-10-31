package com.eirunye.myanimatordemo.view;

import android.animation.TypeEvaluator;


/**
 * Author Eirunye
 * Created by on 2018/10/31.
 * Describe
 */
public class MyEvaluator implements TypeEvaluator<Integer> {
    @Override
    public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
        System.out.println(startValue + "====" + endValue);
        int startInt = startValue;
        return (int) (startInt + fraction * (endValue - startInt));
    }
}
