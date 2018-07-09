package com.library.imageloader;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.GlideModule;
import com.library.app.ServiceContext;
import com.library.fs.DirType;

/**
 * Create by wangqingqing
 * On 2018/7/9 13:57
 * Copyright(c) 2018 极光
 * Description
 */
public class GlideGlobalConfig implements GlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        String path = ServiceContext.getDirectoryPath(DirType.image);
        builder.setDiskCache(new DiskLruCacheFactory(path, "", Integer.MAX_VALUE));
        builder.setMemoryCache(GlideImageLoader.getInstance().memoryCache);
        builder.setBitmapPool(GlideImageLoader.getInstance().bitmapPool);
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
