package com.prep.flickr.api;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.prep.flickr.api.responses.FlickrPhotosResponse;
import com.prep.flickr.api.responses.FlickrSearchResponse;
import com.prep.flickr.executors.AppExecutors;
import com.prep.flickr.models.FlickrPhoto;
import com.prep.flickr.utils.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class SearchRemoteDataSource {
    private static final String REST_API_METHOD = "flickr.photos.search";
    private static final String REST_API_RESPONSE_FORMAT = "json";
    private static final String REST_API_EXTRAS = "url_s";
    private static final String REST_API_NOJSON_CALLBACK = "1";

    private MutableLiveData<FlickrPhotosResponse> mPhotosResponse;

    private SearchPhotosRunnable mSearchPhotosRunnable;

    private static SearchRemoteDataSource instance;

    private MutableLiveData<Boolean> mIsQueryExhausted;

    private SearchRemoteDataSource() {
        mPhotosResponse = new MutableLiveData<>();
        mIsQueryExhausted = new MutableLiveData<>();
    }

    public static SearchRemoteDataSource getInstance() {
        if (instance == null) {
            synchronized (SearchRemoteDataSource.class) {
                instance = new SearchRemoteDataSource();
            }
        }
        return instance;
    }

    public LiveData<FlickrPhotosResponse> getPhotosResponse() {
        return mPhotosResponse;
    }

    public void searchPhotos(String query, int pageNumber) {
        // Use a new Runnable every time to execute a request
        if (mSearchPhotosRunnable != null) {
            mSearchPhotosRunnable = null;
        }

        mSearchPhotosRunnable = new SearchPhotosRunnable(query, pageNumber);

        final Future handler = AppExecutors.getInstance().networkIO().submit(mSearchPhotosRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // Will cancel the thread after 3 seconds
                handler.cancel(true);
            }
        }, Constants.NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    public LiveData<Boolean> isQueryExhausted() {
        return mIsQueryExhausted;
    }

    private class SearchPhotosRunnable implements Runnable {
        private String query;
        private String pageNumber;
        private boolean cancelRequest;

        public SearchPhotosRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = String.valueOf(pageNumber);
            this.cancelRequest = false;
        }

        @Override
        public void run() {
            try {
                Response<FlickrSearchResponse> searchResponseResponse = searchPhotos().execute();

                // If someone cancelled the request in the meantime do nothing
                if (cancelRequest) {
                    return;
                }

                if (searchResponseResponse.code() == 200) { // Success
                    if (searchResponseResponse.body() != null) {
                        Log.d("XXX", "run: " + searchResponseResponse.body().getPhotosResponse());
                        if (pageNumber.equals("1")) {
                            // First response should directly update the LiveData
                            mPhotosResponse.postValue(searchResponseResponse.body().getPhotosResponse());
                        } else {
                            // Compose the result and update the liveData otherwise we will lose old data
                            mPhotosResponse.postValue(combine(mPhotosResponse.getValue(), searchResponseResponse.body().getPhotosResponse()));
                        }

                        // Check if we have reached the last page
                        if (pageNumber.equals(searchResponseResponse.body().getPhotosResponse().getPages())) {
                            mIsQueryExhausted.postValue(true);
                        }
                    } else {
                        // Response with no data
                        mPhotosResponse.postValue(null);
                        mIsQueryExhausted.postValue(true);
                    }
                } else { // Failure
                    String error = searchResponseResponse.errorBody().string();
                    Log.d("XXX", "run: " + error);
                    // TODO: Create a wrapper dataholder for the UI. Posting null is not a good pattern
                    // We post null for errors
                    mPhotosResponse.postValue(null);
                    mIsQueryExhausted.postValue(true);
                }

            } catch (IOException e) {
                e.printStackTrace();
                // TODO: Create a wrapper dataholder for the UI. Posting null is not a good pattern
                // We post null for errors
                mPhotosResponse.postValue(null);
                mIsQueryExhausted.postValue(true);
            }
        }

        private Call<FlickrSearchResponse> searchPhotos() {
            return ServiceGenerator.getSearchRequest().searchPhotos(
                    REST_API_METHOD,
                    Constants.API_KEY,
                    query,
                    pageNumber,
                    REST_API_EXTRAS,
                    REST_API_RESPONSE_FORMAT,
                    REST_API_NOJSON_CALLBACK
                    );
        }

        private FlickrPhotosResponse combine(FlickrPhotosResponse original, FlickrPhotosResponse newlyFetched) {
            List<FlickrPhoto> combinedPhotos = new ArrayList<>(original.getPhotos());
            combinedPhotos.addAll(newlyFetched.getPhotos());
            return new FlickrPhotosResponse(newlyFetched.getPage(), newlyFetched.getPages(), newlyFetched.getPageSize(), newlyFetched.getTotal(), combinedPhotos);
        }

        private void cancelRequest() {
            cancelRequest = true;
        }
    }
}
