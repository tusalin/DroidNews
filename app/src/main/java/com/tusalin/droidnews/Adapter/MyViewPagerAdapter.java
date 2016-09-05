package com.tusalin.droidnews.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tusalin.droidnews.Fragment.TabFragment;

/**
 * Created by tusalin on 9/4/2016.
 */

public class MyViewPagerAdapter extends FragmentPagerAdapter {

    private String[] titles;
    @Override
    public Fragment getItem(int position) {
        return new TabFragment().newInstance(position);
    }

    public MyViewPagerAdapter(FragmentManager fm,String[] titles) {
        super(fm);
        this.titles = titles;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position%titles.length];
    }
}
