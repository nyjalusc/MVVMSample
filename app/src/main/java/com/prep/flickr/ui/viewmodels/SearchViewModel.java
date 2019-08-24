package com.prep.flickr.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.prep.flickr.api.responses.FlickrPhotosResponse;
import com.prep.flickr.repositories.SearchRepository;

public class SearchViewModel extends ViewModel {
    private SearchRepository searchRepository;

    public SearchViewModel() {
        this.searchRepository = SearchRepository.getInstance();
    }

    public LiveData<FlickrPhotosResponse> getPhotos() {
        return searchRepository.getPhotos();
    }

    public LiveData<Boolean> isQueryExhausted() {
        return searchRepository.isQueryExhausted();
    }

    public void searchPhotos(String query, int pageNumber) {
        searchRepository.searchPhotos(query, pageNumber);
    }
}
