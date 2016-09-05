package com.tusalin.droidnews.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tusalin.droidnews.Bean.NewsItem;
import com.tusalin.innews.R;

import java.util.List;

/**
 * Created by tusalin on 9/4/2016.
 */

public class TabAdapter extends RecyclerView.Adapter<TabAdapter.MyViewHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<NewsItem> mNewsItems;

    public TabAdapter(Context context,List<NewsItem> items){
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mNewsItems = items;
    }

    @Override
    public int getItemCount() {
        return mNewsItems.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mTitle.setText(mNewsItems.get(position).getNewsTitle());
        holder.mContent.setText(mNewsItems.get(position).getNewsContent());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mInflater.inflate(R.layout.recyclerview_item,parent,false));
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView mTitle;
        ImageView mImage;
        TextView mContent;

        public MyViewHolder(View view){
            super(view);
            mTitle = (TextView) view.findViewById(R.id.recyclerview_title);
            mContent = (TextView) view.findViewById(R.id.recyclerview_content);
            mImage = (ImageView) view.findViewById(R.id.item_image);
        }
    }
}
