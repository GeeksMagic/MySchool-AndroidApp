package com.gmt.myschool.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIManager {
    private static int READ_TIMEOUT = 120;
    private static RetrofitAPIService service = null;
    public static final String DOMAIN_URL = "http://myschool.us-west-2.elasticbeanstalk.com/";

    private APIManager() {
    }

    public static RetrofitAPIService getRetrofitServiceInstance() {
        if (service == null) {
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(RetrofitInterceptor.getInterceptor())
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    .connectTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    .build();
            Gson gson = new GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation()
                    .create();

            Retrofit retrofit = new Retrofit.Builder().client(client)
                    .baseUrl(DOMAIN_URL).addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            service = retrofit.create(RetrofitAPIService.class);
        }
        return service;
    }
}
