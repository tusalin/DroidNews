package com.tusalin.droidnews.Network;

import com.tusalin.droidnews.Bean.GankNews;
import com.tusalin.droidnews.Callback.RetrofitCallBack;
import com.tusalin.droidnews.GankUrl;
import com.tusalin.droidnews.MyApplication;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by tusalin on 9/6/2016.
 */

public class GankRetrofit {

     static Interceptor interceptor = new Interceptor() {
         @Override
         public okhttp3.Response intercept(Chain chain) throws IOException {
             CacheControl cacheControl = new CacheControl.Builder()
                     .maxAge(4, TimeUnit.HOURS)
                     .maxStale(4,TimeUnit.HOURS)
                     .build();
             Request request = chain.request();
//             request.newBuilder()
//                     .cacheControl(cacheControl)
//                     .build();
             okhttp3.Response response = chain.proceed(request);

             return response.newBuilder()
                     .header("Cache-Control","public,max-age="+60*60*24)
//                     .removeHeader("Pragma")
                     .build();
         }
     };

    static Cache cache = new Cache(new File(MyApplication.getContext().getCacheDir(),"picasso-cache"),
            1024 * 1024 * 10);
   static OkHttpClient okHttpClient = new OkHttpClient.Builder()
           .addNetworkInterceptor(interceptor)
           .cache(cache)
           .build();

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(GankUrl.GANK_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build();

    public static void getAllResult(final String keyType, final int count, final int pageIndex,
                                    final RetrofitCallBack<GankNews> retrofitCallBack){
        Call<GankNews> call = retrofit.create(GankService.class).getGankNews(keyType,count,pageIndex);
        call.enqueue(new Callback<GankNews>() {
            @Override
            public void onResponse(Call<GankNews> call, Response<GankNews> response) {
                if(response.isSuccessful()){
                    retrofitCallBack.retrofitSuccess(keyType,response.body());
                }else {
                    retrofitCallBack.retrofitFailure(keyType,"call.enqueue onSuccess but response not isSuccesssful");
                }
            }

            @Override
            public void onFailure(Call<GankNews> call, Throwable t) {
                retrofitCallBack.retrofitFailure(keyType,"onFailure:" + t.getMessage());
            }
        });
    }

}
