package com.kksearch.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.kksearch.MainActivity;
import com.library.base.BaseActivity;

/**
 * Create by wangqingqing
 * On 2018/7/10 10:12
 * Copyright(c) 2018 极光
 * Description
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getBaseContext(),MainActivity.class));
                finish();
            }
        }, 200);
    }

    @Override
    public void onBackPressed() {

    }
}
