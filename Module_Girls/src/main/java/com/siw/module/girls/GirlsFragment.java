package com.siw.module.girls;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.siw.module.girls.modle.GirlsBean;
import com.siw.module.modulecommon.base.BaseFragment;
import com.siw.module.modulecommon.base.BaseView;
import com.siw.module.modulecommon.http.HttpUtils;
import com.siw.module.modulecommon.http.JsonCallback;

import java.util.List;

/**
 * Created by 童思伟 on 2017/12/7.
 */
@Route(path = "/girls/girlsFragment")
public class GirlsFragment extends BaseFragment implements BaseView<GirlsBean> {

    private RecyclerView recycler_view;
    private LinearLayoutManager mLayoutManager;
    private MyAdapter myAdapter;
    private List<GirlsBean.ResultsBean> results;

    public GirlsFragment() {
    }

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.girls_fragment, null);
        initData();
        initView();
        return view;
    }

    private void initData() {
        GrilsPresenter grilsPresenter = new GrilsPresenter(this);
        grilsPresenter.getData();
    }

    private void initView() {
        recycler_view = (RecyclerView) view.findViewById(R.id.recycler_view);
        myAdapter = new MyAdapter();
        mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
//        mLayoutManager = new GridLayoutManager(context, 2, OrientationHelper.VERTICAL, false);
//        mLayoutManager = new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL);
        // 设置布局管理器
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setAdapter(myAdapter);
    }

    @Override
    public void getData(GirlsBean girlsBean) {
        results = girlsBean.getResults();
        if (myAdapter != null) {
            myAdapter.notifyDataSetChanged();
        }
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = View.inflate(context, R.layout.grils_fragment_item, null);
            View view = LayoutInflater.from(context).inflate(R.layout.grils_fragment_item, null);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Glide.with(context).load(results.get(position).getUrl()).placeholder(R.mipmap.ic_launcher).into(holder.girls_iv_item);
        }

        @Override
        public int getItemCount() {
            if (results == null) {
                return 0;
            }
            return results.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView girls_iv_item;

            public ViewHolder(View itemView) {
                super(itemView);
                girls_iv_item = itemView.findViewById(R.id.girls_iv_item);
            }
        }


    }

    public String data = "{\n" +
            "  \"error\": false, \n" +
            "  \"results\": [\n" +
            "    {\n" +
            "      \"_id\": \"5a273d40421aa90fef203585\", \n" +
            "      \"createdAt\": \"2017-12-06T08:43:44.386Z\", \n" +
            "      \"desc\": \"12-6\", \n" +
            "      \"publishedAt\": \"2017-12-06T08:49:34.303Z\", \n" +
            "      \"source\": \"chrome\", \n" +
            "      \"type\": \"\\u798f\\u5229\", \n" +
            "      \"url\": \"http://7xi8d6.com1.z0.glb.clouddn.com/20171206084331_wylXWG_misafighting_6_12_2017_8_43_16_390.jpeg\", \n" +
            "      \"used\": true, \n" +
            "      \"who\": \"daimajia\"\n" +
            "    }, \n" +
            "    {\n" +
            "      \"_id\": \"5a20ace0421aa90fe50c024c\", \n" +
            "      \"createdAt\": \"2017-12-01T09:14:08.63Z\", \n" +
            "      \"desc\": \"12-1\", \n" +
            "      \"publishedAt\": \"2017-12-05T08:48:31.384Z\", \n" +
            "      \"source\": \"chrome\", \n" +
            "      \"type\": \"\\u798f\\u5229\", \n" +
            "      \"url\": \"http://7xi8d6.com1.z0.glb.clouddn.com/20171201091356_OPqmuO_kanna399_1_12_2017_9_13_42_126.jpeg\", \n" +
            "      \"used\": true, \n" +
            "      \"who\": \"daimajia\"\n" +
            "    }, \n" +
            "    {\n" +
            "      \"_id\": \"5a1ad043421aa90fe725366c\", \n" +
            "      \"createdAt\": \"2017-11-26T22:31:31.363Z\", \n" +
            "      \"desc\": \"11-26\", \n" +
            "      \"publishedAt\": \"2017-11-30T13:11:10.665Z\", \n" +
            "      \"source\": \"chrome\", \n" +
            "      \"type\": \"\\u798f\\u5229\", \n" +
            "      \"url\": \"http://7xi8d6.com1.z0.glb.clouddn.com/20171126223118_jeCYtY_chayexiaoguo_apple_26_11_2017_22_30_59_409.jpeg\", \n" +
            "      \"used\": true, \n" +
            "      \"who\": \"\\u4ee3\\u7801\\u5bb6\"\n" +
            "    }, \n" +
            "    {\n" +
            "      \"_id\": \"5a16171d421aa90fef203553\", \n" +
            "      \"createdAt\": \"2017-11-23T08:32:29.546Z\", \n" +
            "      \"desc\": \"11-23\", \n" +
            "      \"publishedAt\": \"2017-11-24T11:08:03.624Z\", \n" +
            "      \"source\": \"chrome\", \n" +
            "      \"type\": \"\\u798f\\u5229\", \n" +
            "      \"url\": \"http://7xi8d6.com1.z0.glb.clouddn.com/20171123083218_5mhRLg_sakura.gun_23_11_2017_8_32_9_312.jpeg\", \n" +
            "      \"used\": true, \n" +
            "      \"who\": \"daimajia\"\n" +
            "    }, \n" +
            "    {\n" +
            "      \"_id\": \"5a121895421aa90fe50c021e\", \n" +
            "      \"createdAt\": \"2017-11-20T07:49:41.797Z\", \n" +
            "      \"desc\": \"2017-11-20\", \n" +
            "      \"publishedAt\": \"2017-11-20T12:42:06.454Z\", \n" +
            "      \"source\": \"chrome\", \n" +
            "      \"type\": \"\\u798f\\u5229\", \n" +
            "      \"url\": \"http://7xi8d6.com1.z0.glb.clouddn.com/20171120074925_ZXDh6l_joanne_722_20_11_2017_7_49_16_336.jpeg\", \n" +
            "      \"used\": true, \n" +
            "      \"who\": \"daimajia\"\n" +
            "    }, \n" +
            "    {\n" +
            "      \"_id\": \"5a0e4a0d421aa90fe7253643\", \n" +
            "      \"createdAt\": \"2017-11-17T10:31:41.155Z\", \n" +
            "      \"desc\": \"11-17\", \n" +
            "      \"publishedAt\": \"2017-11-17T12:39:48.189Z\", \n" +
            "      \"source\": \"chrome\", \n" +
            "      \"type\": \"\\u798f\\u5229\", \n" +
            "      \"url\": \"http://7xi8d6.com1.z0.glb.clouddn.com/2017-11-17-22794158_128707347832045_9158114204975104000_n.jpg\", \n" +
            "      \"used\": true, \n" +
            "      \"who\": \"\\u4ee3\\u7801\\u5bb6\"\n" +
            "    }, \n" +
            "    {\n" +
            "      \"_id\": \"5a0d0c97421aa90fe2f02c60\", \n" +
            "      \"createdAt\": \"2017-11-16T11:57:11.4Z\", \n" +
            "      \"desc\": \"11-16\", \n" +
            "      \"publishedAt\": \"2017-11-16T12:01:05.619Z\", \n" +
            "      \"source\": \"chrome\", \n" +
            "      \"type\": \"\\u798f\\u5229\", \n" +
            "      \"url\": \"http://7xi8d6.com1.z0.glb.clouddn.com/20171116115656_vnsrab_Screenshot.jpeg\", \n" +
            "      \"used\": true, \n" +
            "      \"who\": \"\\u4ee3\\u7801\\u5bb6\"\n" +
            "    }, \n" +
            "    {\n" +
            "      \"_id\": \"5a0a5141421aa90fef203525\", \n" +
            "      \"createdAt\": \"2017-11-14T10:13:21.137Z\", \n" +
            "      \"desc\": \"11-14\", \n" +
            "      \"publishedAt\": \"2017-11-14T10:43:36.180Z\", \n" +
            "      \"source\": \"chrome\", \n" +
            "      \"type\": \"\\u798f\\u5229\", \n" +
            "      \"url\": \"http://7xi8d6.com1.z0.glb.clouddn.com/20171114101305_NIAzCK_rakukoo_14_11_2017_10_12_58_703.jpeg\", \n" +
            "      \"used\": true, \n" +
            "      \"who\": \"daimajia\"\n" +
            "    }, \n" +
            "    {\n" +
            "      \"_id\": \"5a08ea7b421aa90fe7253628\", \n" +
            "      \"createdAt\": \"2017-11-13T08:42:35.306Z\", \n" +
            "      \"desc\": \"11-13\", \n" +
            "      \"publishedAt\": \"2017-11-13T12:10:58.643Z\", \n" +
            "      \"source\": \"chrome\", \n" +
            "      \"type\": \"\\u798f\\u5229\", \n" +
            "      \"url\": \"http://7xi8d6.com1.z0.glb.clouddn.com/20171113084220_LuJgqv_sakura.gun_13_11_2017_8_42_12_311.jpeg\", \n" +
            "      \"used\": true, \n" +
            "      \"who\": \"daimajia\"\n" +
            "    }, \n" +
            "    {\n" +
            "      \"_id\": \"5a03b502421aa90fe7253618\", \n" +
            "      \"createdAt\": \"2017-11-09T09:53:06.802Z\", \n" +
            "      \"desc\": \"11-9\", \n" +
            "      \"publishedAt\": \"2017-11-10T08:10:02.838Z\", \n" +
            "      \"source\": \"chrome\", \n" +
            "      \"type\": \"\\u798f\\u5229\", \n" +
            "      \"url\": \"http://7xi8d6.com1.z0.glb.clouddn.com/20171109095254_dOw5qh_bluenamchu_9_11_2017_9_52_47_256.jpeg\", \n" +
            "      \"used\": true, \n" +
            "      \"who\": \"daimajia\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";
}
