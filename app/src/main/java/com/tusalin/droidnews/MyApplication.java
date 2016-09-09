package com.tusalin.droidnews;

import android.app.Application;
import android.content.Context;

/**
 * Created by tusalin on 9/9/2016.
 */

public class MyApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        mContext = getApplicationContext();
    }

    public static Context getContext(){
        return mContext;
    }
}
