package com.library.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import java.io.File;

/**
 * Create by wangqingqing
 * On 2018/7/9 13:47
 * Copyright(c) 2018 极光
 * Description
 */
public interface LoaderInterface {

    /**
     * 展示图片
     *
     * @param resId
     * @param imageView
     */
    void display(int resId, ImageView imageView);

    /**
     * 展示图片
     *
     * @param url
     * @param imageView
     */
    void display(String url, ImageView imageView);

    /**
     * 展示图片，配置信息见option
     *
     * @param url
     * @param imageView
     * @param option
     */
    void display(String url, ImageView imageView, ImageLoaderOption option);

    /**
     * 获取bitmap
     *
     * @param context
     * @param url
     * @param width
     * @param height
     * @return
     */
    Bitmap loadBitmap(Context context, String url, int width, int height);

    /**
     * 释放内存
     *
     * @param context
     * @param level
     */
    void trimMemory(Context context, int level);

    /**
     * 下载图片
     *
     * @param context
     * @param url
     * @param width
     * @param height
     * @return
     */
    File downloadImg(Context context, String url, int width, int height);
}
