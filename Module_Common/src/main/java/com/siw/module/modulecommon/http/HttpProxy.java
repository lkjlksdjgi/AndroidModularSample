package com.siw.module.modulecommon.http;

import java.io.File;
import java.util.Map;

/**
 * Created by 童思伟 on 2017/10/9.
 */

public interface HttpProxy {

    /**
     * 同步get请求
     * @param url
     * @param callback
     */
    void getSync(String url, Callback callback);

    /**
     * 同步post请求，请求参数是键值对类型
     * @param url
     * @param params
     * @param callback
     */
    void postSync(String url, Map<String, String> params, Callback callback);

    /**
     * 同步post请求，请求参数是json类型
     * @param url
     * @param json
     * @param callback
     */
    void postSync(String url, String json, Callback callback);

    /**
     * 异步get请求
     * @param url
     * @param callback
     */
    void getAsync(String url, Callback callback);
    /**
     * 异步post请求，请求参数是键值对类型
     * @param url
     * @param params
     * @param callback
     */
    void postAsync(String url, Map<String, String> params, Callback callback);
    /**
     * 异步post请求，请求参数是json类型
     * @param url
     * @param json
     * @param callback
     */
    void postAsync(String url, String json, Callback callback);

    void uploadFile(String url, File file, String fileKey, Callback callback);

    void uploadFile(String url, File[] files, String[] fileKeys, Map<String, String> params, Callback callback);

    /***
     * 下载文件
     * @param url ：
     * @param saveDir ：保存文件的路径
     * @param callback
     */
    void download(String url, String saveDir, Callback callback);

    /**
     *
     * @param url
     * @param saveDir ：保存文件的路径
     * @param downloadFileName ：保存文件的文件名
     * @param callback
     */
    void download(String url, String saveDir, String downloadFileName, Callback callback);

}
