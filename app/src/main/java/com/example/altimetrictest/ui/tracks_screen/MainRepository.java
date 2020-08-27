package com.example.altimetrictest.ui.tracks_screen;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.altimetrictest.model.Constants;
import com.example.altimetrictest.model.SearchResults;
import com.example.altimetrictest.network.RetrofitClient;
import com.example.altimetrictest.network.SearchNetworkApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {

    private String TAG = "main_repository";

    private MutableLiveData<SearchResults> liveData = new MutableLiveData<>();

    public LiveData<SearchResults> getAlbums() {
        SearchNetworkApi searchNetworkApi = RetrofitClient.getInstance().getApi();
        Call<SearchResults> call = searchNetworkApi.getSearchresults(Constants.URL);
        call.enqueue(new Callback<SearchResults>() {
            @Override
            public void onResponse(Call<SearchResults> call, Response<SearchResults> response) {
                Log.d(TAG,"onResponse inside");
                SearchResults searchResults = response.body();
                liveData.postValue(searchResults);
            }

            @Override
            public void onFailure(Call<SearchResults> call, Throwable t) {
                Log.d(TAG,"on failure inside");
                Log.d("Error", t.getMessage());
                liveData.postValue(null);
            }
        });
        return liveData;
    }
}
