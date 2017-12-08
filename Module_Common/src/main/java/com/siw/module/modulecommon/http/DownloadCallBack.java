package com.siw.module.modulecommon.http;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Response;

/**
 * Created by 童思伟 on 2017/11/20.
 */

public abstract class DownloadCallBack implements Callback<Response>{

    public abstract void onDownloadingProcess(int process,long contentLength,long currentLength);
    public abstract void onDownLoadingSuccess(String result);

    private final int ON_DOWN_SUCCESS = 0;
    private final int ON_DOWN_PROGRESS = 1;
    private final int ON_DOWN_ERROR = 2;

    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case ON_DOWN_PROGRESS:
                    Bundle data = msg.getData();
                    int progress = data.getInt("progress");
                    long total = data.getLong("total");
                    long sum = data.getLong("sum");
                    onDownloadingProcess(progress,total,sum);
                    break;
                case ON_DOWN_SUCCESS:
                    onDownLoadingSuccess("下载完成");
                    break;
                case ON_DOWN_ERROR:
                    Exception e= (Exception) msg.obj;
                    onError(e.getMessage());
                    break;
            }
        }
    };
    private String savePath;
    private String url;
    private String downloadFileName;

    public String getDownloadFileName() {
        return downloadFileName;
    }

    public void setDownloadFileName(String downloadFileName) {
        this.downloadFileName = downloadFileName;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    private Bundle bundle = new Bundle();
    @Override
    public void onSuccess( final Response response) {
        InputStream is=null;
        byte[] buf=new byte[2048];
        int len=0;
        FileOutputStream fos=null;
        //储存下载文件的目录
        String savePath=isExistDir(getSavePath());
        try{
            is=response.body().byteStream();
            long total=response.body().contentLength();
            String downloadFileName = getDownloadFileName();
            if(TextUtils.isEmpty(downloadFileName)){
                downloadFileName = getNameFromUrl(url);
            }
            File file=new File(savePath,downloadFileName);
            fos=new FileOutputStream(file);
            long sum=0;
            while((len = is.read(buf))!=-1){
                fos.write(buf,0,len);
                sum+=len;
                int progress=(int)(sum*1.0f/total*100);
                //下载中
                Message message = Message.obtain();
                bundle.putInt("progress",progress);
                bundle.putLong("total",total);
                bundle.putLong("sum",sum);
                message.setData(bundle);
                message.what = ON_DOWN_PROGRESS;
                handler.sendMessage(message);
            }
            fos.flush();
            //下载完成
            Message.obtain(handler,ON_DOWN_SUCCESS);
            is.close();
            fos.close();
        }catch (Exception e){
            Message.obtain(handler,ON_DOWN_ERROR,e);
        }finally{
            try{
                if(is!=null) {
                    is.close();
                }
                if(fos!=null){
                    fos.close();
                }
            }catch (IOException e){
                Message.obtain(handler,ON_DOWN_ERROR,e);
            }
        }
    }
    private String getNameFromUrl(String url) {
        return url.substring(url.lastIndexOf("/")+1);
    }
    private String isExistDir(String saveDir) {
        try {
            File downloadFile=new File(saveDir);
            if(!downloadFile.mkdirs()){
                downloadFile.createNewFile();
            }
            String savePath=downloadFile.getAbsolutePath();
            return savePath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
