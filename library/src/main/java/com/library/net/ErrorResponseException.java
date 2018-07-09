package com.library.net;

/**
 * Create by wangqingqing
 * On 2018/6/7 10:31
 * Copyright(c) 2018 极光
 * Description
 */
public class ErrorResponseException extends Exception {

    private int code;

    public ErrorResponseException(int code,String message) {
        super(message);
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}
