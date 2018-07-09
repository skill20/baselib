package com.library.action;

import android.support.annotation.NonNull;

import java.util.ArrayDeque;

/**
 * Create by wangqingqing
 * On 2017/12/15 11:34
 * Copyright(c) 2017
 * Description 事件校验管理器
 */
public class CheckManager {

    private final ArrayDeque<Checkable> mCheckQueue = new ArrayDeque<>();

    private Action mAction;

    private Checkable mLastCheckable;

    private CheckManager() {

    }

    public CheckManager register(@NonNull Action action, @NonNull Checkable... checkable) {
        this.mAction = action;
        this.mLastCheckable = null;
        add(checkable);
        return this;
    }

    private void add(@NonNull Checkable... args) {
        mCheckQueue.clear();
        for (Checkable checkable : args) {
            boolean checked = checkable.isChecked();
            if (!checked) {
                mCheckQueue.add(checkable);
            }
        }
    }

    public void onCheck() {

        boolean un = true;
        do {
            if (mAction == null) {
                break;
            }

            if (mLastCheckable != null && !mLastCheckable.isChecked()) {
                break;
            }

            int size = mCheckQueue.size();
            if (size == 0) {
                mAction.doAction();
                break;
            }

            Checkable checkable = mCheckQueue.poll();
            checkable.doCheck();
            mLastCheckable = checkable;

            un = false;

        } while (false);

        if (un) {
            unregister();
        }
    }

    private void unregister() {
        mCheckQueue.clear();
        mAction = null;
        mLastCheckable = null;
    }

    private static class Holder {
        private static final CheckManager INSTANCE = new CheckManager();
    }

    public static CheckManager getInstance() {
        return Holder.INSTANCE;
    }
}
