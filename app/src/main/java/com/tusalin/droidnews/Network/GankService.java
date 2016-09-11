package com.tusalin.droidnews.Network;

import com.tusalin.droidnews.Bean.GankNews;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by tusalin on 9/6/2016.
 */

public interface GankService {
    @GET("data/{type}/{count}/{pageIndex}")
    Call<GankNews> getGankNews(@Path("type") String type,
                              @Path("count") int count,
                              @Path("pageIndex") int pageIndex
    );
}
