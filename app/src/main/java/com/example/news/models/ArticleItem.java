package com.example.news.models;

/**
 * Created by Rim on 10/03/2018.
 */

public class ArticleItem {

    private String title;
    private String description;
    private String url;
    private String urlToImage;


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

    public String getURL() {
        return url;
    }

    public void setURL(String URL) {
        this.url = URL;
    }

    public String getImageURL() {
        return urlToImage;
    }

    public void setImageURL(String imageURL) {
        this.urlToImage = imageURL;
    }

}