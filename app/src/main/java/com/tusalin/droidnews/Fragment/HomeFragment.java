package com.tusalin.droidnews.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tusalin.droidnews.Adapter.HomeAdapter;
import com.tusalin.droidnews.FragmentType;
import com.tusalin.innews.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tusalin on 9/4/2016.
 */

public class HomeFragment extends Fragment {

    private TabLayout tablayout;
    private List<String> tabTitle = new ArrayList<>();
    private ViewPager viewPager;
    private HomeAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tablayout = (TabLayout) view.findViewById(R.id.tablayout);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(3);
        if (viewPager!=null){
            setupViewPager();
        }

        tablayout.setupWithViewPager(viewPager);
        tablayout.setTabMode(TabLayout.MODE_FIXED);
    }

    private void setupViewPager() {
        HomeAdapter homeAdapter = new HomeAdapter(getChildFragmentManager());
        homeAdapter.addFragment(new ArticalFragment(FragmentType.Android),"Andriod");
        homeAdapter.addFragment(new ArticalFragment(FragmentType.xiatuijian),"Tuijian");
        homeAdapter.addFragment(new ArticalFragment(FragmentType.tuozhanziyuan),"tuozhan");
        viewPager.setAdapter(homeAdapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

}
