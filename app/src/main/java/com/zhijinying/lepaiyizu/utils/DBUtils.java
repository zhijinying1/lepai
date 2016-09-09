package com.zhijinying.lepaiyizu.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 16-9-3.
 */
public class DBUtils extends SQLiteOpenHelper{

    private static final int DB_VERSION = 2;
    private static String DB_NAME = "fengniao.db";

    public DBUtils(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "create table fengniao (_id integer primary key autoincrement, title String not null, web_url String not null)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "drop table fengniao";
        db.execSQL(sql);
        onCreate(db);
    }
}
