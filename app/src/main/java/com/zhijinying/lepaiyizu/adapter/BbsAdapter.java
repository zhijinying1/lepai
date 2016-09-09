package com.zhijinying.lepaiyizu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 16-9-7.
 */
public class BbsAdapter extends FragmentPagerAdapter{

    private List<Fragment> list;
    private List<String> datas;

    public BbsAdapter(FragmentManager fm, List<Fragment> list, List<String> datas) {
        super(fm);
        this.list = list;
        this.datas = datas;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return datas.get(position);
    }
}
