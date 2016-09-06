package com.tusalin.droidnews.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tusalin.droidnews.Adapter.ArticalAdapter;
import com.tusalin.droidnews.FragmentType;
import com.tusalin.innews.R;

import static com.tusalin.droidnews.GankUrl.GANK_API_ANDROID;
import static com.tusalin.droidnews.GankUrl.GANK_API_TUIJIAN;
import static com.tusalin.droidnews.GankUrl.GANK_API_TUOZHAN;

/**
 * Created by tusalin on 9/6/2016.
 */

public class ArticalFragment extends Fragment {

    private String keyType;
    private FragmentType fragmentType;
    private RecyclerView recyclerview;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView.LayoutManager layoutManager;
    private ArticalAdapter articalAdapter;

    public ArticalFragment(FragmentType type){
        fragmentType = type;
        switch (type){
            case Android:
                keyType = GANK_API_ANDROID;
                break;
            case xiatuijian:
                keyType = GANK_API_TUIJIAN;
                break;
            case tuozhanziyuan:
                keyType = GANK_API_TUOZHAN;
                break;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artical,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerview = (RecyclerView) view.findViewById(R.id.artical_recyclerview);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.artical_swiperefresh);
        setAdapter();
        setDefaultLayoutManager();
        recyclerview.setAdapter(articalAdapter);
        recyclerview.setLayoutManager(layoutManager);
    }

    public void setAdapter(){
        articalAdapter = new ArticalAdapter(fragmentType);
    }

    public void setDefaultLayoutManager(){
        switch (fragmentType){
            case Android:
                layoutManager = new LinearLayoutManager(getContext());
                break;
            case xiatuijian:
                layoutManager = new GridLayoutManager(getContext(),2);
                break;
            case tuozhanziyuan:
                layoutManager = new GridLayoutManager(getContext(),2);
                break;
        }
    }
}
