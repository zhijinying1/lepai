package com.zhijinying.lepaiyizu.mainfragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zhijinying.lepaiyizu.R;
import com.zhijinying.lepaiyizu.activity.BbsActivity;
import com.zhijinying.lepaiyizu.bean.LTDataInfo;
import com.zhijinying.lepaiyizu.utils.Path;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BbsFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {
    private ListView listView;
    private RadioGroup radioGroup;

    private ArrayAdapter arrayAdapter;
    private ArrayList<LTDataInfo> list = new ArrayList<>();

    private Map<Integer, String[]> map;
    private Map<Integer, String[]> pathMap;
    private List<String> datas;

    String[] path1 = {Path.getLTPath(1), Path.getLTPath(2), Path.getLTPath(3), Path.getLTPath(4)};
    String[] path2 = {Path.getLTPath(5), Path.getLTPath(6), Path.getLTPath(7), Path.getLTPath(8),
            Path.getLTPath(9), Path.getLTPath(10), Path.getLTPath(11), Path.getLTPath(12)};
    String[] path3 = {Path.getLTPath(13), Path.getLTPath(14), Path.getLTPath(15), Path.getLTPath(16),
            Path.getLTPath(17), Path.getLTPath(18), Path.getLTPath(19), Path.getLTPath(20)};
    String[] path4 = {Path.getLTPath(1), Path.getLTPath(2), Path.getLTPath(3)};
    String[] path5 = {Path.getLTPath(4), Path.getLTPath(5), Path.getLTPath(6)};
    String[] path6 = {Path.getLTPath(7), Path.getLTPath(8), Path.getLTPath(9)};
    String[] path7 = {Path.getLTPath(10), Path.getLTPath(11), Path.getLTPath(12)};
    String[] str1 = {"热帖", "精华帖", "最新帖子", "最新回复"};
    String[] str2 = {"人像", "风光", "纪实", "人体", "儿童", "建筑", "生态", "宠物"};
    String[] str3 = {"商业", "女性视觉", "新手", "数码", "黑白", "实验", "生活摄影", "高校"};
    String[] str4 = {"交易警示", "二手交易", "器材维修"};
    String[] str5 = {"北京", "上海", "西安"};
    String[] str6 = {"单反和镜头", "大中画幅", "便携数码"};
    String[] str7 = {"活动区", "网友服务", "蜂鸟茶馆"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();

        datas = new ArrayList<>();
        for (int i = 0; i < str1.length; i++) {
            datas.add(str1[i]);
        }
    }

    private void initData() {

        map = new ArrayMap<>();
        map.put(0, str1);
        map.put(1, str2);
        map.put(2, str3);
        map.put(3, str4);
        map.put(4, str5);
        map.put(5, str6);
        map.put(6, str7);

        pathMap = new ArrayMap<>();
        pathMap.put(0, path1);
        pathMap.put(1, path2);
        pathMap.put(2, path3);
        pathMap.put(3, path4);
        pathMap.put(4, path5);
        pathMap.put(5, path6);
        pathMap.put(6, path7);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bbs, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = (ListView) view.findViewById(R.id.listViewBbs);
        radioGroup = (RadioGroup) view.findViewById(R.id.radioGroupBbs);
        radioGroup.setOnCheckedChangeListener(this);
        onClick(0);

        arrayAdapter = new ArrayAdapter(getContext(), R.layout.item_bbs, R.id.tvBbs, datas);
        listView.setAdapter(arrayAdapter);

    }

    public void onClick(final int path){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), BbsActivity.class);
                intent.putExtra("url", pathMap.get(path)[position]);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId) {
            case R.id.rbLT:
                Selection(0);
                onClick(0);
                break;

            case R.id.rbZP:
                Selection(1);
                onClick(1);
                break;

            case R.id.rbSY:
                Selection(2);
                onClick(2);
                break;

            case R.id.rbJY:
                Selection(3);
                onClick(3);
                break;

            case R.id.rbFZ:
                Selection(4);
                onClick(4);
                break;

            case R.id.rbTL:
                Selection(5);
                onClick(5);
                break;

            case R.id.rbFW:
                Selection(6);
                onClick(6);
                break;

            default:
                break;
        }
    }

    public void Selection(int num) {

        RadioButton button;
        for (int i = 0; i < 7; i++) {
            button = (RadioButton) radioGroup.getChildAt(i);
            if (i == num) {
                button.setTextColor(Color.RED);
                datas.clear();
                for (int j = 0; j < map.get(num).length; j++) {

                    datas.add(map.get(num)[j]);
                }
                arrayAdapter.notifyDataSetChanged();
            } else {
                button.setTextColor(Color.BLACK);
            }
        }
    }
}
