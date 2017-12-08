package com.siw.module.modulecommon.http;

import java.io.File;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 童思伟 on 2017/12/8.
 */

public class RetrofitUtils implements HttpProxy{


    public RetrofitUtils() {

    }

    @Override
    public void getSync(String url, Callback callback) {

    }

    @Override
    public void postSync(String url, Map<String, String> params, Callback callback) {

    }

    @Override
    public void postSync(String url, String json, Callback callback) {

    }

    @Override
    public void getAsync(String url, Callback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                //使用自定义的mGsonConverterFactory
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://apis.baidu.com/txapi/")
                .build();
        mApi = retrofit.create(APi.class);

    }

    @Override
    public void postAsync(String url, Map<String, String> params, Callback callback) {

    }

    @Override
    public void postAsync(String url, String json, Callback callback) {

    }

    @Override
    public void uploadFile(String url, File file, String fileKey, Callback callback) {

    }

    @Override
    public void uploadFile(String url, File[] files, String[] fileKeys, Map<String, String> params, Callback callback) {

    }

    @Override
    public void download(String url, String saveDir, Callback callback) {

    }

    @Override
    public void download(String url, String saveDir, String downloadFileName, Callback callback) {

    }
}
