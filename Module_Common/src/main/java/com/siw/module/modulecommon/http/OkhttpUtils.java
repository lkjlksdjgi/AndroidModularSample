package com.siw.module.modulecommon.http;




import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 童思伟 on 2017/10/9.
 */

public class OkhttpUtils implements HttpProxy {

    private final OkHttpClient mOkHttpClient;
    private static final MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");
    public OkhttpUtils() {
//        ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(Utils.getContext()));
//        HttpsSSLUtils.SSLParams sslParams = HttpsSSLUtils.getSslSocketFactory(Utils.getContext(), R.raw.cer,STORE_PASS , STORE_ALIAS);
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
//                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
//                .hostnameVerifier(HttpsSSLUtils.getHostnameVerifier())
//                .addInterceptor(new LoggerInterceptor(null, true))
//                .cookieJar(cookieJar)
                .build();
    }

    /**
     * 生成request
     *
     * @param url
     * @param params
     * @return
     */
    private Request buildPostRequst(String url, Map<String, String> params) {
        Request request = null;
        if (params == null) {
            params = new HashMap<>();
        }
        if (params != null) {
            Set<Map.Entry<String, String>> entries = params.entrySet();
            FormBody.Builder builder = new FormBody.Builder();
            for (Map.Entry<String, String> entry : entries) {
                builder.add(entry.getKey(), entry.getValue());
            }
            request = new Request.Builder().url(url).post(builder.build()).build();
        }
        return request;
    }

    private RequestBody buildMultipartFormRequestBody(File[] files, String[] fileKeys, Map<String, String> params) {
        if (params == null) {
            params = new HashMap<String, String>();
        }
        MultipartBody.Builder builder = new MultipartBody.Builder();
        Set<Map.Entry<String, String>> entries = params.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + entry.getKey() + "\""),
                    RequestBody.create(null, entry.getValue()));
        }

        if (files == null) {
            files = new File[0];
        }
        if (fileKeys == null) {
            fileKeys = new String[0];
        }

        if (fileKeys.length != files.length) {
            throw new ArrayStoreException("fileKeys.length != files.length");
        }
        RequestBody fileBody = null;
        int length = files.length;
        for (int i = 0; i < length; i++) {
            File file = files[i];
            String fileName = file.getName();
            fileBody = RequestBody.create(MediaType.parse(guessMimeType(fileName)), file);
            builder.addPart(Headers.of("Content-Disposition",
                    "form-data; name=\"" + fileKeys[i] + "\"; fileName=\"" + fileName + "\""),
                    fileBody);
        }
        return builder.build();
    }

    private String guessMimeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String type = fileNameMap.getContentTypeFor(path);
        if (type == null) {
            type = "application/octet-stream";
        }
        return type;
    }

    private void analysisData(Call call, final Callback callback) {
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onError(e.getMessage().toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                        callback.onSuccess(response);
//                }

            }
        });

    }

    @Override
    public void getSync(String url, Callback callback) {
        try {
            final Request request = new Request.Builder().url(url).build();
            Response response = mOkHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                callback.onSuccess(response.body().toString());
            } else {
                callback.onError(response.message().toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
            callback.onError(e.getMessage());
        }
    }

    @Override
    public void postSync(String url, Map<String, String> params, Callback callback) {
        try {
            final Request request = buildPostRequst(url, params);
            Response response = mOkHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                callback.onSuccess(response.body().toString());
            } else {
                callback.onError(response.message().toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
            callback.onError(e.getMessage());
        }
    }

    @Override
    public void postSync(String url, String json, Callback callback) {
        try {
            final RequestBody requestBody = RequestBody.create(JSON_TYPE, json);
            final Request request = new Request.Builder().url(url).post(requestBody).build();
            Response response = mOkHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                callback.onSuccess(response.body().toString());
            } else {
                callback.onError(response.message().toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
            callback.onError(e.getMessage());
        }
    }

    @Override
    public void getAsync(String url, Callback callback) {
        final Request request = new Request.Builder().url(url).build();
        final Call call = mOkHttpClient.newCall(request);
        analysisData(call, callback);
    }

    @Override
    public void postAsync(String url, Map<String, String> params, final Callback callback) {
        final Call call = mOkHttpClient.newCall(buildPostRequst(url, params));
        analysisData(call, callback);
    }


    @Override
    public void postAsync(String url, String json, Callback callback) {
        final RequestBody requestBody = RequestBody.create(JSON_TYPE, json);
        final Request request = new Request.Builder().url(url).post(requestBody).build();
        analysisData(mOkHttpClient.newCall(request), callback);
    }

    @Override
    public void uploadFile(String url, File file, String fileKey, Callback callback) {
        uploadFile(url, new File[]{file}, new String[]{fileKey}, new HashMap<String, String>(), callback);
    }

    @Override
    public void uploadFile(String url, File[] files, String[] fileKeys, Map<String, String> params, Callback callback) {
//        final RequestBody requestBody = buildMultipartFormRequestBody(files, fileKeys, params);
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), files[0]);
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
//        builder.addFormDataPart("image",)

//        final Request request = new Request.Builder().url(url).post(requestBody).build();
    }

    @Override
    public void download(final String url,final String saveDir,final Callback callback) {
        download(url,saveDir,"",callback);
    }

    @Override
    public void download(final String url, final String saveDir, final String fileName, final Callback callback) {
        Request request=new Request.Builder().url(url).build();
        mOkHttpClient.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onError(e.getMessage());
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callback.onSuccess(response);
            }
        });
    }
}
