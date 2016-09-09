package com.zhijinying.lepaiyizu.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zhijinying.lepaiyizu.R;
import com.zhijinying.lepaiyizu.utils.DBUtils;

public class DetailsActivity extends AppCompatActivity {

    private WebView webView;
    private SQLiteDatabase db = null;

    String web_url = null;
    String title = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        webView = (WebView) findViewById(R.id.webView);

        toolbar.setLogo(R.drawable.btn_back_focus);

        DBUtils dbUtils = new DBUtils(this);
        try {
            db = dbUtils.getWritableDatabase();

        } catch (Exception e) {
            db = dbUtils.getReadableDatabase();
            e.printStackTrace();
        }

        Intent intent = getIntent();
        web_url = intent.getStringExtra("web_url");
        title = intent.getStringExtra("title");
        webView.loadUrl(web_url);
        WebSettings settings = webView.getSettings();
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        settings.setJavaScriptEnabled(true);

    }

    public void  collect(View view){

        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("web_url", web_url);
        db.insert("fengniao", null, values);
    }
}
