package com.example.bookaddict.Network;

import android.provider.SyncStateContract;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {

    private static Retrofit retrofit;
    private static ApiService ApiService;

    private static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .client(buildClient())
                    .baseUrl("https://www.googleapis.com/books/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

        }
        return retrofit;
    }

    public static ApiService getApiService() {
        if (ApiService == null) {
            ApiService = getInstance().create(ApiService.class);
        }
        return ApiService;
    }

    private static OkHttpClient buildClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.level(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient
                .Builder()
                .addInterceptor(logging)
                .build();
    }

}