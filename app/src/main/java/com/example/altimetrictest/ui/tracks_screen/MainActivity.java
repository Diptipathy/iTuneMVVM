package com.example.altimetrictest.ui.tracks_screen;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.altimetrictest.R;
import com.example.altimetrictest.adapter.SearchAdapter;
import com.example.altimetrictest.adapter.SearchItemComparator;
import com.example.altimetrictest.model.SearchItems;
import com.example.altimetrictest.model.SearchResults;
import com.example.altimetrictest.ui.search_screen.SearchActivity;

import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int REQUEST_CODE_SEARCH_QUERY = 20;

    private MainViewModel viewModel;
    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        recyclerView = findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        viewModel.getAlbums().observe(this, new Observer<SearchResults>() {
            @Override
            public void onChanged(@Nullable SearchResults searchResults) {
                if (searchResults != null) {
                    HashSet<SearchItems> hashSet = new HashSet<>();
                    hashSet.addAll(searchResults.getResults());
                    searchAdapter = new SearchAdapter(hashSet);
                    recyclerView.setAdapter(searchAdapter);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.appSearchBar:
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SEARCH_QUERY);
                break;
            case R.id.menuArtistname:
                searchAdapter.sort(SearchItemComparator.SORT__ARTISTNAME_DESC);
                break;

            case R.id.menuCollectionPrice:
                searchAdapter.sort(SearchItemComparator.SORT__COLLECTIONPRICE_DESC);
                break;

            case R.id.menuCollectionName:
                searchAdapter.sort(SearchItemComparator.SORT__COLLECTIONNAME_DESC);
                break;

            case R.id.menuTrackName:
                searchAdapter.sort(SearchItemComparator.SORT__TRACKNAME_DESC);
                break;

        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check that it is the SecondActivity with an OK result
        if (requestCode == REQUEST_CODE_SEARCH_QUERY && resultCode == RESULT_OK) {
            String artistname = data.getStringExtra("artistname");
            String collectionName = data.getStringExtra("collectionName");
            String trackname = data.getStringExtra("trackname");
            String collectionprice = data.getStringExtra("collectionprice");
            String relesedate = data.getStringExtra("relesedate");
            //searchAdapter.search(artistname,trackname,collectionName,collectionprice,relesedate);
        }
    }
}



