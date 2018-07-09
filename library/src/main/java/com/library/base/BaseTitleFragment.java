package com.library.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.library.R;


/**
 * Create by wangqingqing
 * On 2018/5/16 11:15
 * Copyright(c) 2018 极光
 * Description
 */
public abstract class BaseTitleFragment extends LazyBaseFragment implements NetworkState {


    protected View mContentView;
    private AppCompatActivity mAppCompatActivity;
    private MultipleStateLayout mStateLayout;
    private View mSubView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_title_root, container, false);
        int rid = getContentLayout();
        if (rid <= 0) {
            throw new RuntimeException(
                    "BaseTitleFragment has none valid sub content layout");
        }

        if (!supportTitle()) {
            v.findViewById(R.id.toolbar).setVisibility(View.GONE);
            v.findViewById(R.id.v_title_divider).setVisibility(View.GONE);
        }

        View view = View.inflate(mContext, rid, null);
        FrameLayout mRootLayout = (FrameLayout) v.findViewById(R.id.container);

        mStateLayout = (MultipleStateLayout) v.findViewById(R.id.state_layout);
        if (!supportNetLayout()) {
            mStateLayout.setVisibility(View.GONE);
        }

        mStateLayout.setOnErrorClickLinstener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRetry();
            }
        });

        if (mRootLayout != null) {
            mRootLayout.addView(view, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            mSubView = view;
        }

        mContentView = v;
        return v;
    }

    protected void onRetry() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAppCompatActivity = (AppCompatActivity) getActivity();
        if (supportTitle()) {
            initTopLayout(mContentView);
        }
        initContentView(mContentView);
    }

    private void initTopLayout(View v) {
        Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar);
        mAppCompatActivity.setSupportActionBar(toolbar);

        ActionBar actionBar = mAppCompatActivity.getSupportActionBar();
        if (actionBar == null) {
            throw new RuntimeException("set toolbar exception");
        }

        actionBar.setDisplayShowTitleEnabled(false);
        int navigationIcon = getNavigationIcon();
        if (navigationIcon > 0) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationIcon(navigationIcon);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAppCompatActivity.onBackPressed();
                }
            });
        }

        TextView titleView = (TextView) v.findViewById(R.id.toolbar_title);
        String title = setToolbarTitle(titleView);
        if (!TextUtils.isEmpty(title)) {
            titleView.setText(title);
        }

        TextView subTitleView = (TextView) v.findViewById(R.id.toolbar_subtitle);
        String subTitle = setToolbarSubTitle(subTitleView);
        if (!TextUtils.isEmpty(subTitle)) {
            subTitleView.setText(subTitle);
        }

        int res = getToolbarBackground();
        toolbar.setBackgroundResource(res);

        View dividerView = v.findViewById(R.id.v_title_divider);
        setTitleDividerView(dividerView);

        ImageView rightImageView = (ImageView) v.findViewById(R.id.iv_right);
        setRightImageView(rightImageView);
    }

    protected int getNavigationIcon() {
        return -1;
    }

    protected void setRightImageView(ImageView rightImageView) {

    }

    protected void setTitleDividerView(View dividerView) {

    }

    protected String setToolbarSubTitle(TextView subTitleView) {
        return null;
    }

    protected String setToolbarTitle(TextView titleView) {
        return null;
    }

    protected int getToolbarBackground() {
        return R.color.white_100;
    }

    protected boolean supportTitle() {
        return true;
    }

    protected boolean supportNetLayout() {
        return false;
    }

    protected abstract void initContentView(View v);

    protected abstract int getContentLayout();


    @Override
    public void showLoading() {
        mStateLayout.showLoading();
        mSubView.setVisibility(View.GONE);
    }

    @Override
    public void showEmpty() {
        mStateLayout.showEmpty();
    }

    @Override
    public void showError() {
        mStateLayout.showError();
    }

    @Override
    public void showContent() {
        mStateLayout.showContent();
        mSubView.setVisibility(View.VISIBLE);
    }
}
