package com.library.app;

import android.content.Context;

import com.library.fs.DirectoryManager;

/**
 * Create by wangqingqing
 * On 2018/7/9 14:26
 * Copyright(c) 2018 极光
 * Description
 */
public class GlobeContext extends ServiceContext {
    private static final String SD_ROOT = "kk_search";
    private static final String SERVICE_NAME = "dir";
    private static boolean CONTEXT_INIT_SUCCESS = false;

    private DirectoryManager mDirectoryManager = null;

    public GlobeContext(Context context) {
        super(context);
    }

    public static boolean initInstance(Context context) {
        if (!CONTEXT_INIT_SUCCESS || _instance == null) {
            GlobeContext gcContext = new GlobeContext(context);

            _instance = gcContext;
            CONTEXT_INIT_SUCCESS = gcContext.init();
            return CONTEXT_INIT_SUCCESS;
        }
        return true;
    }

    private boolean init() {
        DirectoryManager manager = new DirectoryManager(new FileContext(getApplicationContext(), SD_ROOT));
        boolean ret = manager.buildAndClean();
        if (!ret) {
            return false;
        }
        registerSystemObject(SERVICE_NAME, manager);
        mDirectoryManager = manager;
        return true;
    }

    @Override
    public DirectoryManager getDirectoryManager() {
        return mDirectoryManager;
    }
}
