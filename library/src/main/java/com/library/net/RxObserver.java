package com.library.net;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Create by wangqingqing
 * On 2018/5/10 13:41
 * Copyright(c) 2018 极光
 * Description
 */
public abstract class RxObserver<T> implements Observer<T>, IObserver<T> {

    private static final int SUCCESS = 60000;

    //登陆方面的错误码
    private static final int ERROR_LOGIN_INVALID = 60004;//会话无效
    private static final int ERROR_LOGIN_OVERDUE = 60024;//帐户信息已过期，重新登录

    @Override
    public void onSubscribe(Disposable d) {
        onStartRequest();
    }

    @Override
    public void onStartRequest() {

    }

    @Override
    public void onNext(T t) {
        if (t instanceof BaseResponse) {
            BaseResponse rp = (BaseResponse) t;
            int code = rp.code;
            String msg = rp.msg;

            if (code == SUCCESS) {
                onSuccess(t);
            } else {
                onError(new ErrorResponseException(code,msg));
            }
        } else {
            onSuccess(t);
        }
    }

    @Override
    public void onError(Throwable e) {
        if (e.getMessage() != null && (e.getMessage().contains("host") || e.getMessage().contains("http") || e.getMessage().contains("HTTP"))) {
            onError(-1, "服务器异常，请稍后再试！");
        } else if (e instanceof ConnectException || e instanceof SocketTimeoutException) {
            onError(-1, "当前无网络，请检查网络设置!");
        } else if (e instanceof ErrorResponseException) {
            int code = ((ErrorResponseException) e).getCode();
            if (code == ERROR_LOGIN_INVALID || code == ERROR_LOGIN_OVERDUE) {
                tokenFail();
                return;
            }
            onError(code, e.getMessage());
        } else {
            onError(-1, e.getMessage());
        }
    }

    @Override
    public void onError(int code, String s) {

    }

    private static void tokenFail() {

    }

    @Override
    public void onComplete() {

    }
}
