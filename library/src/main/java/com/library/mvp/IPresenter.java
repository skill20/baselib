package com.library.mvp;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.NonNull;


/**
 * Create by wangqingqing
 * On 2018/5/17 15:58
 * Copyright(c) 2018 极光
 * Description
 */
public interface IPresenter extends LifecycleObserver {

    /**
     * 做一些初始化操作
     */
    void onStart();

    /**
     * 释放资源
     */
    void onDestroy();

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate(@NonNull LifecycleOwner owner);

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy(@NonNull LifecycleOwner owner);
}
