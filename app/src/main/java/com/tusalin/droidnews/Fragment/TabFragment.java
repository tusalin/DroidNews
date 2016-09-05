package com.tusalin.droidnews.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tusalin.droidnews.Bean.NewsItem;
import com.tusalin.droidnews.Adapter.TabAdapter;
import com.tusalin.innews.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tusalin on 9/4/2016.
 */

public class TabFragment extends Fragment{

    private RecyclerView recyclerview;
    private TabAdapter tabAdapter;
    private List<NewsItem> newsItem;
    private SwipeRefreshLayout swipetrefreshlayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab,container,false);
        recyclerview = (RecyclerView) view.findViewById(R.id.tab_recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        initNewsItem();
        tabAdapter = new TabAdapter(getContext(),newsItem);
        recyclerview.setAdapter(tabAdapter);
        swipetrefreshlayout = (SwipeRefreshLayout) view.findViewById(R.id.tab_swiperefresh);
        swipetrefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipetrefreshlayout.setRefreshing(false);
                        addData();
                    }
                },2000);
            }
        });
        return view;
    }

    public TabFragment newInstance(int position){

        TabFragment tabFragment = new TabFragment();
        return tabFragment;
    }

    public void initNewsItem(){
        newsItem = new ArrayList<NewsItem>();

        for (int i = 0;i < 10;i++){
            NewsItem item = new NewsItem();
            item.setNewsTitle("News Title "+ i);
            item.setNewsContent("this is content " + i);
            newsItem.add(item);
        }
    }
    public void addData(){
        List<NewsItem> newItem = new ArrayList<NewsItem>();

        for (int i = 0;i<3;i++){
            NewsItem item = new NewsItem();
            item.setNewsTitle("Add Title "+ i);
            item.setNewsContent("this is add content " + i);
            newItem.add(item);
        }
        newsItem.addAll(0,newItem);
        tabAdapter.notifyDataSetChanged();
    }


}
