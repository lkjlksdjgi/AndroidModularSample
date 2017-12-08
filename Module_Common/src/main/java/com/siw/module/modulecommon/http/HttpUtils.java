package com.siw.module.modulecommon.http;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.File;
import java.util.Map;

/**
 * Created by 童思伟 on 2017/10/9.
 */

public class HttpUtils implements HttpProxy {
    private static HttpUtils httpUtils = null;
    private static HttpProxy httpProxy = null;

    private HttpUtils() {
    }
    public static HttpUtils obtain() {
        return HttpClientHolder.httpUtils;
    }

    public static Handler getMainLooperHandler() {
        return HttpClientHolder.mainLooperHandler;
    }

    public static Gson getgson() {
        return HttpClientHolder.gson;
    }

    private static class HttpClientHolder {
        private static final HttpUtils httpUtils = new HttpUtils();
        private static final Handler mainLooperHandler = new Handler(Looper.getMainLooper());
        private static final Gson gson = new Gson();
    }

    public static void init(HttpProxy mHttpProxy) {
        httpProxy = mHttpProxy;
    }

    public void cancle() {
        httpProxy = null;
        httpUtils = null;
    }

    @Override
    public void getSync(String url, Callback callback) {
        httpProxy.getSync(url, callback);
    }

    @Override
    public void postSync(String url, Map<String, String> params, Callback callback) {
        httpProxy.postSync(url, params, callback);
    }

    @Override
    public void postSync(String url, String json, Callback callback) {
        httpProxy.postSync(url, json, callback);
    }

    @Override
    public void getAsync(String url, Callback callback) {
        httpProxy.getAsync(url, callback);
    }

    @Override
    public void postAsync(String url, Map<String, String> params, Callback callback) {
        httpProxy.postAsync(url, params, callback);
    }

    @Override
    public void postAsync(String url, String json, Callback callback) {
        httpProxy.postAsync(url, json, callback);
    }

    @Override
    public void uploadFile(String url, File file, String fileKey, Callback callback) {
        httpProxy.uploadFile(url, file, fileKey, callback);
    }

    @Override
    public void uploadFile(String url, File[] files, String[] fileKeys, Map<String, String> params, Callback callback) {
        httpProxy.uploadFile(url, files, fileKeys, params, callback);
    }

    @Override
    public void download(String url, String saveDir, Callback callback) {
        httpProxy.download(url, saveDir, callback);
    }

    @Override
    public void download(String url, String saveDir, String downloadFileName, Callback callback) {
        if (callback instanceof DownloadCallBack) {
            throw new ClassCastException("download must be used DownloadCallBack");
        }
        ((DownloadCallBack) callback).setSavePath(saveDir);
        ((DownloadCallBack) callback).setUrl(url);
        ((DownloadCallBack) callback).setDownloadFileName(downloadFileName);
        httpProxy.download(url, saveDir, downloadFileName, callback);
    }


}
