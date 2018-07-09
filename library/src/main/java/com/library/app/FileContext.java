package com.library.app;

import android.content.Context;

import com.library.fs.DirType;
import com.library.fs.Directory;
import com.library.fs.DirectoryContext;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Create by wangqingqing
 * On 2018/7/9 14:26
 * Copyright(c) 2018 极光
 * Description
 */
public class FileContext extends DirectoryContext {
    private static final long ONE_DAY_MS = 1000 * 60 * 60 * 24L;

    FileContext(Context context, String rootPath) {
        super(context, rootPath);
    }

    @Override
    protected Collection<Directory> initDirectories() {
        ArrayList<Directory> children = new ArrayList<>();
        children.add(newDirectory(DirType.log));
        children.add(newDirectory(DirType.crash));
        children.add(newDirectory(DirType.image));
        children.add(newDirectory(DirType.cache));
        return children;
    }

    private Directory newDirectory(DirType type) {
        Directory child = new Directory(type.name(), null);
        child.setType(type.value());
        if (type.equals(DirType.cache)) {
            child.setCache(true);
            child.setExpiredTime(ONE_DAY_MS);
        }

        return child;
    }
}

