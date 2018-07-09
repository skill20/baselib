package com.library.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

import com.library.R;


/**
 * Create by wangqingqing
 * On 2018/5/16 14:25
 * Copyright(c) 2018 极光
 * Description
 */
public class FragmentContainerActivity extends BaseActivity {

    public static final String FRAGMENT_ID = "fragment_id";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);
        parseIntent();
    }

    private void parseIntent() {
        Intent intent = getIntent();
        String fragmentId = intent.getStringExtra(FRAGMENT_ID);
        if (TextUtils.isEmpty(fragmentId)) {
            throw new IllegalArgumentException("fragment id is empty or null");
        }

        Fragment fragment = FragmentFactory.getFragmentById(fragmentId);
        replaceFragment(fragment);
    }

    private void replaceFragment(Fragment targetFragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.fragment_container, targetFragment);
        ft.commit();
    }
}
