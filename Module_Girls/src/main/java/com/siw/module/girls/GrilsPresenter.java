package com.siw.module.girls;

import com.siw.module.girls.modle.GirlsBean;
import com.siw.module.girls.modle.GirlsBeanModle;
import com.siw.module.girls.modle.GirlsModleInterface;
import com.siw.module.modulecommon.base.BaseView;

/**
 * Created by 童思伟 on 2017/12/8.
 */

public class GrilsPresenter {

    private final GirlsBeanModle girlsBeanModle;
    private  BaseView baseView;

    public GrilsPresenter(BaseView baseView) {
        this.baseView = baseView;
        girlsBeanModle = new GirlsBeanModle();
    }

    public void getData(){
        girlsBeanModle.loadData(new GirlsModleInterface.GirlsModleInterfaceListener<GirlsBean>() {
            @Override
            public void onSuccess(GirlsBean girlsBean) {
                baseView.getData(girlsBean);
            }

            @Override
            public void onError(String string) {

            }
        });
    }
}
