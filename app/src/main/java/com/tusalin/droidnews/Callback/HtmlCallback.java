package com.tusalin.droidnews.Callback;

import com.tusalin.droidnews.Bean.NewsInfo;

import java.util.List;

/**
 * Created by tusalin on 9/7/2016.
 */

public interface HtmlCallback {
    public void onSuccessParseHtml(List<NewsInfo> newsInfo);
    public void onFailedParseHtml();
}
