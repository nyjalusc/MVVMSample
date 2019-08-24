package com.prep.flickr.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.prep.flickr.R;
import com.prep.flickr.api.ServiceGenerator;
import com.prep.flickr.api.responses.FlickrSearchResponse;
import com.prep.flickr.ui.viewmodels.SearchViewModel;
import com.prep.flickr.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {

    private SearchViewModel mViewModel;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        // TODO: Use the ViewModel

        // method=flickr.photos.search&api_key=675894853ae8ec6c242fa4c077bcf4a0&text=dogs&extras=url_s&format=json&nojsoncallback=1
        Call<FlickrSearchResponse> call = ServiceGenerator.getSearchRequest().searchPhotos("flickr.photos.search", Constants.API_KEY, "dogs", "url_s", "json", "1");
        call.enqueue(new Callback<FlickrSearchResponse>() {
            @Override
            public void onResponse(Call<FlickrSearchResponse> call, Response<FlickrSearchResponse> response) {
                FlickrSearchResponse flickrSearchResponse = response.body();
                if (flickrSearchResponse != null) {
                    Log.d("XXX", "onResponse: Found " + flickrSearchResponse.getPhotosResponse());
                } else {
                    Log.d("XXX", "onResponse: Success But BODY is NULL");
                }
            }

            @Override
            public void onFailure(Call<FlickrSearchResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
