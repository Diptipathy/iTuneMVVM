package com.example.altimetrictest.adapter;
import com.example.altimetrictest.model.SearchItems;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class SearchItemComparator implements Comparator<SearchItems> {

    public static final int SORT_RELEASEDATE_ASCENDING = 0;
    public static final int SORT_RELESEDATE_DESCENDING = 1;
    public static final int SORT__COLLECTIONNAME_DESC = 2;
    public static final int SORT__TRACKNAME_DESC = 3;
    public static final int SORT__ARTISTNAME_DESC = 4;
    public static final int SORT__COLLECTIONPRICE_DESC = 5;

    public static final int SORT_DEFAULT = SORT_RELEASEDATE_ASCENDING;
    private int sortType = SORT_DEFAULT;


    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);

    public SearchItemComparator(int sortType){
        this.sortType = sortType;
    }

    @Override
    public int compare(SearchItems s1, SearchItems s2) {
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = sdf.parse(s1.getReleaseDate());
            d2 = sdf.parse(s2.getReleaseDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(sortType == SORT_RELEASEDATE_ASCENDING)
        {
            return (d1.getTime() > d2.getTime() ? 1 : -1);
        }
        else if(sortType == SORT_RELESEDATE_DESCENDING)
        {
            return (d1.getTime() > d2.getTime() ? -1 : 1);
        }
        else if(sortType == SORT__COLLECTIONNAME_DESC)
        {
            return s1.getCollectionName().compareTo(s2.getCollectionName());
        }
        else if(sortType == SORT__TRACKNAME_DESC)
        {
            return s1.getTrackName().compareTo(s2.getTrackName());
        }
        else if(sortType == SORT__ARTISTNAME_DESC){
            return s1.getArtistName().compareTo(s2.getArtistName());
        }
        else if(sortType == SORT__COLLECTIONPRICE_DESC){
            return (int) (s1.getCollectionPrice() - s2.getCollectionPrice());
        }
        else
            return 0;
    }
}
