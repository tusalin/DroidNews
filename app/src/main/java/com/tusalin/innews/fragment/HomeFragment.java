package com.tusalin.innews.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tusalin.innews.Adapter.MyViewPagerAdapter;
import com.tusalin.innews.R;

/**
 * Created by tusalin on 9/4/2016.
 */

public class HomeFragment extends Fragment {

    private TabLayout tablayout;
    private String[] tabTitle = new String[]{"Tab1","Tab2","Tab3","Tablayout1","TabLayout2"};
    private ViewPager viewPager;
    private MyViewPagerAdapter adapter;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        tablayout = (TabLayout) view.findViewById(R.id.tablayout);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        adapter = new MyViewPagerAdapter(getFragmentManager(),tabTitle);


        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);
        /*for (int i = 0;i < tabTitle.length;i++){
            tablayout.addTab(tablayout.newTab().setText(tabTitle[i]));
        }*/
        tablayout.setTabMode(TabLayout.MODE_FIXED);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

}
