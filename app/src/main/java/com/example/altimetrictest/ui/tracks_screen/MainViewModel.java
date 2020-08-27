package com.example.altimetrictest.ui.tracks_screen;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.altimetrictest.model.SearchResults;
import com.example.altimetrictest.ui.tracks_screen.MainRepository;

public class MainViewModel extends ViewModel {

    private MainRepository repository = new MainRepository();

    public LiveData<SearchResults> getAlbums(){
        return repository.getAlbums();
    }
}
