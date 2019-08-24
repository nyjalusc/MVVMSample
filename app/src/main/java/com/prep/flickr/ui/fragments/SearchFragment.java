package com.prep.flickr.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.prep.flickr.R;
import com.prep.flickr.api.responses.FlickrPhotosResponse;
import com.prep.flickr.ui.viewmodels.SearchViewModel;

public class SearchFragment extends Fragment {

    private SearchViewModel mViewModel;
    private Toolbar mToolbar;
    private SearchView mSearchView;
    private ProgressBar mProgressBar;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        mSearchView = view.findViewById(R.id.search_view);
        mToolbar = view.findViewById(R.id.toolbar);
        mProgressBar = view.findViewById(R.id.progress_loading);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        initSearchView();
        return view;
    }

    private void initSearchView() {
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Clear focus as soon as query is submitted otherwise the focus stays on
                // and if we click back the click will be used to remove focus and not actually
                // perform back navigation
                mSearchView.clearFocus();

                mViewModel.searchPhotos(query, 1);
                setProgressBarVisibility(true);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        subscribeObservers();
    }

    private void subscribeObservers() {
        mViewModel.getPhotos().observe(getViewLifecycleOwner(), new Observer<FlickrPhotosResponse>() {
            @Override
            public void onChanged(FlickrPhotosResponse flickrPhotosResponse) {
                setProgressBarVisibility(false);
            }
        });

        mViewModel.isQueryExhausted().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

            }
        });
    }

    private void setProgressBarVisibility(boolean show) {
        int visibility = (show ? View.VISIBLE : View.GONE);
        mProgressBar.setVisibility(visibility);
    }
}
