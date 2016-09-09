package com.zhijinying.lepaiyizu.bean;

/**
 * Created by Administrator on 16-9-8.
 */
public class SZDataInfo {

    String title;
    String web_url;

    public SZDataInfo() {
    }

    public SZDataInfo(String title, String web_url) {
        this.title = title;
        this.web_url = web_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }
}
