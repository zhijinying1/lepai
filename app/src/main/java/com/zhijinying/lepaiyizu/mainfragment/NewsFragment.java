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
import com.zhijinying.lepaiyizu.fragment.JXFragment;
import com.zhijinying.lepaiyizu.fragment.QCFragment;
import com.zhijinying.lepaiyizu.fragment.XYFragment;
import com.zhijinying.lepaiyizu.fragment.YXFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 16-9-3.
 */
public class NewsFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private PicAdapter picAdapter;
    private List<Fragment> list;
    private List<String> titleList;
    private String titles[] = {"精选", "器材", "影像", "学院"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_news, null);
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

        picAdapter = new PicAdapter(getChildFragmentManager(), list, titleList);
        viewPager.setAdapter(picAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(picAdapter);
    }

    private void initData() {

        list = new ArrayList<>();

        JXFragment jxFragment = new JXFragment();
        list.add(jxFragment);
        QCFragment qcFragment = new QCFragment();
        list.add(qcFragment);
        YXFragment yxFragment = new YXFragment();
        list.add(yxFragment);
        XYFragment xyFragment = new XYFragment();
        list.add(xyFragment);
    }
}
