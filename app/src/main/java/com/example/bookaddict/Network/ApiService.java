package com.example.bookaddict.Network;

import com.example.bookaddict.BuildConfig;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("volumes?key=" + BuildConfig.API_KEY)
    Single<ResponseBody> getResponse(@Query("q")String q);

    @GET("volumes")
    Observable<ResponseBody> getResponseInObs();
}
