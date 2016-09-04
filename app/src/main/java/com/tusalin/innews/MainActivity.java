package com.tusalin.innews;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.tusalin.innews.Fragment.EntertainmentFragment;
import com.tusalin.innews.Fragment.HomeFragment;
import com.tusalin.innews.Fragment.TechnologyFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerlayout;
    private Toolbar toobar;
    private ActionBarDrawerToggle drawerToggle;
    private HomeFragment homeFragment;
    private NavigationView navigationview;
    private TechnologyFragment technologyFragment;
    private EntertainmentFragment entertainmentFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home);
        navigationview = (NavigationView) findViewById(R.id.navigation_view);
        drawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        toobar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toobar);
        toobar.setNavigationIcon(R.drawable.menu);
        setupNavigation();

    }

    public void setupNavigation(){
        drawerToggle = new ActionBarDrawerToggle(this,drawerlayout,toobar,
                R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerlayout.addDrawerListener(drawerToggle);
        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()){
                    case R.id.menu_home:
                        homeFragment = new HomeFragment();
                        fragmentTransaction.replace(R.id.framelaoyout,homeFragment);
                        toobar.setTitle("Tablayout");
                        break;
                    case R.id.menu_technology:
                        technologyFragment = new TechnologyFragment();
                        fragmentTransaction.replace(R.id.framelaoyout,technologyFragment);
                        toobar.setTitle("Technology");
                        break;
                    case R.id.menu_entertainment:
                        entertainmentFragment = new EntertainmentFragment();
                        fragmentTransaction.replace(R.id.framelaoyout,entertainmentFragment);
                        toobar.setTitle("Entertainment");
                        break;

                }
                fragmentTransaction.commit();
                item.setChecked(true);
                drawerlayout.closeDrawers();
                return true;
            }
        });
    }



}
