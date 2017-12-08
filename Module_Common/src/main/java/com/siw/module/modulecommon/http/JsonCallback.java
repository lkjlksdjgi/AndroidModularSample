package com.siw.module.modulecommon.http;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;

/**
 * Created by 童思伟 on 2017/10/9.
 */

public abstract class JsonCallback<Result> implements Callback<Response> {
    private Handler handler = new Handler(Looper.getMainLooper());
    @Override
    public void onSuccess(final Response response) {
        try {
        String result = response.body().string();
        Class<Result> clazz = getClazz();
        Gson gson = new Gson();
        final Result r = gson.fromJson(result, clazz);
        handler.post(new Runnable() {
            @Override
            public void run() {
                onHttpCallbackSuccess(r);
            }
        });
        } catch (IOException e) {
            e.printStackTrace();
            onError(e.getMessage());
        }
    }

    private Class<Result> getClazz() {
        Type genericSuperclass =  getClass().getGenericSuperclass();
        Class<Result> clazz;
        if(genericSuperclass instanceof ParameterizedType){
            //参数化类型
            ParameterizedType parameterizedType= (ParameterizedType) genericSuperclass;
            //返回表示此类型实际类型参数的 Type 对象的数组
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            clazz = (Class<Result>)actualTypeArguments[0];
        }else{
            clazz= (Class<Result>)genericSuperclass;
        }
        return clazz;
    }
    /**这个是在主线程*/
    public abstract void onHttpCallbackSuccess(Result result);
}
