package com.library.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.library.R;


/**
 * Create by wangqingqing
 * On 2018/5/16 11:20
 * Copyright(c) 2018 极光
 * Description 多状态的View封装
 */
public class MultipleStateLayout extends FrameLayout {


    private static final int NULL_RESOURCE_ID = -1;

    private static final int STATUS_CONTENT = 0x00;
    private static final int STATUS_LOADING = 0x01;
    private static final int STATUS_EMPTY = 0x02;
    private static final int STATUS_ERROR = 0x03;
    private static final int STATUS_NO_NETWORK = 0x04;

    private View mEmptyView;
    private View mErrorView;
    private View mLoadingView;
    private View mNoNetworkView;

    private View mEmptyRetryView;
    private View mErrorRetryView;
    private View mNoNetworkRetryView;

    private int mEmptyViewResId;
    private int mErrorViewResId;
    private int mLoadingViewResId;
    private int mNoNetworkViewResId;
    private int mViewStatus;

    private LayoutInflater mInflater;
    private ViewGroup.LayoutParams mLayoutParams;
    private OnClickListener errorListener;

    public MultipleStateLayout(@NonNull Context context) {
        this(context, null);
    }

    public MultipleStateLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultipleStateLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MultipleStateLayout);

        mEmptyViewResId = a.getResourceId(R.styleable.MultipleStateLayout_emptyView, R.layout.empty_view);
        mErrorViewResId = a.getResourceId(R.styleable.MultipleStateLayout_errorView, R.layout.error_view);
        mLoadingViewResId = a.getResourceId(R.styleable.MultipleStateLayout_loadingView, R.layout.loading_view);
        mNoNetworkViewResId = a.getResourceId(R.styleable.MultipleStateLayout_noNetworkView, R.layout.no_network_view);
        a.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mInflater = LayoutInflater.from(getContext());
        mLayoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

    }

    public void setEmptyViewResId(int mEmptyViewResId) {
        this.mEmptyViewResId = mEmptyViewResId;
    }

    public void setErrorViewResId(int mErrorViewResId) {
        this.mErrorViewResId = mErrorViewResId;
    }

    public void setLoadingViewResId(int mLoadingViewResId) {
        this.mLoadingViewResId = mLoadingViewResId;
    }

    public void setNoNetworkViewResId(int mNoNetworkViewResId) {
        this.mNoNetworkViewResId = mNoNetworkViewResId;
    }

    public final void showLoading() {
        mViewStatus = STATUS_LOADING;

        if (mLoadingView == null) {
            mLoadingView = mInflater.inflate(mLoadingViewResId, null);
            addView(mLoadingView, 0, mLayoutParams);
        }

        showViewByStatus(mViewStatus);
    }

    public final void showContent() {
        mViewStatus = STATUS_CONTENT;
        showViewByStatus(mViewStatus);
    }

    public final void showEmpty() {
        mViewStatus = STATUS_EMPTY;
        if (null == mEmptyView) {
            mEmptyView = mInflater.inflate(mEmptyViewResId, null);
//            mEmptyRetryView = mEmptyView.findViewById(R.id.empty_retry_view);
//            if (null != mOnRetryClickListener && null != mEmptyRetryView) {
//                mEmptyRetryView.setOnClickListener(mOnRetryClickListener);
//            }
            addView(mEmptyView, 0, mLayoutParams);
        }
        showViewByStatus(mViewStatus);
    }

    public final void showNoNetwork() {
        mViewStatus = STATUS_NO_NETWORK;
        if (null == mNoNetworkView) {
            mNoNetworkView = mInflater.inflate(mNoNetworkViewResId, null);
//            mNoNetworkRetryView = mNoNetworkView.findViewById(R.id.no_network_retry_view);
//            if (null != mOnRetryClickListener && null != mNoNetworkRetryView) {
//                mNoNetworkRetryView.setOnClickListener(mOnRetryClickListener);
//            }
            addView(mNoNetworkView, 0, mLayoutParams);
        }
        showViewByStatus(mViewStatus);
    }

    public final void showError() {
        mViewStatus = STATUS_ERROR;
        if (null == mErrorView) {
            mErrorView = mInflater.inflate(mErrorViewResId, null);
            mErrorRetryView = mErrorView.findViewById(R.id.tv_retry);
            if (null != errorListener && null != mErrorRetryView) {
                mErrorRetryView.setOnClickListener(errorListener);
            }

            addView(mErrorView, 0, mLayoutParams);
        }
        showViewByStatus(mViewStatus);
    }

    public void setOnErrorClickLinstener(OnClickListener linstener) {
        this.errorListener = linstener;
    }

    private void showViewByStatus(int status) {
        if (null != mLoadingView) {
            mLoadingView.setVisibility(status == STATUS_LOADING ? View.VISIBLE : View.GONE);
        }
        if (null != mEmptyView) {
            mEmptyView.setVisibility(status == STATUS_EMPTY ? View.VISIBLE : View.GONE);
        }
        if (null != mErrorView) {
            mErrorView.setVisibility(status == STATUS_ERROR ? View.VISIBLE : View.GONE);
        }
        if (null != mNoNetworkView) {
            mNoNetworkView.setVisibility(status == STATUS_NO_NETWORK ? View.VISIBLE : View.GONE);
        }

        setVisibility(status == STATUS_CONTENT ? GONE : VISIBLE);

    }

}
