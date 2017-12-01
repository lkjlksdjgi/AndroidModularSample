package com.siw.module.modulecommon.base;

import android.app.Application;

import com.siw.module.modulecommon.utils.CommonUtils;

/**
 * Created by 童思伟 on 2017/12/1.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CommonUtils.init(this);

    }
}
