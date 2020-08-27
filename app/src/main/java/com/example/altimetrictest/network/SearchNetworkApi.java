package com.example.altimetrictest.network;

import com.example.altimetrictest.model.SearchResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface SearchNetworkApi {

    @GET
    Call<SearchResults> getSearchresults(@Url String url);

}
