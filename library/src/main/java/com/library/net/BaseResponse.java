package com.library.net;

import com.library.base.SafeProGuard;

/**
 * Create by wangqingqing
 * On 2018/5/17 18:19
 * Copyright(c) 2018 极光
 * Description
 */
public class BaseResponse<T> implements SafeProGuard {
    public T data;
    public int code;
    public String msg;
}
