package com.tusalin.droidnews.Callback;

import android.view.View;

import com.tusalin.droidnews.Adapter.ArticalAdapter;

/**
 * Created by tusalin on 9/6/2016.
 */

public interface OnRecyclerViewItemClickListener {
    public void onRecyclerViewItemClick(ArticalAdapter.ArticalViewHolder viewHolder,
                                        int position, View view);
}
