package com.tusalin.droidnews.Bean;

import android.graphics.Bitmap;

/**
 * Created by tusalin on 9/4/2016.
 */

public class NewsItem {
    private Bitmap newsImage;
    private String newsTitle;
    private String newsContent;

    public Bitmap getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(Bitmap newsImage) {
        this.newsImage = newsImage;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }
}
