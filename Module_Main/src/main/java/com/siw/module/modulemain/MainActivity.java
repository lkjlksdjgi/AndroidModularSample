package com.siw.module.modulemain;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.siw.module.modulecommon.base.BaseActivity;
import com.siw.module.modulecommon.base.BaseFragment;

import java.util.ArrayList;

@Route(path = "/Main/MainActivity")
public class MainActivity extends BaseActivity {

    private ArrayList<BaseFragment> fragments;
    private LinearLayout main_fragment_tab;
    private FrameLayout main_fragment_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_main);
        initFragments();
        initView();
        changeFragment(0);
    }

    private void initFragments() {
        BaseFragment girlsFragment = (BaseFragment) ARouter.getInstance().build("/girls/girlsFragment").navigation();
        BaseFragment androidFragment = (BaseFragment) ARouter.getInstance().build("/android/AndroidFragment").navigation();
        BaseFragment iosFragment = (BaseFragment) ARouter.getInstance().build("/ios/IOSFragments").navigation();
        fragments = new ArrayList<>();
        fragments.add(girlsFragment);
        fragments.add(androidFragment);
        fragments.add(iosFragment);
    }

    private void initView() {
        main_fragment_container = (FrameLayout) findViewById(R.id.main_fragment_container);
        main_fragment_tab =  (LinearLayout) findViewById(R.id.main_fragment_tab);

        for(int i = 0;i < main_fragment_tab.getChildCount() ; i++){
            TextView tab  = (TextView) main_fragment_tab.getChildAt(i);
            tab.setOnClickListener(onClickListener);
        }
    }
    private View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int index=main_fragment_tab.indexOfChild(view);
            changeTab(index);
            changeFragment(index);
        }
    };

    private void changeTab(int index) {
        int childCount = main_fragment_tab.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if(i==index){
                setEnable(main_fragment_tab.getChildAt(i),false);
            }else{
                setEnable(main_fragment_tab.getChildAt(i),true);
            }
        }
    }
    private void setEnable(View item, boolean b) {
        item.setEnabled(b);
        if(item instanceof ViewGroup)
        {
            int childCount = ((ViewGroup) item).getChildCount();
            for (int i = 0; i < childCount; i++) {
                setEnable(((ViewGroup) item).getChildAt(i),b);
            }
        }
    }
    private void changeFragment(int index) {
        BaseFragment fragment = fragments.get(index);
        replaceFragment(fragment,R.id.main_fragment_container);
    }
}
