package com.prep.flickr.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.prep.flickr.api.responses.FlickrPhotosResponse;
import com.prep.flickr.repositories.SearchRepository;

public class SearchViewModel extends ViewModel {
    private SearchRepository searchRepository;
    private boolean mIsPerformingQuery;
    private String mQuery;
    private int mPageNumber;

    public SearchViewModel() {
        this.searchRepository = SearchRepository.getInstance();
        mIsPerformingQuery = false;
    }

    public LiveData<FlickrPhotosResponse> getPhotos() {
        return searchRepository.getPhotos();
    }

    public LiveData<Boolean> isQueryExhausted() {
        return searchRepository.isQueryExhausted();
    }

    public void searchPhotos(String query, int pageNumber) {
        mIsPerformingQuery = true;
        mQuery = query;
        mPageNumber = pageNumber;
        searchRepository.searchPhotos(query, pageNumber);
    }

    public void searchNextPage() {
        if (!mIsPerformingQuery
                && !isQueryExhausted().getValue()) {
            searchRepository.searchPhotos(mQuery, mPageNumber + 1);
        }
    }

    public boolean isPerformingQuery() {
        return mIsPerformingQuery;
    }

    public void setIsPerformingQuery(boolean isPerformingQuery) {
        mIsPerformingQuery = isPerformingQuery;
    }

    public void cancelRequest() {
        searchRepository.cancelRequest();
    }
}
