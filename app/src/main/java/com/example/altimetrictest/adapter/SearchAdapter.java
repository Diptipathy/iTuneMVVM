package com.example.altimetrictest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.altimetrictest.R;
import com.example.altimetrictest.model.SearchItems;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> implements Filterable {

    private List<SearchItems> mSearchItems;

    private List<String>filteredData = null;


    public SearchAdapter(HashSet<SearchItems> searchItems) {
        this.mSearchItems = new ArrayList<>();
        this.mSearchItems.addAll(searchItems);
        Collections.sort(this.mSearchItems, new SearchItemComparator(SearchItemComparator.SORT_DEFAULT));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.items, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        SearchItems items = (SearchItems) mSearchItems.get(position);
        viewHolder.textViewArtistNameTrackName.setText(items.getTrackName() + "(" + items.getArtistName() + ")");
        viewHolder.textViewCollectionNamePrice.setText(items.getCollectionName() + " - " + items.getCollectionPrice());
        viewHolder.textViewReleaseDate.setText(formatDate(String.valueOf(items.getReleaseDate())));
        viewHolder.artworkUrl100.setText(items.getArtworkUrl100());
    }

    public static String formatDate(String dateStr) {
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        DateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date formattedDate = null;
        try {
            formattedDate = inputFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDateStr = outputFormat.format(formattedDate);
        return formattedDateStr;
    }

    @Override
    public int getItemCount() {
        return mSearchItems.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewArtistNameTrackName, textViewCollectionNamePrice, artworkUrl100, textViewReleaseDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewArtistNameTrackName = itemView.findViewById(R.id.textViewArtistNameTrackName);
            textViewCollectionNamePrice = itemView.findViewById(R.id.textViewCollectionNamePrice);
            textViewReleaseDate = itemView.findViewById(R.id.textViewReleaseDate);
            artworkUrl100 = itemView.findViewById(R.id.textViewUrl);
        }
    }

    public void sort(int sortType) {
        Collections.sort(mSearchItems, new SearchItemComparator(sortType));
        notifyDataSetChanged();
    }
}
