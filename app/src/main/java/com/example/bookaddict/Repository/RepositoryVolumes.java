package com.example.bookaddict.Repository;

import com.example.bookaddict.Network.RetrofitClient;

import io.reactivex.Single;
import okhttp3.ResponseBody;

public class RepositoryVolumes {
    private static RepositoryVolumes repositoryVolumes;

    public static RepositoryVolumes getRepositoryVolumesInstance() {
        return repositoryVolumes == null ? new RepositoryVolumes() : repositoryVolumes;
    }

    private RepositoryVolumes(){

    }

    public Single<ResponseBody> getResponse(String q){
        Single<ResponseBody> single = RetrofitClient.getApiService().getResponse(q);
        return single;
    }
}