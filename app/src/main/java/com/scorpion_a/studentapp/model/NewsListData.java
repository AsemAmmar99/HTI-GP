package com.scorpion_a.studentapp.model;

public class NewsListData {
    private String newsTitle;
    private String newsDesc;
    private String newsDate;

    public NewsListData(String newsTitle, String newsDesc, String newsDate) {
        this.newsTitle = newsTitle;
        this.newsDesc = newsDesc;
        this.newsDate = newsDate;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsDesc() {
        return newsDesc;
    }

    public void setNewsDesc(String newsDesc) {
        this.newsDesc = newsDesc;
    }

    public String getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(String newsDate) {
        this.newsDate = newsDate;
    }
}
