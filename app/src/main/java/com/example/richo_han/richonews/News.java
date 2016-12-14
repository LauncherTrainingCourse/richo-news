package com.example.richo_han.richonews;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Richo_Han on 2016/12/13.
 */

public class News implements Parcelable{

    public String author, title, description, url, urlToImage, publishedAt;

    public News() {
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeString(this.author);
        out.writeString(this.title);
        out.writeString(this.description);
        out.writeString(this.publishedAt);
        out.writeString(this.url);
        out.writeString(this.urlToImage);
    }

    public static final Parcelable.Creator<News> CREATOR
            = new Parcelable.Creator<News>() {

        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    public News(Parcel in){
        this.title = in.readString();
        this.author = in.readString();
        this.description = in.readString();
        this.publishedAt = in.readString();
        this.url = in.readString();
        this.urlToImage = in.readString();
    }
}
