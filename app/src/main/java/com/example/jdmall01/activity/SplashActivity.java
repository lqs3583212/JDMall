package com.example.jdmall01.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.jdmall01.R;
import com.example.jdmall01.util.ActivityUtil;

public class SplashActivity extends BaseActivity {

    private ImageView mIv;


    @Override
    protected void initUI() {
        mIv = findViewById(R.id.logo_iv);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initUI();
        alphaAnim();

    }

    private void alphaAnim() {
        AlphaAnimation anim = new AlphaAnimation(0.2f, 1.0f);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ActivityUtil.start(SplashActivity.this, LoginActivity.class, true);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {


            }
        });
        anim.setDuration(3000);
        anim.setFillAfter(true);
        mIv.startAnimation(anim);
    }
}
