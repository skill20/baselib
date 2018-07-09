package com.library.base;

import java.util.HashMap;

/**
 * Create by wangqingqing
 * On 2018/5/16 14:26
 * Copyright(c) 2018 极光
 * Description
 */
public class FragmentFactory {


    private static final HashMap<String, BaseFragment> sFragmentMap = new HashMap<>();

    private FragmentFactory() {
        throw new UnsupportedOperationException("can't create FragmentFactory instance");
    }

    public static BaseFragment getFragmentById(String fragmentId) {
        return sFragmentMap.remove(fragmentId);
    }

    public static void register(String key, BaseFragment fragment) {
        sFragmentMap.put(key, fragment);
    }
}
