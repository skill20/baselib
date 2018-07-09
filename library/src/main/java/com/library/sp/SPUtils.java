package com.library.sp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.library.app.GlobeContext;

/**
 * Create by wangqingqing
 * On 2018/6/7 16:48
 * Copyright(c) 2018 极光
 * Description
 */
public class SPUtils {

    @SuppressLint("StaticFieldLeak")
    private static Context sContext = GlobeContext.get().getApplicationContext();

    private SPUtils() {
    }

    private static final String FILE_NAME = "config";
    private static final int SP_MODE = Context.MODE_APPEND;

    public static void setString(String name, String value) {
        SharedPreferences.Editor editor = getSp().edit();
        editor.putString(name, value);
        editor.apply();
    }

    @SuppressLint("WrongConstant")
    private static SharedPreferences getSp() {
        return sContext.getSharedPreferences(FILE_NAME, SP_MODE);
    }

    public static String getString(String name, String defValue) {
        return getSp().getString(name, defValue);
    }

    public static float getFloat(String name, float defValue) {
        return getSp().getFloat(name, defValue);
    }

    public static void setFloat(String name, float value) {
        SharedPreferences.Editor editor = getSp().edit();
        editor.putFloat(name, value);
        editor.apply();
    }

    public static void setInt(String name, int value) {
        SharedPreferences.Editor editor = getSp().edit();
        editor.putInt(name, value);
        editor.apply();
    }

    public static int getInt(String name, int defValue) {
        return getSp().getInt(name, defValue);
    }

    public static void setLong(String name, long value) {
        SharedPreferences.Editor editor = getSp().edit();
        editor.putLong(name, value);
        editor.apply();
    }

    public static long getLong(String name, long defValue) {
        return getSp().getLong(name, defValue);
    }

    public static void setBoolean(String name, boolean value) {
        SharedPreferences.Editor editor = getSp().edit();
        editor.putBoolean(name, value);
        editor.apply();
    }

    public static boolean getBoolean(String name, boolean defValue) {
        return getSp().getBoolean(name, defValue);
    }

    public static void remove(String key) {
        SharedPreferences.Editor editor = getSp().edit();
        editor.remove(key);
        editor.apply();
    }
}
