package com.siw.module.girls.modle;

/**
 * Created by 童思伟 on 2017/12/8.
 */

public interface GirlsModleInterface {

    void loadData(GirlsModleInterfaceListener girlsModleInterfaceListener);

    public  interface GirlsModleInterfaceListener<T>{
        void  onSuccess(T t);
        void onError(String string);
    }
}
