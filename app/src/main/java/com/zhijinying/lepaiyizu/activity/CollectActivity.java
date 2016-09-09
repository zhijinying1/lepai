package com.zhijinying.lepaiyizu.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zhijinying.lepaiyizu.R;
import com.zhijinying.lepaiyizu.adapter.SZAdapter;
import com.zhijinying.lepaiyizu.bean.SZDataInfo;
import com.zhijinying.lepaiyizu.utils.DBUtils;

import java.util.ArrayList;
import java.util.List;

public class CollectActivity extends AppCompatActivity {

    private ListView listView;
    private List<SZDataInfo> dataList = new ArrayList<>();
    private SZAdapter szAdapter;
    private SQLiteDatabase db = null;

    private String web_url = null;
    private String title = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);

        listView = (ListView) findViewById(R.id.lvCollect);

        Intent intent = getIntent();
        DBUtils dbUtils = new DBUtils(this);

        try {
            db = dbUtils.getWritableDatabase();
        } catch (Exception e) {

            db = dbUtils.getReadableDatabase();

            e.printStackTrace();
        }

        String sql = "select * from fengniao";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            title = cursor.getString(cursor.getColumnIndex("title"));
            web_url = cursor.getString(cursor.getColumnIndex("web_url"));
            SZDataInfo szDataInfo = new SZDataInfo(title, web_url);
            dataList.add(szDataInfo);
        }

        szAdapter = new SZAdapter(getApplicationContext(), dataList);
        listView.setAdapter(szAdapter);

        onClick();
    }

    private void onClick() {

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("web_url", web_url);
                startActivity(intent);
            }
        });
    }
}
