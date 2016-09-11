package com.tusalin.droidnews.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tusalin.droidnews.Bean.GankNews;
import com.tusalin.droidnews.Callback.OnRecyclerViewItemClickListener;
import com.tusalin.droidnews.FragmentType;
import com.tusalin.innews.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tusalin on 9/6/2016.
 */

public class ArticalAdapter extends RecyclerView.Adapter<ArticalAdapter.ArticalViewHolder> {

    private FragmentType fragmentType;
    private List<GankNews.Results> articalResults = new ArrayList<>();
    private List<GankNews.Results> girlResults;
    private Context context;
    private OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener;
    private GankNews.Results girl;

    public ArticalAdapter(FragmentType type, Context context){
        fragmentType = type;
        this.context = context;
    }

    public void setArticalResults(GankNews gankNews){
        articalResults.clear();
        articalResults.addAll(gankNews.getResults());
    }

    public void setGirlResults(GankNews gankNews){
//        girlResults.clear();
        girlResults = new ArrayList<>();
        girlResults.addAll(gankNews.getResults());
    }

    @Override
    public int getItemCount() {
       return articalResults.size();
    }

    @Override
    public void onBindViewHolder(ArticalViewHolder holder, int position) {
        GankNews.Results artical = articalResults.get(position);
        Log.d("articalSize",articalResults.size()+"");
        if (girlResults!= null){
            girl = girlResults.get((int) (Math.random()*girlResults.size()));
        }
        holder.textViewDate.setText(artical.getCreatedAt().substring(0,10));
        holder.textViewTitle.setText(artical.getDesc());
        if (girl != null){
            String girlsUrl = girl.getUrl();
            Picasso.with(context)
                    .load(girlsUrl)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error)
                    .fit()
                    .centerCrop()
                    .tag(holder.imageView.getContext())
                    .into(holder.imageView);
        }else {
            holder.imageView.setImageResource(R.drawable.placeholder);
        }
    }

    @Override
    public ArticalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (fragmentType){
            case Android:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artical_item_linear,
                        parent,false);
                break;
            case xiatuijian:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artical_item_grid,
                        parent,false);
                break;
            case tuozhanziyuan:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artical_item_grid,
                        parent,false);
                break;
        }
        ArticalViewHolder viewHolder = new ArticalViewHolder(view,mOnRecyclerViewItemClickListener);
        return viewHolder;

    }

    public class ArticalViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textViewTitle;
        private TextView textViewDate;
        private OnRecyclerViewItemClickListener onItemClickListener;

        public ArticalViewHolder(View view,OnRecyclerViewItemClickListener listener){
            super(view);
            onItemClickListener = listener;
            imageView = (ImageView) view.findViewById(R.id.cardview_artical_image);
            textViewDate = (TextView) view.findViewById(R.id.cardview_artical_date);
            textViewTitle = (TextView) view.findViewById(R.id.cardview_artical_title);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener!=null){
                        onItemClickListener.onRecyclerViewItemClick(getLayoutPosition(),view);
                    }
                }
            });
        }

    }

    public void setOnRecyclerViewItemClick(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener){
        mOnRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }
}
