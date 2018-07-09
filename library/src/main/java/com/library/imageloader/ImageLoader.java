package com.library.imageloader;

/**
 * Create by wangqingqing
 * On 2018/7/9 14:02
 * Copyright(c) 2018 极光
 * Description
 */
public class ImageLoader {

    private static final String TYPE_GLIDE = "glide";
    private static final String TYPE_PICASSO = "picasso";
    private static final String DEFAULT_TYPE = TYPE_GLIDE;

    private ImageLoader() {
        throw new UnsupportedOperationException("can't create ImageLoaderHelper instance");
    }

    public static LoaderInterface getInstance() {
        return realImageLoader(DEFAULT_TYPE);
    }

    private static LoaderInterface realImageLoader(String defaultType) {
        return GlideImageLoader.getInstance();
    }
}
