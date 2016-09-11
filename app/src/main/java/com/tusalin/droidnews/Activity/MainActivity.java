package com.tusalin.droidnews.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.tusalin.droidnews.Fragment.HomeFragment;
import com.tusalin.droidnews.Fragment.TechnologyFragment;
import com.tusalin.innews.R;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerlayout;
    private Toolbar toobar;
    private ActionBarDrawerToggle drawerToggle;
    private HomeFragment homeFragment;
    private NavigationView navigationview;
    private TechnologyFragment technologyFragment;


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
                android.support.v4.app.Fragment initFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_home);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()){
                    case R.id.menu_home:
                        if (initFragment != null){
                            getSupportFragmentManager().beginTransaction().show(initFragment).commit();
                        }else {
                            homeFragment = new HomeFragment();
                            fragmentTransaction.replace(R.id.framelayout,homeFragment);
                            toobar.setTitle("Gank News");
                        }
                        break;
                    case R.id.menu_technology:
                        if (initFragment != null){
                            getSupportFragmentManager().beginTransaction().hide(initFragment).commit();
                        }
                        technologyFragment = new TechnologyFragment();
                        fragmentTransaction.replace(R.id.framelayout,technologyFragment);
//                        fragmentTransaction.show(technologyFragment);
                        toobar.setTitle("Technology");
                        break;
                }
                fragmentTransaction.commit();
                item.setChecked(true);
                drawerlayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_setting:
                Snackbar.make(drawerlayout,"click setting",Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.menu_about:
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
                Snackbar.make(drawerlayout,"ckick about",Snackbar.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
