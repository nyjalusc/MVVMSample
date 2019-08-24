package com.prep.flickr.repositories;

import androidx.lifecycle.LiveData;

import com.prep.flickr.api.SearchRemoteDataSource;
import com.prep.flickr.api.responses.FlickrPhotosResponse;

/**
 * Singleton Repo to fetch photos.
 * TODO: Add a local database.
 * TODO: Resolution of where to fetch the data from local vs remote datasource will be handled by this class.
 */
public class SearchRepository {
    private static SearchRepository instance;
    private SearchRemoteDataSource mSearchRemoteDataSource;

    private SearchRepository() {
        mSearchRemoteDataSource = SearchRemoteDataSource.getInstance();
    }

    public static SearchRepository getInstance() {
        if (instance == null) {
            synchronized (SearchRepository.class) {
                instance = new SearchRepository();
            }
        }
        return instance;
    }

    public LiveData<FlickrPhotosResponse> getPhotos() {
        return mSearchRemoteDataSource.getPhotosResponse();
    }

    public LiveData<Boolean> isQueryExhausted() {
        return mSearchRemoteDataSource.isQueryExhausted();
    }

    public void searchPhotos(String query, int pageNumber) {
        mSearchRemoteDataSource.searchPhotos(query, pageNumber);
    }

    public void cancelRequest() {
        mSearchRemoteDataSource.cancelRequest();
    }
}
