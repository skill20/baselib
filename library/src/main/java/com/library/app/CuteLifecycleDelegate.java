package com.library.app;

import android.content.Context;

import com.library.utils.ToastUtils;

/**
 * Create by wangqingqing
 * On 2018/7/9 14:27
 * Copyright(c) 2018 极光
 * Description
 */
public class CuteLifecycleDelegate implements ActivityLifecycleLogger.ApplicationLifecycleDelegate {
    @Override
    public int backgroundTickDelay() {
        return 0;
    }

    @Override
    public void willEnterBackground(Context context) {

    }

    @Override
    public void enterBackground(Context context) {
        ToastUtils.isActive = false;
    }

    @Override
    public void becomeActiveFromSuspend(Context context) {

    }

    @Override
    public void enterForeground(Context context) {
        ToastUtils.isActive = true;
    }
}
