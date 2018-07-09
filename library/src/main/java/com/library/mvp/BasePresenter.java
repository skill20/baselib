package com.library.mvp;

import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;

import com.library.net.RxLifecycleUtils;
import com.library.net.RxObserver;
import com.library.net.RxSchedulers;
import com.uber.autodispose.AutoDisposeConverter;

import io.reactivex.Observable;

/**
 * Create by wangqingqing
 * On 2018/5/17 16:00
 * Copyright(c) 2018 极光
 * Description
 */
public class BasePresenter<M extends IModel, V extends IView> implements IPresenter {

    protected M mModel;
    protected V mRootView;
    private LifecycleOwner mLifecycleOwner;

    public BasePresenter(M model, V rootView) {

        if (model == null) {
            throw new NullPointerException("mode is null");
        }

        if (rootView == null) {
            throw new NullPointerException("view is null");
        }

        this.mModel = model;
        this.mRootView = rootView;
        onStart();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onDestroy() {
        mModel.onDestroy();
        mModel = null;
        mRootView = null;
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        this.mLifecycleOwner = owner;
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        onDestroy();
    }

    private <T> AutoDisposeConverter<T> bindLifecycle() {
        if (null == mLifecycleOwner)
            throw new NullPointerException("lifecycleOwner == null");
        return RxLifecycleUtils.bindLifecycle(mLifecycleOwner);
    }

    protected <T> void toSubscribe(Observable<T> o, RxObserver<T> s) {
        o.compose(RxSchedulers.<T>ioToMain()).as(this.<T>bindLifecycle()).subscribe(s);
    }
}
