package com.example.bookaddict.Network;

import com.example.bookaddict.BuildConfig;
import com.example.bookaddict.Model.VolumesResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface  ApiService {
    @GET("volumes?key=" + BuildConfig.API_KEY)
    Single<VolumesResponse> getResponse(@Query("q")String q);

    @GET("volumes?key=" + BuildConfig.API_KEY)
    Single<VolumesResponse> getTenItems(@Query("q")String q, @Query("startIndex")int startIndex,@Query("maxResults")int resultsCount);

    @GET("volumes?key=" + BuildConfig.API_KEY)
    Observable<VolumesResponse> getResponseInObs(@Query("q")String q);
}
