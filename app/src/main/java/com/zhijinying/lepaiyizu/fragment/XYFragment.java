package com.zhijinying.lepaiyizu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.zhijinying.lepaiyizu.R;
import com.zhijinying.lepaiyizu.activity.DetailsActivity;
import com.zhijinying.lepaiyizu.adapter.ZXAdapter;
import com.zhijinying.lepaiyizu.bean.ZXDataInfo;
import com.zhijinying.lepaiyizu.utils.JSONDownloadUtils;
import com.zhijinying.lepaiyizu.utils.Path;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.zhijinying.lepaiyizu.R.id.viewPager;

public class XYFragment extends Fragment implements AdapterView.OnItemClickListener {

    private PullToRefreshGridView gridView = null;
    private View v = null;
    private ArrayList<ZXDataInfo> list = new ArrayList<>();
    private List<ZXDataInfo> newList = new ArrayList<>();
    private ZXAdapter adapter;
    private int index = 1;
    private int current = 0;

    private List<ImageView> imageList = null;
    private ViewPager vp = null;
    private int[] imgs = new int[]{R.drawable.jx, R.drawable.jx2, R.drawable.jx3};

    private android.os.Handler handler = new android.os.Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    sendEmptyMessageDelayed(0, 3000);
                    vp.setCurrentItem((current++) % imgs.length);
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        imageList = new ArrayList<>();
        View view = inflater.inflate(R.layout.head_gridview, container, false);
        vp = (ViewPager) view.findViewById(viewPager);
        gridView = (PullToRefreshGridView) view.findViewById(R.id.ptrGridview);

        for (int i = 0; i < imgs.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(params);
            imageView.setImageResource(imgs[i]);
            imageList.add(imageView);
        }

        MyAdapter myAdapter = new MyAdapter();
        vp.setAdapter(myAdapter);
        handler.sendEmptyMessage(0);
        gridView.setOnItemClickListener(this);

        JSONDownloadUtils.getJson(Path.getXYPath(190, index), new JSONDownloadUtils.JSONListener() {
            @Override
            public void successJson(String json) {

                try {

                    JSONObject object = new JSONObject(json);
                    JSONArray array = object.getJSONArray("list");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObj = array.getJSONObject(i);

                        String title = jsonObj.optString("title");
                        String pic_url = jsonObj.optString("pic_url");
                        String web_url = jsonObj.optString("web_url");

                        ZXDataInfo oneDataInfo = new ZXDataInfo(title, pic_url, web_url);

                        list.add(oneDataInfo);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter = new ZXAdapter(list);
                gridView.setAdapter(adapter);
            }

            @Override
            public void errorJson() {

                Toast.makeText(getContext(), "网络异常", Toast.LENGTH_SHORT).show();
            }
        });

        gridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {

                JSONDownloadUtils.getJson(Path.getXYPath(190, index), new JSONDownloadUtils.JSONListener() {
                    @Override
                    public void successJson(String json) {
                        try {
                            JSONObject object = new JSONObject(json);
                            JSONArray array = object.getJSONArray("list");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject obj = array.getJSONObject(i);

                                String title = obj.optString("title");
                                String pic_url = obj.optString("pic_url");
                                String web_url = obj.optString("web_url");

                                ZXDataInfo oneDataInfo = new ZXDataInfo(title, pic_url, web_url);


                                newList.add(oneDataInfo);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        list.addAll(newList);
                        adapter.notifyDataSetChanged();
                        gridView.onRefreshComplete();
                    }

                    @Override
                    public void errorJson() {

                        Toast.makeText(getContext(), "网络异常", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();

                JSONDownloadUtils.getJson(Path.getXYPath(190, index), new JSONDownloadUtils.JSONListener() {
                    @Override
                    public void successJson(String json) {
                        try {
                            JSONObject object = new JSONObject(json);
                            JSONArray array = object.getJSONArray("list");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject obj = array.getJSONObject(i);

                                String title = obj.optString("title");
                                String pic_url = obj.optString("pic_url");
                                String web_url = obj.optString("web_url");

                                ZXDataInfo oneDataInfo = new ZXDataInfo(title, pic_url, web_url);

                                newList.add(oneDataInfo);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        list.addAll(newList);
                        adapter.notifyDataSetChanged();
                        gridView.onRefreshComplete();
                    }

                    @Override
                    public void errorJson() {

                        Toast.makeText(getContext(), "网络异常", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        gridView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {

                JSONDownloadUtils.getJson(Path.getXYPath(190, ++index), new JSONDownloadUtils.JSONListener() {
                    @Override
                    public void successJson(String json) {

                        try {
                            JSONObject object = new JSONObject(json);
                            JSONArray array = object.getJSONArray("list");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject obj = array.getJSONObject(i);

                                String title = obj.optString("title");
                                String pic_url = obj.optString("pic_url");
                                String web_url = obj.optString("web_url");

                                ZXDataInfo oneDataInfo = new ZXDataInfo(title, pic_url, web_url);

                                newList.add(oneDataInfo);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        list.addAll(newList);
                        adapter.notifyDataSetChanged();
                        gridView.onRefreshComplete();
                    }

                    @Override
                    public void errorJson() {

                        Toast.makeText(getContext(), "网络异常", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        return view;
    }

    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            ViewGroup parent = (ViewGroup) imageList.get(position).getParent();
            if (parent != null) {
                parent.removeAllViews();
            }
            container.addView(imageList.get(position));
            return imageList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView(imageList.get(position));
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("title", list.get(position).getTitle());
        intent.putExtra("web_url", list.get(position).getWeb_url());
        startActivity(intent);
    }
}
