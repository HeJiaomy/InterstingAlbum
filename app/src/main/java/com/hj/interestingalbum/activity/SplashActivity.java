package com.hj.interestingalbum.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.hj.interestingalbum.R;
import com.hj.interestingalbum.utils.PrefUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import steelkiwi.com.library.DotsLoaderView;

public class SplashActivity extends Activity {

    @BindView(R.id.activity_splash_dotsLoaderView)
    DotsLoaderView dotsLoaderView;
    boolean isFirstEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        dotsLoaderView.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent();
                intent.setClass(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);

//        isFirstEnter();
    }

    public void isFirstEnter() {
        final Intent intent = new Intent();
        isFirstEnter = PrefUtils.getPrefBoolean(this, "splash", "isFirstEnter");
        if (isFirstEnter) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    intent.setClass(SplashActivity.this, MainActivity.class);
                    PrefUtils.setPrefBoolean(SplashActivity.this, "splash", "isFirstEnter", true); //第一次进入
                    finish();
                }
            }, 3000);
        } else {
            intent.setClass(SplashActivity.this, MainActivity.class);
            finish();
        }
        startActivity(intent);
    }
}
