package com.kksearch;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kksearch.state.StatusLayoutManager;
import com.library.base.BaseFragment;
import com.library.base.NetworkState;

/**
 * Create by wangqingqing
 * On 2018/11/27 16:09
 * Copyright(c) 2018 极光
 * Description
 */
public abstract class BaseTitleFragment extends BaseFragment implements NetworkState {

    private Activity mActivity;
    private View mRootView;
    private CommonTitleLayout mCommonBar;
    private View mToolBarDivider;
    private StatusLayoutManager statusLayoutManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parseArgs();
        View v = inflater.inflate(R.layout.fragment_base, container, false);
        int rid = getContentLayout();
        if (rid <= 0) {
            throw new RuntimeException(
                    "BaseTitleFragment has none valid sub content layout");
        }

        View mStatusBar = v.findViewById(R.id.status_bar);
        setStatusBar(mStatusBar);

        mCommonBar = v.findViewById(R.id.common_bar);
        mToolBarDivider = v.findViewById(R.id.tool_bar_divider);

        if (!supportTitle()) {
            mCommonBar.setVisibility(View.GONE);
            mToolBarDivider.setVisibility(View.GONE);
        }

        View view = View.inflate(mContext, rid, null);
        LinearLayout mRootLayout = v.findViewById(R.id.layout_root);
        mRootLayout.addView(view, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        if (supportNetLayout()) {
            statusLayoutManager =
                    new StatusLayoutManager.Builder(view)
                            .setLoadingLayout(R.layout.state_loading)
                            .build();
        }

        mRootView = v;
        findViewById(v);
        initView();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = getActivity();
        if (supportTitle()) {
            initTopLayout(mRootView);
        }
    }

    private void initTopLayout(View v) {
        Toolbar toolbar = mCommonBar.getToolbar();

        int navigationIcon = getNavigationIcon();
        if (navigationIcon > 0) {
            toolbar.setNavigationIcon(navigationIcon);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mActivity.onBackPressed();
                }
            });
        }

        TextView titleView = mCommonBar.getTitleTextView();
        String title = setToolbarTitle(titleView);
        if (!TextUtils.isEmpty(title)) {
            titleView.setText(title);
        }

        TextView subTitleView = mCommonBar.getSubTitleTextView();
        String subTitle = setToolbarSubTitle(subTitleView);
        if (!TextUtils.isEmpty(subTitle)) {
            subTitleView.setText(subTitle);
        }

        int res = getToolbarBackground();
        mCommonBar.setBackgroundResource(res);

        setTitleDividerView(mToolBarDivider);

        ImageView rightImageView = mCommonBar.getRightImageView();
        setToolbarRightImageView(rightImageView);
    }

    protected void setTitleDividerView(View dividerView) {

    }

    protected void setToolbarRightImageView(ImageView rightImageView) {

    }

    protected int getToolbarBackground() {
        return 0;
    }

    protected String setToolbarSubTitle(TextView subTitleView) {
        return null;
    }

    protected String setToolbarTitle(TextView titleView) {
        return null;
    }

    protected int getNavigationIcon() {
        return R.drawable.back_icon;
    }


    protected boolean supportNetLayout() {
        return false;
    }

    protected boolean supportTitle() {
        return false;
    }

    protected void setStatusBar(View mStatusBar) {

    }

    @Override
    public void showLoading() {
        statusLayoutManager.showLoadingLayout();
    }

    @Override
    public void showEmpty() {
        statusLayoutManager.showEmptyLayout();
    }

    @Override
    public void showError() {
        statusLayoutManager.showErrorLayout();
    }

    @Override
    public void showContent() {
        statusLayoutManager.showSuccessLayout();
    }

    protected abstract int getContentLayout();

    protected abstract void parseArgs();

    protected abstract void initView();

    protected abstract void findViewById(View v);
}
