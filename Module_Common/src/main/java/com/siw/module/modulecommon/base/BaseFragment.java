package com.siw.module.modulecommon.base;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;

/**
 * Created by 童思伟 on 2017/12/7.
 */

public class BaseFragment extends Fragment {
    protected BaseActivity baseActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.baseActivity = (BaseActivity) context;
    }

    /**
     * 添加fragment
     *
     * @param fragment
     * @param frameId
     */
    protected void addFragment(BaseFragment fragment, @IdRes int frameId) {
        baseActivity.addFragment(fragment, frameId);

    }

    /**
     * 替换fragment
     *
     * @param fragment
     * @param frameId
     */
    protected void replaceFragment(BaseFragment fragment, @IdRes int frameId) {
        baseActivity.replaceFragment(fragment, frameId);
    }


    /**
     * 隐藏fragment
     *
     * @param fragment
     */
    protected void hideFragment(BaseFragment fragment) {
        baseActivity.hideFragment(fragment);
    }


    /**
     * 显示fragment
     *
     * @param fragment
     */
    protected void showFragment(BaseFragment fragment) {
        baseActivity.showFragment(fragment);
    }


    /**
     * 移除Fragment
     *
     * @param fragment
     */
    protected void removeFragment(BaseFragment fragment) {
        baseActivity.removeFragment(fragment);
    }
}
