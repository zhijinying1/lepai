package com.zhijinying.lepaiyizu.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.widget.RadioGroup;

import com.zhijinying.lepaiyizu.R;
import com.zhijinying.lepaiyizu.mainfragment.BbsFragment;
import com.zhijinying.lepaiyizu.mainfragment.NewsFragment;
import com.zhijinying.lepaiyizu.mainfragment.PicFragment;
import com.zhijinying.lepaiyizu.mainfragment.SetFragment;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup radioGroup;
    FragmentTransaction transaction;

    NewsFragment newsFragment;
    PicFragment picFragment;
    BbsFragment bbsFragment;
    SetFragment setFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initView() {

        radioGroup = (RadioGroup) findViewById(R.id.mainRadioGroup);
        radioGroup.setOnCheckedChangeListener(this);
    }

    private void initData() {

        newsFragment = new NewsFragment();
        picFragment = new PicFragment();
        bbsFragment = new BbsFragment();
        setFragment = new SetFragment();

        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_contents_fl, newsFragment).commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        transaction = getSupportFragmentManager().beginTransaction();
        switch (checkedId){
            case R.id.mainRadio_news:
                transaction.replace(R.id.main_contents_fl, newsFragment);
                break;

            case R.id.mainRadio_pic:
                transaction.replace(R.id.main_contents_fl, picFragment);
                break;

            case R.id.mainRadio_bbs:
                transaction.replace(R.id.main_contents_fl, bbsFragment);
                break;

            case R.id.mainRadio_set:
                transaction.replace(R.id.main_contents_fl, setFragment);
                break;
        }
        transaction.commit();
    }
}
