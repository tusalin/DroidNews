package com.tusalin.droidnews.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tusalin on 9/4/2016.
 */

/*
*
*    This Adapter is used to associate tablayout with viwepager so that the tab can bee switched
*     by sliding
*
* */

public class HomeAdapter extends FragmentStatePagerAdapter {

    private List<String> mFragmentTitle = new ArrayList<>();
    private List<Fragment> mFragment = new ArrayList<>();

    public void addFragment(Fragment fragment,String fragmentTitle){
        mFragment.add(fragment);
        mFragmentTitle.add(fragmentTitle);
    }
    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    public HomeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitle.get(position);
    }
}
