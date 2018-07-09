package com.library.app;

import android.app.Application;
import android.text.TextUtils;

import com.library.BuildConfig;
import com.library.crash.ACUncaughtExceptionHandler;
import com.library.fs.DirType;
import com.library.imageloader.ImageLoader;
import com.library.log.Logger;
import com.library.log.NLog;
import com.library.utils.AndroidUtils;
import com.library.utils.NetworkHelper;

/**
 * Create by wangqingqing
 * On 2018/7/9 14:25
 * Copyright(c) 2018 极光
 * Description
 */
public abstract class BaseApplication extends Application {

    private String mProcessName;

    @Override
    public void onCreate() {
        super.onCreate();
        mProcessName = AndroidUtils.getCurrentProcessName(this);
        init();
    }

    private void init() {
        GlobeContext.initInstance(this);
        getMainProcessName();
        initNLog();
        initNetwork();
        initCrashReport();
        initCommon();
    }

    protected abstract String getMainProcessName();

    private void initNLog() {
        if (!BuildConfig.LOG_DEBUG) {
            NLog.setDebug(false, Logger.VERBOSE);
            return;
        }

        String path = GlobeContext.getDirectoryPath(DirType.crash);
        // 抓取崩溃日志
        ACUncaughtExceptionHandler handler =
                new ACUncaughtExceptionHandler(this, path, BuildConfig.ENABLE_DUMP_OOM);
        handler.registerForExceptionHandler();

        NLog.setDebug(true, Logger.VERBOSE);

        //日志写入文件
        if (BuildConfig.LOG_OFFLINE) {
            String loggerPath = GlobeContext.getDirectoryPath(DirType.log);
            NLog.trace(Logger.TRACE_OFFLINE, loggerPath);
        } else {
            NLog.trace(Logger.TRACE_REALTIME, null);
        }
    }

    private void initNetwork() {
        NetworkHelper.sharedHelper().registerNetworkSensor(this);
    }

    protected void initCrashReport() {

    }

    private void initCommon() {
        //主进程才执行的代码
        if (TextUtils.equals(mProcessName, getMainProcessName())) {
            ActivityLifecycleLogger activityLifecycleLogger =
                    new ActivityLifecycleLogger(new CuteLifecycleDelegate());
            registerActivityLifecycleCallbacks(activityLifecycleLogger);
            initOther();
        }
    }

    protected void initOther() {

    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        NLog.v("GlideGlobalConfig", "onTrimMemory level: %d", level);
        super.onTrimMemory(level);
        if (getMainProcessName().equals(mProcessName)) {
            //释放图片资源
            ImageLoader.getInstance().trimMemory(this, level);
        }
    }
}
