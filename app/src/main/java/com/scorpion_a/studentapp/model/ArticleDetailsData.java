package com.scorpion_a.studentapp.model;

public class ArticleDetailsData {
    private String id;
    private String title;
    private String description;
    private String images;
    private String type;
    private String date;
    private String archived_at;

    public ArticleDetailsData(String id, String title, String description, String images, String type, String date, String archived_at) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.images = images;
        this.type = type;
        this.date = date;
        this.archived_at = archived_at;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getArchived_at() {
        return archived_at;
    }

    public void setArchived_at(String archived_at) {
        this.archived_at = archived_at;
    }
}
