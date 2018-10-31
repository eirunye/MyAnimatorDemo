# 动画的使用相关

# ValueAnimator

## 简单使用

> 常用的使用方式
  ```JAVA
   ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
   valueAnimator.setDuration(2000);
   valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
   valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
   valueAnimator.setInterpolator(new LinearInterpolator());
   @Override
   public void onAnimationUpdate(ValueAnimator animation) {

       value = (float) animation.getAnimatedValue();
       postInvalidate();
   }
   });
   valueAnimator.start();
  ```

# ObjectAnimator

## 简单使用

# 总结
