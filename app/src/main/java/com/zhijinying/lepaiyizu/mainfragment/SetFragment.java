package com.zhijinying.lepaiyizu.mainfragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.zhijinying.lepaiyizu.R;
import com.zhijinying.lepaiyizu.activity.AboutActivity;
import com.zhijinying.lepaiyizu.activity.CollectActivity;
import com.zhijinying.lepaiyizu.activity.FeedbackActivity;
import com.zhijinying.lepaiyizu.activity.LoginActivity;
import com.zhijinying.lepaiyizu.activity.MybbsActivity;
import com.zhijinying.lepaiyizu.activity.ShareActivity;

public class SetFragment extends Fragment {

    private LinearLayout setting_reg_login, setting_shareManager, setting_bbs, setting_collection,
            setting_clearCache, setting_autoImg, setting_feedback, setting_about;
    private ListView lvCollect = null;
    private SQLiteDatabase db = null;
    private SimpleCursorAdapter cursorAdapter;
    private Cursor cursor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_set, container, false);

        setting_reg_login = (LinearLayout) view.findViewById(R.id.setting_reg_login);
        setting_shareManager = (LinearLayout) view.findViewById(R.id.setting_shareManager);
        setting_bbs = (LinearLayout) view.findViewById(R.id.setting_bbs);
        setting_collection = (LinearLayout) view.findViewById(R.id.setting_collection);
        setting_clearCache = (LinearLayout) view.findViewById(R.id.setting_clearCache);
        setting_autoImg = (LinearLayout) view.findViewById(R.id.setting_autoImg);
        setting_feedback = (LinearLayout) view.findViewById(R.id.setting_feedback);
        setting_about = (LinearLayout) view.findViewById(R.id.setting_about);
//        lvCollect = (ListView) view.findViewById(R.id.lvCollect);
//        DBUtils dbUtils = new DBUtils(getActivity());

        setting_reg_login.setOnClickListener(onClickListener);
        setting_shareManager.setOnClickListener(onClickListener);
        setting_bbs.setOnClickListener(onClickListener);
        setting_collection.setOnClickListener(onClickListener);
        setting_clearCache.setOnClickListener(onClickListener);
        setting_autoImg.setOnClickListener(onClickListener);
        setting_feedback.setOnClickListener(onClickListener);
        setting_about.setOnClickListener(onClickListener);

//        try {
//            db = dbUtils.getWritableDatabase();
//
//        } catch (Exception e) {
//            db = dbUtils.getReadableDatabase();
//            e.printStackTrace();
//        }
//
//        cursor = db.rawQuery("select * from fengniao", null);
//
//        cursorAdapter = new SimpleCursorAdapter(getActivity(), R.layout.collect_listview, cursor,
//                new String[]{"title", "web_url"}, new int[]{R.id.tvTitleCollect, R.id.tvWeburlCollect});
//        lvCollect.setAdapter(cursorAdapter);

        return view;
    }

    public View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.setting_reg_login:
                    Intent intent1 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent1);
                    break;

                case R.id.setting_shareManager:
                    Intent intent2 = new Intent(getActivity(), ShareActivity.class);
                    startActivity(intent2);
                    break;

                case R.id.setting_bbs:
                    Intent intent3 = new Intent(getActivity(), MybbsActivity.class);
                    startActivity(intent3);
                    break;

                case R.id.setting_collection:
                    Intent intent4 = new Intent(getActivity(), CollectActivity.class);
                    startActivity(intent4);
                    break;

                case R.id.setting_clearCache:

                    break;

                case R.id.setting_autoImg:

                    break;

                case R.id.setting_feedback:
                    Intent intent7 = new Intent(getActivity(), FeedbackActivity.class);
                    startActivity(intent7);
                    break;

                case R.id.setting_about:
                    Intent intent8 = new Intent(getActivity(), AboutActivity.class);
                    startActivity(intent8);
                    break;

                default:
                    break;
            }
        }
    };
}
