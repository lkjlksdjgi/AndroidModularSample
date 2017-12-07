package module;

import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.siw.module.moduleandroid.AndroidFragment;
import com.siw.module.moduleandroid.R;
import com.siw.module.modulecommon.base.BaseActivity;
import com.siw.module.modulecommon.base.BaseFragment;

public class MainActivity extends BaseActivity {

    private BaseFragment androidFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_activity_main);
        Log.e("MainActivity","MainActivity onCreate");
        androidFragment = new AndroidFragment();
        BaseFragment androidFragments = (BaseFragment) ARouter.getInstance().build("/android/AndroidFragment").navigation();
        addFragment(androidFragments,R.id.android_contain);
    }
}
