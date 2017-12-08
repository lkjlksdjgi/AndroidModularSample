package com.siw.module.girls.modle;

import com.siw.module.modulecommon.http.HttpUtils;
import com.siw.module.modulecommon.http.JsonCallback;

/**
 * Created by 童思伟 on 2017/12/8.
 */

public class GirlsBeanModle implements GirlsModleInterface {
    @Override
    public void loadData(final GirlsModleInterfaceListener girlsModleInterfaceListener) {
        HttpUtils.obtain().getAsync("http://gank.io/api/data/福利/10/1", new JsonCallback<GirlsBean>() {
            @Override
            public void onHttpCallbackSuccess(GirlsBean girlsBean) {

                girlsModleInterfaceListener.onSuccess(girlsBean);
            }
            @Override
            public void onError(String error) {
                girlsModleInterfaceListener.onError(error);
            }
        });
    }
}
