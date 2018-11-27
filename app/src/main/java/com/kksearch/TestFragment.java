package com.kksearch;

import android.view.View;

/**
 * Create by wangqingqing
 * On 2018/11/27 17:33
 * Copyright(c) 2018 极光
 * Description
 */
public class TestFragment extends BaseTitleFragment {
    @Override
    protected int getContentLayout() {
        return R.layout.fragment_test;
    }

    @Override
    protected void parseArgs() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected boolean supportTitle() {
        return true;
    }

    @Override
    protected boolean supportNetLayout() {
        return true;
    }

    @Override
    protected void findViewById(View v) {
        showLoading();
    }
}
