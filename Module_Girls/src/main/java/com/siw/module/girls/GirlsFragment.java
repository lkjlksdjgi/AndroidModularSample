package com.siw.module.girls;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.siw.module.modulecommon.base.BaseFragment;

/**
 * Created by 童思伟 on 2017/12/7.
 *
 */
@Route(path = "/girls/girlsFragment")
public class GirlsFragment extends BaseFragment {
    public GirlsFragment() {
    }
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.girls_activity_main, null);
        return view;
    }
}
