package com.siw.module.modulecommon.utils;

import android.content.Context;

/**
 * Created by 童思伟 on 2017/12/1.
 */

public class CommonUtils {
    private static Context mContext;
    public static void init(Context context){
        mContext = context;
    }

    public static Context getContext(){
        checkNull();
        return mContext;
    }



    private static void checkNull(){
        if(mContext == null){
            throw new NullPointerException("context is null , Please initialize context first");
        }
    }
}
