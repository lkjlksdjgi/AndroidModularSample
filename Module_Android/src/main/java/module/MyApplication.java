package module;


import android.util.Log;

import com.siw.module.modulecommon.base.BaseApplication;

/**
 * Created by 童思伟 on 2017/12/1.
 */

public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("MyApplication ","Module Android MyApplication onCreate");
    }
}
