package com.zhijinying.lepaiyizu.bean;

/**
 * Created by liuying on 2016/9/4.
 */
public class JXDataInfo {

    String title;
    String pic_url;
    String date;
    String author;
    String doc_url;
    String web_url;
    String more_comment_url;

    public JXDataInfo() {
    }

    public JXDataInfo(String title, String pic_url, String date, String web_url) {
        this.title = title;
        this.pic_url = pic_url;
        this.date = date;
        this.web_url = web_url;
    }

    public JXDataInfo(String title, String pic_url) {

        this.title = title;
        this.pic_url = pic_url;
    }

    public JXDataInfo(String title, String pic_url, String date, String author,
                      String doc_url, String web_url, String more_comment_url) {
        this.title = title;
        this.pic_url = pic_url;
        this.date = date;
        this.author = author;
        this.doc_url = doc_url;
        this.web_url = web_url;
        this.more_comment_url = more_comment_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDoc_url() {
        return doc_url;
    }

    public void setDoc_url(String doc_url) {
        this.doc_url = doc_url;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public String getMore_comment_url() {
        return more_comment_url;
    }

    public void setMore_comment_url(String more_comment_url) {
        this.more_comment_url = more_comment_url;
    }

    @Override
    public String toString() {
        return "JXDataInfo{" +
                "title='" + title + '\'' +
                ", pic_url='" + pic_url + '\'' +
                ", date='" + date + '\'' +
                ", author='" + author + '\'' +
                ", doc_url='" + doc_url + '\'' +
                ", web_url='" + web_url + '\'' +
                ", more_comment_url='" + more_comment_url + '\'' +
                '}';
    }
}
