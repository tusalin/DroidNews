package com.tusalin.droidnews.Network;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tusalin.droidnews.Bean.NewsInfo;
import com.tusalin.droidnews.Callback.HtmlCallback;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tusalin on 9/7/2016.
 */

public class VolleyRequest {

    private static List<NewsInfo> newsInfo = new ArrayList<>();

    public static void requetJiemian(String url,Context context, final HtmlCallback htmlCallback){
        RequestQueue mRequestquue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseHtml(response);
                        htmlCallback.onSuccessParseHtml(newsInfo);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        htmlCallback.onFailedParseHtml(error);
                    }
                });
        mRequestquue.add(stringRequest);
    }


    public static void parseHtml(final String response){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Document document = Jsoup.parse(response);
                Elements elements = document.select(".news-view").select(".left");
                parseElementsToList(elements,newsInfo);
            }
        }).start();
    }

    public static void parseElementsToList(Elements elements, List<NewsInfo> list) {
        if ( elements.size() > 0) {
            for (int i = 0; i < elements.size(); i++) {
                NewsInfo item = new NewsInfo();
                Element element = elements.get(i);
                Elements elementTitle = element.select("div.news-header").select("a");
                Elements elementImageUrl = element.getElementsByTag("img");
                Elements elementDate = element.getElementsByClass("news-footer");
                elementDate.select("span.comment").remove();
                elementDate.select("a").remove();

                if (elementTitle.size() > 0 && elementImageUrl.size() > 0 && elementDate.size() > 0) {
                    item.setNewsTitle(elementTitle.text());
                    item.setNewsUrl(elementTitle.attr("href"));
                    item.setNewsImageUrl(elementImageUrl.attr("src"));
                    item.setNewsDate(elementDate.text());
                    list.add(item);
                }
            }

        }

    }
}
