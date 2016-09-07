package com.tusalin.droidnews.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tusalin.droidnews.Bean.NewsInfo;
import com.tusalin.droidnews.Callback.OnRecyclerViewItemClickListener;
import com.tusalin.innews.R;

import java.util.List;

/**
 * Created by tusalin on 9/5/2016.
 */

public class TechnologyAdapter extends RecyclerView.Adapter<TechnologyAdapter.TechnologyViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<NewsInfo> mNewsInfos;
    private OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener;
    public TechnologyAdapter(Context context,List<NewsInfo> newsInfos) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mNewsInfos = newsInfos;
    }

    public NewsInfo getItem(int position){
        if (mNewsInfos!=null){
            return mNewsInfos.get(position);
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return mNewsInfos.size();
    }

    @Override
    public void onBindViewHolder(TechnologyViewHolder holder, int position) {
        holder.newsDate.setText(mNewsInfos.get(position).getNewsDate());
        holder.newsTitle.setText(mNewsInfos.get(position).getNewsTitle());
        Picasso.with(mContext)
                .load(mNewsInfos.get(position).getNewsImageUrl())
                .placeholder(R.drawable.placeholder)
                .fit()
                .into(holder.newsImage);
    }

    @Override
    public TechnologyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TechnologyViewHolder(mInflater.inflate(R.layout.drawer_news_item,parent,false),
                mOnRecyclerViewItemClickListener);
    }

    public class TechnologyViewHolder extends RecyclerView.ViewHolder{
        public ImageView newsImage;
        public TextView newsTitle;
        public TextView newsDate;
        private OnRecyclerViewItemClickListener onClickListener;

        public TechnologyViewHolder(View view, final OnRecyclerViewItemClickListener onItemClickListener){
            super(view);
            newsImage = (ImageView) view.findViewById(R.id.cardview_news_image);
            newsTitle = (TextView) view.findViewById(R.id.cardview_news_title);
            newsDate = (TextView) view.findViewById(R.id.cardview_news_date);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListener = onItemClickListener;
                    onClickListener.onRecyclerViewItemClick(getLayoutPosition(),view);
                }
            });
        }
    }

    public void addData(List<NewsInfo> list){
        mNewsInfos = list;
        notifyDataSetChanged();
    }

    public void setOnRecyclerViewItemClick(OnRecyclerViewItemClickListener listener){
        mOnRecyclerViewItemClickListener = listener;
    }
}
