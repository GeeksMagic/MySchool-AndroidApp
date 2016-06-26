package com.gmt.myschool.api;


import android.util.Log;

import com.google.gson.annotations.Expose;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Barun on 01-03-2016.
 */
public class RetrofitInterceptor implements Interceptor {
    private static RetrofitInterceptor interceptor = new RetrofitInterceptor();
    @Expose
    String token;

    private RetrofitInterceptor() {

    }

    public static RetrofitInterceptor getInterceptor() {
        return interceptor;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request request = original.newBuilder()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .method(original.method(), original.body())
                .build();

        Log.d("TOKEN", "token got::" + token);
        return chain.proceed(request);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
