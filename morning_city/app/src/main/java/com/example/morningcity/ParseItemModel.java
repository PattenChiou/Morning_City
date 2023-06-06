package com.example.morningcity;

public class ParseItemModel {
    String title;
    String date;
    String imageLink;

    public ParseItemModel(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    String websiteUrl;

    public ParseItemModel(String title, String date, String imageLink,String websiteUrl) {
        this.title = title;
        this.date = date;
        this.imageLink = imageLink;
        this.websiteUrl = websiteUrl;
    }


    public ParseItemModel(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }
}
