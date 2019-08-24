package com.prep.flickr.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.prep.flickr.R;
import com.prep.flickr.api.responses.FlickrPhotosResponse;
import com.prep.flickr.ui.viewmodels.SearchViewModel;

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
        initObservers();

        mViewModel.searchPhotos("mole rat", 15);
    }

    private void initObservers() {
        mViewModel.getPhotos().observe(getViewLifecycleOwner(), new Observer<FlickrPhotosResponse>() {
            @Override
            public void onChanged(FlickrPhotosResponse flickrPhotosResponse) {

            }
        });

        mViewModel.isQueryExhausted().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

            }
        });
    }
}
