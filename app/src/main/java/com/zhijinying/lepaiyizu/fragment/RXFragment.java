package com.zhijinying.lepaiyizu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.zhijinying.lepaiyizu.R;
import com.zhijinying.lepaiyizu.activity.DetailsActivity;
import com.zhijinying.lepaiyizu.adapter.TSAdapter;
import com.zhijinying.lepaiyizu.bean.TSDataInfo;
import com.zhijinying.lepaiyizu.utils.JSONDownloadUtils;
import com.zhijinying.lepaiyizu.utils.Path;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 16-9-5.
 */
public class RXFragment extends Fragment implements AdapterView.OnItemClickListener {

    private PullToRefreshGridView gridView = null;
    private View v = null;
    private ArrayList<TSDataInfo> list = new ArrayList<>();
    private List<TSDataInfo> newList = new ArrayList<>();
    private TSAdapter adapter;
    private int index = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.gridview, container, false);
        gridView = (PullToRefreshGridView) view.findViewById(R.id.ptrGridview);
        gridView.setOnItemClickListener(this);

        JSONDownloadUtils.getJson(Path.getRXPath(101, index), new JSONDownloadUtils.JSONListener() {
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

                        TSDataInfo tsDataInfo = new TSDataInfo(title, pic_url, web_url);

                        list.add(tsDataInfo);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter = new TSAdapter(list);
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
                JSONDownloadUtils.getJson(Path.getRXPath(101, index), new JSONDownloadUtils.JSONListener() {
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

                                TSDataInfo tsDataInfo = new TSDataInfo(title, pic_url, web_url);

                                newList.add(tsDataInfo);
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

                JSONDownloadUtils.getJson(Path.getRXPath(101, index), new JSONDownloadUtils.JSONListener() {
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

                                TSDataInfo tsDataInfo = new TSDataInfo(title, pic_url, web_url);

                                newList.add(tsDataInfo);
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
                JSONDownloadUtils.getJson(Path.getRXPath(101, ++index), new JSONDownloadUtils.JSONListener() {
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

                                TSDataInfo tsDataInfo = new TSDataInfo(title, pic_url, web_url);

                                newList.add(tsDataInfo);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("title", list.get(position).getTitle());
        intent.putExtra("web_url", list.get(position).getWeb_url());
        startActivity(intent);
    }
}
