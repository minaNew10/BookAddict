package com.example.bookaddict.Repository;

import com.example.bookaddict.Model.VolumesResponse;
import com.example.bookaddict.Network.RetrofitClient;

import io.reactivex.Single;

public class RepositoryVolumes {
    private static RepositoryVolumes repositoryVolumes;

    public static RepositoryVolumes getRepositoryVolumesInstance() {
        return repositoryVolumes == null ? new RepositoryVolumes() : repositoryVolumes;
    }

    private RepositoryVolumes(){

    }

    public Single<VolumesResponse> getResponse(String q){
        Single<VolumesResponse> single = RetrofitClient.getApiService().getResponse(q);
        return single;
    }

    public Single<VolumesResponse> getTenItems(String q, int startIndex,int ResultsCount){
        Single<VolumesResponse> single = RetrofitClient.getApiService()
                .getTenItems(q,startIndex,ResultsCount);
        return single;
    }
}