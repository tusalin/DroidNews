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
import android.widget.Toast;

import com.tusalin.droidnews.Adapter.ArticalAdapter;
import com.tusalin.droidnews.Bean.GankNews;
import com.tusalin.droidnews.Callback.RetrofitCallBack;
import com.tusalin.droidnews.FragmentType;
import com.tusalin.droidnews.Network.DefaultRetrofit;
import com.tusalin.innews.R;

import java.util.ArrayList;
import java.util.List;

import static com.tusalin.droidnews.GankUrl.GANK_API_ANDROID;
import static com.tusalin.droidnews.GankUrl.GANK_API_MEIZI;
import static com.tusalin.droidnews.GankUrl.GANK_API_TUIJIAN;
import static com.tusalin.droidnews.GankUrl.GANK_API_TUOZHAN;

/**
 * Created by tusalin on 9/6/2016.
 */

public class ArticalFragment extends Fragment{

    private String keyType;
    private FragmentType fragmentType;
    private RecyclerView recyclerview;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView.LayoutManager layoutManager;
    private ArticalAdapter articalAdapter;
    private int contentQuantity = 10;
    private GankNews articals;
    private GankNews girls;

    public ArticalFragment(){
        super();
    }
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
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                requestGank();
            }
        });
        setAdapter();
        setDefaultLayoutManager();
        recyclerview.setAdapter(articalAdapter);
        recyclerview.setLayoutManager(layoutManager);
    }


    public void requestGank(){
        swipeRefreshLayout.measure(View.MEASURED_SIZE_MASK,View.MEASURED_HEIGHT_STATE_SHIFT);
        swipeRefreshLayout.setRefreshing(true);
//        contentQuantity += 10;
        DefaultRetrofit.getAllResult(keyType,contentQuantity,1,retrofitCallBack);

    }

    RetrofitCallBack<GankNews> retrofitCallBack = new RetrofitCallBack<GankNews>() {
        @Override
        public void retrofitSuccess(String keyType, GankNews news) {
            if (!keyType.equals(GANK_API_MEIZI)){
                articals = news;
                articalAdapter.setArticalResults(articals);
                DefaultRetrofit.getAllResult(GANK_API_MEIZI,contentQuantity,1,retrofitCallBack);
            } else {
                girls = news;
                articalAdapter.setGirlResults(girls);
                articalAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        }

        @Override
        public void retrofitFailure(String keyType, String error) {
            Toast.makeText(getContext(),error,Toast.LENGTH_SHORT).show();
            swipeRefreshLayout.setRefreshing(false);
        }
    };

    public void setAdapter(){
        articalAdapter = new ArticalAdapter(fragmentType,getContext());
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
