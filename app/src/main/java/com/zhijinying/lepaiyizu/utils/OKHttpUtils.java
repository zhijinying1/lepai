package com.zhijinying.lepaiyizu.utils;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 16-9-3.
 */
public class OKHttpUtils {

    public static String getJsonByUrl(String url) {
        String json = null;

        OkHttpClient client = new OkHttpClient();

        Request.Builder builder = new Request.Builder();
        builder.url(url);

        Request request = builder.build();

        try {
            Response response = client.newCall(request).execute();

            json = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

    public static byte[] getByteArrayByUrl(String url) {

        byte[] data = null;

        OkHttpClient client = new OkHttpClient();

        Request.Builder builder = new Request.Builder();
        builder.url(url);

        Request request = builder.build();

        try {
            Response response = client.newCall(request).execute();

            data = response.body().bytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
