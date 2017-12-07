package com.siw.module.modulecommon.base;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by 童思伟 on 2017/12/7.
 *
 */

public class BaseActivity extends AppCompatActivity {

    protected void replaceFragment(BaseFragment fragment, @IdRes int frameId) {
        if(fragment == null){
            throw  new NullPointerException("The replacement  fragment is empty");
        }
        getSupportFragmentManager().beginTransaction()
                .replace(frameId, fragment, fragment.getClass().getSimpleName())
                .commitAllowingStateLoss();
    }

    protected void addFragment(BaseFragment fragment, @IdRes int frameId) {
        if(fragment == null){
            throw  new NullPointerException("The added  fragment is empty");
        }
        getSupportFragmentManager().beginTransaction()
                .add(frameId, fragment, fragment.getClass().getSimpleName())
                .commitAllowingStateLoss();
    }

    protected void hideFragment(BaseFragment fragment) {
        if(fragment == null){
            throw  new NullPointerException("The added  fragment is empty");
        }
        getSupportFragmentManager().beginTransaction()
                .hide(fragment)
                .commitAllowingStateLoss();
    }

    protected void showFragment(BaseFragment fragment) {
        if(fragment == null){
            throw  new NullPointerException("The show  fragment is empty");
        }
        getSupportFragmentManager().beginTransaction()
                .show(fragment)
                .commitAllowingStateLoss();

    }

    protected void removeFragment(BaseFragment fragment) {
        if(fragment == null){
            throw  new NullPointerException("The removed  fragment is empty");
        }
        getSupportFragmentManager().beginTransaction()
                .remove(fragment)
                .commitAllowingStateLoss();

    }

}
