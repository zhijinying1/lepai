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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhijinying.lepaiyizu.R;
import com.zhijinying.lepaiyizu.activity.DetailsActivity;
import com.zhijinying.lepaiyizu.adapter.JXAdapter;
import com.zhijinying.lepaiyizu.bean.JXDataInfo;
import com.zhijinying.lepaiyizu.utils.JSONDownloadUtils;
import com.zhijinying.lepaiyizu.utils.Path;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JXFragment extends Fragment implements AdapterView.OnItemClickListener {

    private PullToRefreshListView listView = null;
    private View v = null;
    private ArrayList<JXDataInfo> list = new ArrayList<>();
    private List<JXDataInfo> newList = new ArrayList<>();
    private JXAdapter adapter = null;
    private int index = 1;

    private List<ImageView> imageList = null;
    private ViewPager vp = null;
    private int current = 1;
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
        View view = inflater.inflate(R.layout.listview, container, false);
        vp = (ViewPager) view.findViewById(R.id.viewPager);
        listView = (PullToRefreshListView) view.findViewById(R.id.ptrListview);
        listView.setOnItemClickListener(this);

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

        JSONDownloadUtils.getJson(Path.getJXPath(index), new JSONDownloadUtils.JSONListener() {
                    @Override
                    public void successJson(String json) {

                        try {

                            JSONObject object = new JSONObject(json);
                            JSONArray array = object.getJSONArray("160120");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject jsonObj = array.getJSONObject(i);

                                String title = jsonObj.optString("title");
                                String pic_url = jsonObj.optString("pic_url");
                                String date = jsonObj.optString("date");
                                String web_url = jsonObj.optString("web_url");

                                JXDataInfo jxDataInfo1 = new JXDataInfo(title, pic_url, date, web_url);

                                list.add(jxDataInfo1);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter = new JXAdapter(getActivity(), list);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void errorJson() {

                        Toast.makeText(getContext(), "网络异常", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {

                list.clear();

                JSONDownloadUtils.getJson(Path.getJXPath(index), new JSONDownloadUtils.JSONListener() {
                    @Override
                    public void successJson(String json) {

                        try {
                            JSONObject object = new JSONObject(json);
                            JSONArray array = object.getJSONArray("160120");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject obj = array.getJSONObject(i);

                                String title = obj.optString("title");
                                String pic_url = obj.optString("pic_url");
                                String date = obj.optString("date");
                                String web_url = obj.optString("web_url");

                                JXDataInfo jxDataInfo = new JXDataInfo(title, pic_url, date, web_url);

                                newList.add(jxDataInfo);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        list.addAll(newList);
                        adapter.notifyDataSetChanged();
                        listView.onRefreshComplete();
                    }

                    @Override
                    public void errorJson() {

                        Toast.makeText(getContext(), "网络异常", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        listView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {

                list.clear();

                JSONDownloadUtils.getJson(Path.getJXPath(++index), new JSONDownloadUtils.JSONListener() {
                    @Override
                    public void successJson(String json) {

                        try {
                            JSONObject object = new JSONObject(json);
                            JSONArray array = object.getJSONArray("160120");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject obj = array.getJSONObject(i);

                                String title = obj.optString("title");
                                String pic_url = obj.optString("pic_url");
                                String date = obj.optString("date");
                                String web_url = obj.optString("web_url");

                                JXDataInfo jxDataInfo = new JXDataInfo(title, pic_url, date, web_url);

                                newList.add(jxDataInfo);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        list.addAll(newList);
                        adapter.notifyDataSetChanged();
                        listView.onRefreshComplete();
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
