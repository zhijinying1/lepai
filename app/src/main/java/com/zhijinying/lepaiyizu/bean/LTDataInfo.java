package com.zhijinying.lepaiyizu.bean;

/**
 * Created by Administrator on 16-9-7.
 */
public class LTDataInfo {

    private String title;
    private String author;
    private String views;
    private String reply;
    private String web_url;

    public LTDataInfo() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public LTDataInfo(String title, String author, String views, String reply) {

        this.title = title;
        this.author = author;
        this.views = views;
        this.reply = reply;
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
