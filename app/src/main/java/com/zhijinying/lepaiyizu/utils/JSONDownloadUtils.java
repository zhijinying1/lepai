package com.zhijinying.lepaiyizu.utils;

import android.os.Handler;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 16-9-3.
 */
public class JSONDownloadUtils {

    private static JSONListener listener = null;
    private static Handler handler = null;

    public static void getJson(String path, JSONListener listener) {

        JSONDownloadUtils.listener = listener;
        handler = new Handler();
        OkHttpClient client = new OkHttpClient();
        Request build = new Request.Builder().url(path).build();
        Call call = client.newCall(build);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        JSONDownloadUtils.listener.errorJson();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String json = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        JSONDownloadUtils.listener.successJson(json);
                    }
                });
            }
        });
    }

    public interface JSONListener {

        void successJson(String json);
        void errorJson();
    }
}
