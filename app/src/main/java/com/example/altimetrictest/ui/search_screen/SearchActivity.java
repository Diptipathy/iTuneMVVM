package com.example.altimetrictest.ui.search_screen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.altimetrictest.R;
import com.example.altimetrictest.ui.tracks_screen.MainActivity;

public class SearchActivity extends AppCompatActivity {

    EditText edtArtistname, edtTrackname, edtcollectionName, edtcollectionprice, edtrelesedate;
    Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        edtArtistname = findViewById(R.id.edtArtistName);
        edtcollectionName = findViewById(R.id.edtCollectionname);
        edtTrackname = findViewById(R.id.edtTrackName);
        edtcollectionprice = findViewById(R.id.edtCollectionname);
        edtrelesedate = findViewById(R.id.edtRelesedate);

        final String artistname = edtArtistname.getText().toString().trim();
        final String collectionname = edtcollectionName.getText().toString().trim();
        final String trackname = edtTrackname.getText().toString().trim();
        final String collectionPrice = edtcollectionprice.getText().toString().trim();
        final String relesedate = edtrelesedate.getText().toString();

        search = findViewById(R.id.Search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (artistname.length() > 0 && trackname.length() > 0) {
                    Intent intent = new Intent();
                    intent.putExtra("artistname", artistname);
                    intent.putExtra("collectionName", collectionname);
                    intent.putExtra("trackname", trackname);
                    intent.putExtra("collectionprice", collectionPrice);
                    intent.putExtra("relesedate", relesedate);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),"Please enter to search",Toast.LENGTH_LONG).show();
                }
            }

        });
    }

}


