package com.library.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;

/**
 * Create by wangqingqing
 * On 2018/5/16 11:14
 * Copyright(c) 2018 极光
 * Description 占坑 所有的Fragment都需要继承这个
 * 推荐使用一个Acitivity 多个fragment的方式来写界面
 */
public class BaseFragment extends Fragment {

    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }
}
