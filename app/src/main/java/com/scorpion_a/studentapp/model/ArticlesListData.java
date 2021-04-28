package com.scorpion_a.studentapp.model;

public class ArticlesListData {
    private String id;
    private String title;
    private String images;
    private String type;
    private String date;

    public ArticlesListData(String id, String title, String images, String type, String date) {
        this.id = id;
        this.title = title;
        this.images = images;
        this.type = type;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}