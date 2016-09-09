package com.zhijinying.lepaiyizu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ImageView;

import com.zhijinying.lepaiyizu.R;

public class WelcomeActivity extends AppCompatActivity {

    private ImageView imageView = null;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_welcome);

        initView();

        intent = new Intent(WelcomeActivity.this, MainActivity.class);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void initView() {

        imageView = (ImageView) findViewById(R.id.imagView);
        imageView.setImageResource(R.drawable.img_guide2);
    }
}
