package com.tusalin.droidnews.Bean;


import com.tusalin.droidnews.GankUrl;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tusalin on 9/6/2016.
 */

public class GankNews {
    private boolean error;
    private List<Results> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public class Results implements Serializable{
        private String _id;
        private String createdAt;
        private String desc;
        private String publishAt;
        private String source;
        private String type;
        private String url;
        private String used;
        private String who;

        private String title;

        public String getTitle(String type){
            switch (type){
                case GankUrl.GANK_API_ANDROID:
                    title = "Android";
                    break;
                case GankUrl.GANK_API_TUIJIAN:
                    title = "Recommend";
                    break;
                case GankUrl.GANK_API_TUOZHAN:
                    title = "Expand";
                    break;
            }
            return title;
        }

        public String get_Id() {
            return _id;
        }

        public void set_Id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishAt() {
            return publishAt;
        }

        public void setPublishAt(String publishAt) {
            this.publishAt = publishAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUsed() {
            return used;
        }

        public void setUsed(String used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }

}
