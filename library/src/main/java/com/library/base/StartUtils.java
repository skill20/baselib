package com.library.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Create by wangqingqing
 * On 2018/5/16 15:00
 * Copyright(c) 2018 极光
 * Description
 */
public class StartUtils {

    private StartUtils() {
        throw new UnsupportedOperationException("can't create StartUtils");
    }

    public static void startActivityById(@NonNull Context context, String key, @Nullable Bundle bundle) {
        Intent intent = startActivityIntent(context, key, bundle);
        if (!(context instanceof Activity)) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else {
            ((Activity) context).startActivityForResult(intent, 0);
        }
    }

    public static Intent startActivityIntent(@NonNull Context context, String key, @Nullable Bundle bundle) {
        Intent intent = new Intent(context, FragmentContainerActivity.class);
        intent.putExtra(FragmentContainerActivity.FRAGMENT_ID, key);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        return intent;
    }
}
