package com.tusalin.droidnews.Network;

import android.util.Log;

import com.tusalin.droidnews.Bean.GankNews;
import com.tusalin.droidnews.Callback.RetrofitCallBack;
import com.tusalin.droidnews.GankUrl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by tusalin on 9/6/2016.
 */

public class DefaultRetrofit {
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(GankUrl.GANK_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static void getAllResult(final String keyType, final int count, final int pageIndex,
                                    final RetrofitCallBack<GankNews> retrofitCallBack){
        Call<GankNews> call = retrofit.create(GankService.class).getGankNews(keyType,count,pageIndex);
        call.enqueue(new Callback<GankNews>() {
            @Override
            public void onResponse(Call<GankNews> call, Response<GankNews> response) {

                Log.d("keyTypee",keyType);
                Log.d("count",count+"");
                Log.d("pageIndex",pageIndex+"");
                Log.d("response",response.body().toString());
                if(response.isSuccessful()){
                    retrofitCallBack.retrofitSuccess(keyType,response.body());
                }else {
                    retrofitCallBack.retrofitFailure(keyType,"Data Response Error");
                }
            }

            @Override
            public void onFailure(Call<GankNews> call, Throwable t) {
                retrofitCallBack.retrofitFailure(keyType,"Request Failed");
            }
        });
    }
}
