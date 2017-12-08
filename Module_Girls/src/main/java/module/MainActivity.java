package module;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.siw.module.girls.R;
import com.siw.module.modulecommon.base.BaseActivity;
import com.siw.module.modulecommon.base.BaseFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.girls_activity_main);
        BaseFragment androidFragments = (BaseFragment) ARouter.getInstance().build("/girls/girlsFragment").navigation();
        addFragment(androidFragments,R.id.girls_contain);
    }
}
