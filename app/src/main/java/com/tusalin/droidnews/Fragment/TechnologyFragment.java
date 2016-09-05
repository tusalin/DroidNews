package com.tusalin.droidnews.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tusalin.droidnews.Adapter.TechnologyAdapter;
import com.tusalin.droidnews.Bean.NewsInfo;
import com.tusalin.innews.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * Created by tusalin on 9/4/2016.
 */

public class TechnologyFragment extends Fragment {

    private List<NewsInfo> newsInfo;
    private RecyclerView recyclerview;
    private TechnologyAdapter technologyAdapter;
    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
    public  String TECHNOLOGY_URL = "https://www.jiemian.com/lists/6.html";
    public static final int MSG_SUCCESS = 1;
    public static final int MSG_FAILURE = 0;
    private MyHandler mHandler = new MyHandler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drawer_news,container,false);
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview_technology);
        recyclerview.setLayoutManager(linearLayoutManager);
        requestData();
        technologyAdapter = new TechnologyAdapter(getContext(),newsInfo);
        recyclerview.setAdapter(technologyAdapter);
        return view;
    }

    public void requestData(){
        RequestQueue mQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest mStringRequest = new StringRequest("http://www.jiemian.com/lists/6.html",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("technology", response);
                        parseHtml(response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", error.getMessage(), error);
            }
        });
        mQueue.add(mStringRequest);
    }

    public class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_SUCCESS:
                    technologyAdapter.addData(newsInfo);
                    technologyAdapter.notifyDataSetChanged();
            }
        }
    }


    public void parseHtml(final String response){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Document document = Jsoup.parse(response);
                if (document == null){
                    mHandler.sendEmptyMessage(MSG_FAILURE);
                    return;
                }
                Elements elements = document.select(".news-view").select(".left");
                Log.d("document",elements.toString());
                parseElementsToList(elements,newsInfo);
//                Log.d("newsInfos",newsInfo.toString());
                mHandler.sendEmptyMessage(MSG_SUCCESS);
            }
        }).start();
    }

    public void parseElementsToList(Elements elements,List<NewsInfo> feedList) {
        if (feedList != null && elements.size() > 0) {
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
                    feedList.add(item);
                    Log.d("feedList",feedList.toString());
                }
            }

        }

    }
}
