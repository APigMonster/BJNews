package com.suse.bjnews;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class WelcomeActivity extends Activity {

    private RelativeLayout rl_welcome_root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        rl_welcome_root = findViewById(R.id.rl_welcome_root);
        setAnimation();
    }

    private void setAnimation() {
        //设置动画
        //缩放动画
        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        sa.setFillAfter(true);

        //透明度动画
        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setFillAfter(true);

        //旋转动画
        RotateAnimation ra = new RotateAnimation(0, 360);
        ra.setFillAfter(true);

        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(ra);
        animationSet.addAnimation(sa);
        animationSet.addAnimation(aa);
        animationSet.setDuration(2500);

        //开始动画
        rl_welcome_root.startAnimation(animationSet);
        //设置动画监听
        animationSet.setAnimationListener(new WelcomeAnimationListener());
    }

    class WelcomeAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            Toast.makeText(WelcomeActivity.this, "动画结束了", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
