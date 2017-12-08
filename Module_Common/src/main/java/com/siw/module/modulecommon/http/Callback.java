package com.siw.module.modulecommon.http;

/**
 * Created by 童思伟 on 2017/10/9.
 */

public interface Callback<T> {
    void onSuccess(T result);
    void onError(String error);
}
