package com.kksearch.state;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Create by wangqingqing
 * On 2018/11/27 15:37
 * Copyright(c) 2018 极光
 * Description
 */
public class StatusLayoutManager {

    private View contentLayout;

    @LayoutRes
    private int loadingLayoutID;
    private View loadingLayout;
    @IdRes
    private int emptyClickViewId;
    @LayoutRes
    private int emptyLayoutID;
    private View emptyLayout;
    @IdRes
    private int errorClickViewId;
    @LayoutRes
    private int errorLayoutID;
    private View errorLayout;

    private LayoutInflater inflater;
    private ReplaceLayoutHelper replaceLayoutHelper;
    private OnStatusChildClickListener onStatusChildClickListener;

    private StatusLayoutManager(Builder builder) {
        this.contentLayout = builder.contentLayout;
        this.loadingLayoutID = builder.loadingLayoutID;
        this.loadingLayout = builder.loadingLayout;
        this.emptyClickViewId = builder.emptyClickViewId;
        this.emptyLayoutID = builder.emptyLayoutID;
        this.emptyLayout = builder.emptyLayout;
        this.errorClickViewId = builder.errorClickViewId;
        this.errorLayoutID = builder.errorLayoutID;
        this.errorLayout = builder.errorLayout;
        this.onStatusChildClickListener = builder.onStatusChildClickListener;
        this.replaceLayoutHelper = new ReplaceLayoutHelper(contentLayout);
    }

    private View inflate(@LayoutRes int resource) {
        if (inflater == null) {
            inflater = LayoutInflater.from(contentLayout.getContext());
        }
        return inflater.inflate(resource, null);
    }

    ///////////////////////////////////////////
    /////////////////原有布局////////////////////
    ///////////////////////////////////////////

    /**
     * 显示原有布局
     */
    public void showSuccessLayout() {
        replaceLayoutHelper.restoreLayout();
    }

    ///////////////////////////////////////////
    ////////////////加载中布局///////////////////
    ///////////////////////////////////////////

    /**
     * 创建加载中布局
     */
    private void createLoadingLayout() {
        if (loadingLayout == null) {
            loadingLayout = inflate(loadingLayoutID);
        }
    }

    /**
     * 获取加载中布局
     *
     * @return 加载中布局
     */
    public View getLoadingLayout() {
        createLoadingLayout();
        return loadingLayout;
    }

    /**
     * 显示加载中布局
     */
    public void showLoadingLayout() {
        createLoadingLayout();
        replaceLayoutHelper.showStatusLayout(loadingLayout);
    }

    ///////////////////////////////////////////
    ////////////////空数据布局///////////////////
    ///////////////////////////////////////////

    /**
     * 创建空数据布局
     */
    private void createEmptyLayout() {
        if (emptyLayout == null) {
            emptyLayout = inflate(emptyLayoutID);
        }

        // 点击事件回调
        View view = emptyLayout.findViewById(emptyClickViewId);
        if (view != null && onStatusChildClickListener != null) {
            // 设置点击按钮点击时事件回调
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onStatusChildClickListener.onEmptyChildClick(view);
                }
            });
        }
    }

    /**
     * 获取空数据布局
     *
     * @return 空数据布局
     */
    public View getEmptyLayout() {
        createEmptyLayout();
        return emptyLayout;
    }

    /**
     * 显示空数据布局
     */
    public void showEmptyLayout() {
        createEmptyLayout();
        replaceLayoutHelper.showStatusLayout(emptyLayout);
    }

    ///////////////////////////////////////////
    /////////////////出错布局////////////////////
    ///////////////////////////////////////////

    /**
     * 创建出错布局
     */
    private void createErrorLayout() {
        if (errorLayout == null) {
            errorLayout = inflate(errorLayoutID);
        }

        View view = errorLayout.findViewById(errorClickViewId);
        if (view != null && onStatusChildClickListener != null) {
            // 设置点击按钮点击时事件回调
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onStatusChildClickListener.onErrorChildClick(view);
                }
            });
        }
    }

    /**
     * 获取出错布局
     *
     * @return 出错布局
     */
    public View getErrorLayout() {
        createErrorLayout();
        return errorLayout;
    }

    /**
     * 显示出错布局
     */
    public void showErrorLayout() {
        createErrorLayout();
        replaceLayoutHelper.showStatusLayout(errorLayout);
    }

    ///////////////////////////////////////////
    ////////////////自定义布局///////////////////
    ///////////////////////////////////////////

    /**
     * 显示自定义状态布局
     *
     * @param customLayout 自定义布局
     */
    public void showCustomLayout(@NonNull View customLayout) {
        replaceLayoutHelper.showStatusLayout(customLayout);
    }

    /**
     * 显示自定义状态布局
     *
     * @param customLayoutID 自定义状态布局 ID
     * @return 通过 customLayoutID 生成的 View
     */
    public View showCustomLayout(@LayoutRes int customLayoutID) {
        View customerView = inflate(customLayoutID);
        showCustomLayout(customerView);
        return customerView;
    }

    /**
     * 显示自定义状态布局
     *
     * @param customLayout 自定义布局
     * @param clickViewID  可点击 View ID
     */
    public void showCustomLayout(@NonNull View customLayout, @IdRes int... clickViewID) {
        replaceLayoutHelper.showStatusLayout(customLayout);
        if (onStatusChildClickListener == null) {
            return;
        }

        for (int aClickViewID : clickViewID) {
            View clickView = customLayout.findViewById(aClickViewID);
            if (clickView == null) {
                return;
            }

            // 设置点击按钮点击时事件回调
            clickView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onStatusChildClickListener.onCustomerChildClick(view);
                }
            });
        }
    }

    /**
     * 显示自定义状态布局
     *
     * @param customLayoutID 自定义布局 ID
     * @param clickViewID    点击按钮 ID
     */
    public View showCustomLayout(@LayoutRes int customLayoutID, @IdRes int... clickViewID) {
        View customLayout = inflate(customLayoutID);
        showCustomLayout(customLayout, clickViewID);
        return customLayout;
    }

    public static final class Builder {

        private View contentLayout;
        @LayoutRes
        private int loadingLayoutID;
        private View loadingLayout;
        @IdRes
        private int emptyClickViewId;
        @LayoutRes
        private int emptyLayoutID;
        private View emptyLayout;
        @IdRes
        private int errorClickViewId;
        @LayoutRes
        private int errorLayoutID;
        private View errorLayout;

        private OnStatusChildClickListener onStatusChildClickListener;

        /**
         * 创建状态布局 Build 对象
         *
         * @param contentLayout 原有布局，内容布局
         */
        public Builder(@NonNull View contentLayout) {
            this.contentLayout = contentLayout;
        }

        ///////////////////////////////////////////
        ////////////////加载中布局///////////////////
        ///////////////////////////////////////////

        /**
         * 设置加载中布局
         *
         * @param loadingLayoutID 加载中布局 ID
         * @return 状态布局 Build 对象
         */
        public Builder setLoadingLayout(@LayoutRes int loadingLayoutID) {
            this.loadingLayoutID = loadingLayoutID;
            return this;
        }

        /**
         * 设置加载中布局
         *
         * @param loadingLayout 加载中布局
         * @return 状态布局 Build 对象
         */
        public Builder setLoadingLayout(@NonNull View loadingLayout) {
            this.loadingLayout = loadingLayout;
            return this;
        }


        ///////////////////////////////////////////
        ////////////////空数据布局///////////////////
        ///////////////////////////////////////////

        /**
         * 设置空数据布局
         *
         * @param emptyLayoutResId 空数据布局 ID
         * @return 状态布局 Build 对象
         */
        public Builder setEmptyLayout(@LayoutRes int emptyLayoutResId) {
            this.emptyLayoutID = emptyLayoutResId;
            return this;
        }

        /**
         * 设置空数据布局
         *
         * @param emptyLayout 空数据布局
         * @return 状态布局 Build 对象
         */
        public Builder setEmptyLayout(@NonNull View emptyLayout) {
            this.emptyLayout = emptyLayout;
            return this;
        }

        /**
         * 设置空数据布局点击按钮 ID
         *
         * @param emptyClickViewResId 空数据布局点击按钮 ID
         * @return 状态布局 Build 对象
         */
        public Builder setEmptyClickViewID(@IdRes int emptyClickViewResId) {
            this.emptyClickViewId = emptyClickViewResId;
            return this;
        }

        ///////////////////////////////////////////
        /////////////////出错布局////////////////////
        ///////////////////////////////////////////

        /**
         * 设置出错布局
         *
         * @param errorLayoutResId 出错布局 ID
         * @return 状态布局 Build 对象
         */
        public Builder setErrorLayout(@LayoutRes int errorLayoutResId) {
            this.errorLayoutID = errorLayoutResId;
            return this;
        }

        /**
         * 设置出错布局
         *
         * @param errorLayout 出错布局
         * @return 状态布局 Build 对象
         */
        public Builder setErrorLayout(@NonNull View errorLayout) {
            this.errorLayout = errorLayout;
            return this;
        }

        /**
         * 设置出错布局点击按钮 ID
         *
         * @param errorClickViewResId 出错布局点击按钮 ID
         * @return 状态布局 Build 对象
         */
        public Builder setErrorClickViewID(@IdRes int errorClickViewResId) {
            this.errorClickViewId = errorClickViewResId;
            return this;
        }

        /**
         * 设置点击事件监听器
         *
         * @param listener 点击事件监听器
         * @return 状态布局 Build 对象
         */
        public Builder setOnStatusChildClickListener(OnStatusChildClickListener listener) {
            this.onStatusChildClickListener = listener;
            return this;
        }

        /**
         * 创建状态布局管理器
         *
         * @return 状态布局管理器
         */
        @NonNull
        public StatusLayoutManager build() {
            return new StatusLayoutManager(this);
        }

    }
}
