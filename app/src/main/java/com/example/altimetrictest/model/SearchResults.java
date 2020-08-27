package com.example.altimetrictest.model;

import java.util.Arrays;
import java.util.List;

public class SearchResults {

    private int resultCount;
    private List<SearchItems> results ;

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public List<SearchItems> getResults() {
        return results;
    }

    public void setResults(SearchItems[] results) {
        this.results = Arrays.asList(results);
    }

}
