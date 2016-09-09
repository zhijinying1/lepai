package com.zhijinying.lepaiyizu.mainfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhijinying.lepaiyizu.R;
import com.zhijinying.lepaiyizu.adapter.PicAdapter;
import com.zhijinying.lepaiyizu.fragment.FGFragment;
import com.zhijinying.lepaiyizu.fragment.RXFragment;
import com.zhijinying.lepaiyizu.fragment.SMFragment;
import com.zhijinying.lepaiyizu.fragment.STFragment;

import java.util.ArrayList;
import java.util.List;

public class PicFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private PicAdapter picAdapter;
    private List<Fragment> dataList;
    private List<String> titleList;
    private String titles[] = {"人像", "风光", "生态", "数码"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pic, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        titleList = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(titles[i]));
            titleList.add(titles[i]);
        }

        picAdapter = new PicAdapter(getChildFragmentManager(), dataList, titleList);
        viewPager.setAdapter(picAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(picAdapter);
    }

    private void initData() {

        dataList = new ArrayList<>();

        RXFragment rxFragment = new RXFragment();
        dataList.add(rxFragment);
        FGFragment fgFragment = new FGFragment();
        dataList.add(fgFragment);
        STFragment stFragment = new STFragment();
        dataList.add(stFragment);
        SMFragment smFragment = new SMFragment();
        dataList.add(smFragment);
    }
}
