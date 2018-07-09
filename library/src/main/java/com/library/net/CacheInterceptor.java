package com.library.net;

import android.text.TextUtils;

import com.library.utils.NetworkHelper;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Create by wangqingqing
 * On 2018/5/18 17:12
 * Copyright(c) 2018 极光
 * Description
 */
public class CacheInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        if (!NetworkHelper.sharedHelper().isNetworkAvailable()) {
            // 没网强制从缓存读取（适用addInterceptor(new CacheInterceptor())
            //                .addNetworkInterceptor(new CacheInterceptor());）

            CacheControl control;

            //cache配置是否需要缓存
            String cacheHeader = request.header("cache");
            if (TextUtils.equals(cacheHeader, "false")) {
                control = CacheControl.FORCE_NETWORK;
            } else {
                control = CacheControl.FORCE_CACHE;
            }

            request = request.newBuilder()
                    .cacheControl(control)
                    .build();
        }

        Response response = chain.proceed(request);
        Response responseLatest;

        if (!NetworkHelper.sharedHelper().isNetworkAvailable()) {
            // 有网时候读接口上的@Headers里的配置
            String cacheControl = request.cacheControl().toString();

            if (TextUtils.isEmpty(cacheControl)) {
                cacheControl = "public ,max-age=" + 60;
            }

            responseLatest = response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", cacheControl)
                    .build();
        } else {
            int maxStale = 60 * 60 * 24 * 365; // 没网失效时间(addNetworkInterceptor(new CacheInterceptor())
            responseLatest = response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }
        return responseLatest;

    }
}