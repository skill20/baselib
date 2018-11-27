package com.kksearch.state;

import android.view.View;

/**
 * Create by wangqingqing
 * On 2018/11/27 15:42
 * Copyright(c) 2018 极光
 * Description
 */
public interface OnStatusChildClickListener {
    /**
     * 空数据布局子 View 被点击
     *
     * @param view 被点击的 View
     */
    void onEmptyChildClick(View view);

    /**
     * 出错布局子 View 被点击
     *
     * @param view 被点击的 View
     */
    void onErrorChildClick(View view);

    /**
     * 自定义状态布局布局子 View 被点击
     *
     * @param view 被点击的 View
     */
    void onCustomerChildClick(View view);
}
