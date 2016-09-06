package com.tusalin.droidnews.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tusalin.droidnews.Bean.NewsInfo;
import com.tusalin.innews.R;

import java.util.List;

/**
 * Created by tusalin on 9/5/2016.
 */

public class TechnologyAdapter extends RecyclerView.Adapter<TechnologyAdapter.TechnologyViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<NewsInfo> mNewsInfos;
    public TechnologyAdapter(Context context,List<NewsInfo> newsInfos) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mNewsInfos = newsInfos;
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public void onBindViewHolder(TechnologyViewHolder holder, int position) {
      /*  holder.newsDate.setText(mNewsInfos.get(position).getNewsDate());
        holder.newsTitle.setText(mNewsInfos.get(position).getNewsTitle());*/
    }

    @Override
    public TechnologyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TechnologyViewHolder(mInflater.inflate(R.layout.drawer_news_item,parent,false));
    }

    public class TechnologyViewHolder extends RecyclerView.ViewHolder{
        public ImageView newsImage;
        public TextView newsTitle;
        public TextView newsDate;

        public TechnologyViewHolder(View view){
            super(view);
            newsImage = (ImageView) view.findViewById(R.id.cardview_news_image);
            newsTitle = (TextView) view.findViewById(R.id.cardview_news_title);
            newsDate = (TextView) view.findViewById(R.id.cardview_news_date);
        }
    }

    public void addData(List<NewsInfo> list){
        mNewsInfos = list;
    }
}
