package com.tusalin.droidnews.Callback;

/**
 * Created by tusalin on 9/6/2016.
 */

public interface RetrofitCallBack<T> {
    public void retrofitSuccess(String keyType,T t);
    public void retrofitFailure(String keyType,String error);
}
