package com.tusalin.droidnews.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tusalin.droidnews.Activity.DrawerNewsDetailActivity;
import com.tusalin.droidnews.Adapter.TechnologyAdapter;
import com.tusalin.droidnews.Bean.NewsInfo;
import com.tusalin.droidnews.Callback.HtmlCallback;
import com.tusalin.droidnews.Callback.OnRecyclerViewItemClickListener;
import com.tusalin.droidnews.MyApplication;
import com.tusalin.droidnews.Network.VolleyRequest;
import com.tusalin.innews.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tusalin on 9/4/2016.
 */

public class TechnologyFragment extends Fragment {

    private List<NewsInfo> technologyNewsInfo = new ArrayList<>();
    private RecyclerView recyclerview;
    private TechnologyAdapter technologyAdapter;
    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
    public  String TECHNOLOGY_URL = "http://www.jiemian.com/lists/6.html";
    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_technology,container,false);

        return view;
    }


    public void startDetailActivity(int position){
        Intent intent = new Intent(getActivity(), DrawerNewsDetailActivity.class);
//        intent.putExtra("newsInfo", technologyNewsInfo.get(position));
        intent.putExtra("newsInfo",technologyAdapter.getItem(position));
        startActivity(intent);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview_technology);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh_technology);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                requestJiemianByVolleyRequest();
            }
        });
        recyclerview.setLayoutManager(linearLayoutManager);
//        requestJiemianByVolleyRequest();
        technologyAdapter = new TechnologyAdapter(getContext(), technologyNewsInfo);
        technologyAdapter.setOnRecyclerViewItemClick(new OnRecyclerViewItemClickListener() {
            @Override
            public void onRecyclerViewItemClick(int position, View view) {
                startDetailActivity(position);
            }
        });
        recyclerview.setAdapter(technologyAdapter);
            requestJiemianByVolleyRequest();
            technologyAdapter.addData(technologyNewsInfo);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void requestJiemianByVolleyRequest(){
        VolleyRequest.requetJiemian(TECHNOLOGY_URL, MyApplication.getContext(), new HtmlCallback() {
            @Override
            public void onSuccessParseHtml(List<NewsInfo> newsInfos) {
                if (swipeRefreshLayout.isRefreshing()){
                    swipeRefreshLayout.setRefreshing(false);
                }
                if (newsInfos!= null){
                    technologyNewsInfo = newsInfos;
                    technologyAdapter.addData(newsInfos);
                }
            }

            @Override
            public void onFailedParseHtml() {
                Snackbar.make(recyclerview,"parse html failed",Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
