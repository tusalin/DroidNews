package com.tusalin.droidnews.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
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
import com.tusalin.droidnews.Callback.OnRecyclerViewItemClickListener;
import com.tusalin.droidnews.Callback.RetrofitCallBack;
import com.tusalin.droidnews.DetailActivity;
import com.tusalin.droidnews.FragmentType;
import com.tusalin.droidnews.Network.DefaultRetrofit;
import com.tusalin.innews.R;

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
    private int lastVisibleItem;
    private Activity mActivity;

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
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity) context;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerview = (RecyclerView) view.findViewById(R.id.artical_recyclerview);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.artical_swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestGank(false);
            }
        });
        setAdapter();
        setDefaultLayoutManager();
        articalAdapter.setOnRecyclerViewItemClick(new OnRecyclerViewItemClickListener() {
            @Override
            public void onRecyclerViewItemClick(int position, View view) {
                startDetailActivity(position);
            }
        });
        recyclerview.setAdapter(articalAdapter);
        recyclerview.setLayoutManager(layoutManager);

        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem == layoutManager.getItemCount()-1){
                    requestGank(true);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                lastVisibleItem = findLastVisibleItem();
            }
        });
    }

    private void startDetailActivity(int position) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("girls", girls.getResults().get(position));
        intent.putExtra("articals",articals.getResults().get(position));
        startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity()).toBundle());
    }

    private int findLastVisibleItem() {
        int lastVisibleItemPosition = 0;
        RecyclerView.LayoutManager layoutManager = recyclerview.getLayoutManager();
        switch (fragmentType){
            case Android:
                lastVisibleItemPosition = ((LinearLayoutManager)layoutManager).findLastVisibleItemPosition();
                break;
            case xiatuijian:
            case tuozhanziyuan:
                lastVisibleItemPosition = ((GridLayoutManager)layoutManager).findLastVisibleItemPosition();
                break;
        }
        return lastVisibleItemPosition;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        requestGank(false);
    }

    public void requestGank(boolean loadMore){
        swipeRefreshLayout.measure(View.MEASURED_SIZE_MASK,View.MEASURED_HEIGHT_STATE_SHIFT);
        swipeRefreshLayout.setRefreshing(true);
        if (loadMore){
            contentQuantity += 10;
        }
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
            Toast.makeText(mActivity,error,Toast.LENGTH_SHORT).show();
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
