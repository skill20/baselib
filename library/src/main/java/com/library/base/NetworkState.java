package com.library.base;

/**
 * Create by wangqingqing
 * On 2018/5/16 14:19
 * Copyright(c) 2018 极光
 * Description
 */
public interface NetworkState {
    void showLoading();
    void showEmpty();
    void showError();
    void showContent();
}
