package com.library.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.library.utils.StatusHelper;

/**
 * Create by wangqingqing
 * On 2018/5/16 14:23
 * Copyright(c) 2018 极光
 * Description 占坑 所有的Acitivity都需要继承这个
 */
public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (supportLightMode()) {
            StatusHelper.statusBarLightMode(this);
        }
    }

    protected boolean supportLightMode() {
        return true;
    }
}
