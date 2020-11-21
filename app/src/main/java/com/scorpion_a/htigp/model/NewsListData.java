package com.scorpion_a.htigp.model;

public class NewsListData {
    private String newsTitle;
    private String newsDesc;
    public NewsListData(String newsTitle, String newsDesc) {
        this.newsTitle = newsTitle;
        this.newsDesc = newsDesc;
    }
    public String getnewsTitle() {
        return newsTitle;
    }
    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }
    public String getnewsDesc() {
        return newsDesc;
    }
    public void setnewsDesc(String newsDesc) {
        this.newsDesc = newsDesc;
    }
}
