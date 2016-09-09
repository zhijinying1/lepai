package com.zhijinying.lepaiyizu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.widget.ListView;
import android.widget.Toast;

import com.zhijinying.lepaiyizu.R;
import com.zhijinying.lepaiyizu.adapter.LTAdapter;
import com.zhijinying.lepaiyizu.bean.LTDataInfo;
import com.zhijinying.lepaiyizu.utils.JSONDownloadUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BbsActivity extends AppCompatActivity {

    private ArrayList<LTDataInfo> list = new ArrayList<>();
    private LTAdapter adapter = null;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bbs);

        listView = (ListView) findViewById(R.id.lvBbsDetails);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.drawable.btn_back_focus);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        JSONDownloadUtils.getJson(url, new JSONDownloadUtils.JSONListener() {
            @Override
            public void successJson(String json) {

                try {

                    JSONObject object = new JSONObject(json);
                    JSONArray array = object.getJSONArray("list");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObj = array.getJSONObject(i);

                        String title = jsonObj.optString("title");
                        String author = jsonObj.optString("author");
                        String views = jsonObj.optString("views");
                        String reply = jsonObj.optString("reply");

                        LTDataInfo ltDataInfo = new LTDataInfo(title, author, views, reply);

                        list.add(ltDataInfo);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter = new LTAdapter(list);
                listView.setAdapter(adapter);
            }

            @Override
            public void errorJson() {

                Toast.makeText(getApplicationContext(), "网络异常", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
