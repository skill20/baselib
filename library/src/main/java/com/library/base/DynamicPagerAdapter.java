package com.library.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by wangqingqing
 * On 2018/6/7 15:07
 * Copyright(c) 2018 极光
 * Description 动态fragment adapter
 */
public class DynamicPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragmentList;
    private List<String> mTitleList;

    public DynamicPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> titleList) {
        super(fm);
        mFragmentList = fragmentList != null ? fragmentList : new ArrayList<Fragment>();
        mTitleList = titleList != null ? titleList : new ArrayList<String>();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
