package com.library.net;

import android.content.Context;

import com.google.gson.Gson;
import com.library.base.SafeProGuard;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Create by wangqingqing
 * On 2018/5/18 15:09
 * Copyright(c) 2018 极光
 * Description
 */
public class HttpManager {

    private final static int CONN_TIMEOUT = 40;
    private final static int READ_TIMEOUT = 40;
    private final static int WRITE_TIMEOUT = 40;

    private volatile static HttpManager sHttpManager;
    private Retrofit mRetrofit;

    private HttpManager(Context context, String baseUrl, boolean debug, List<Interceptor> interceptors) {

        Context tempContext = context.getApplicationContext();
        Cache cache = buildCache(tempContext);


        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(CONN_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .cache(cache)
                .addInterceptor(new CacheInterceptor())
                .addNetworkInterceptor(new CacheInterceptor());

        if (interceptors != null) {
            int size = interceptors.size();
            for (int i = 0; i < size; i++) {
                builder.addInterceptor(interceptors.get(i));
            }
        }

        OkHttpClient okHttpClient = builder.build();

        mRetrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }

    private Cache buildCache(Context context) {
        File cacheDir = context.getCacheDir();
        File file = new File(cacheDir, "net_cache");
        return new Cache(file, 10 * 1024 * 1024);
    }

    public static HttpManager getInstance() {
        checkInitialize();
        return sHttpManager;
    }

    /**
     * 检测是否调用初始化方法
     */
    private static void checkInitialize() {
        if (sHttpManager == null) {
            throw new ExceptionInInitializerError("请先在全局Application中初始化 init！");
        }
    }

    /**
     * Application 初始化
     *
     * @param context
     * @param baseUrl
     * @param debug
     */
    public static void init(Context context, String baseUrl, boolean debug, List<Interceptor> interceptors) {
        sHttpManager = new HttpManager(context, baseUrl, debug, interceptors);
    }

    public <T> T createInterface(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }


    public <T extends SafeProGuard> RequestBody createBody(T t) {
        return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(t));
    }
}
