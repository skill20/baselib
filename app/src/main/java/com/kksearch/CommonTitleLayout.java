package com.kksearch;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Create by wangqingqing
 * On 2018/11/27 16:02
 * Copyright(c) 2018 极光
 * Description
 */
public class CommonTitleLayout extends FrameLayout {

    private String titleName;
    private TextView titleTv;
    private String subTitleName;
    private TextView subTitleTv;
    private Drawable rightDrawable;
    private ImageView rightIv;
    private Toolbar toolbar;

    public CommonTitleLayout(Context context) {
        this(context, null);
    }

    public CommonTitleLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommonTitleLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.common_titlebar_layout, this);
        initAttr(attrs);
        initView();
    }

    private void initAttr(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CommonTitleLayout);
        titleName = typedArray.getString(R.styleable.CommonTitleLayout_title);
        subTitleName = typedArray.getString(R.styleable.CommonTitleLayout_sub_title);
        rightDrawable = typedArray.getDrawable(R.styleable.CommonTitleLayout_right_drawable);

        typedArray.recycle();
    }

    private void initView() {
        titleTv = findViewById(R.id.toolbar_title);
        subTitleTv = findViewById(R.id.toolbar_subtitle);
        rightIv = findViewById(R.id.tool_right_iv);
        toolbar = findViewById(R.id.tool_bar);
        setTitleName(titleName);
        setSubTitleName(subTitleName);

        if (rightDrawable != null) {
            rightIv.setImageDrawable(rightDrawable);
        }
    }


    /**
     * 设置标题
     *
     * @param name
     */
    public void setTitleName(String name) {
        if (!TextUtils.isEmpty(name)) {
            titleTv.setText(name);
        }
    }

    public Toolbar getToolbar(){
        return toolbar;
    }

    public TextView getTitleTextView() {
        return titleTv;
    }

    public TextView getSubTitleTextView() {
        return subTitleTv;
    }

    public ImageView getRightImageView() {
        return rightIv;
    }

    public void setSubTitleName(String name) {
        if (!TextUtils.isEmpty(name)) {
            subTitleTv.setText(name);
        }
    }

    public void setRightDrawable(Drawable drawable) {
        if (drawable != null) {
            rightIv.setImageDrawable(drawable);
        }
    }
}

