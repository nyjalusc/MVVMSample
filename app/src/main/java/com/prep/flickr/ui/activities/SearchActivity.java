package com.prep.flickr.ui.activities;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.prep.flickr.R;
import com.prep.flickr.ui.fragments.SearchFragment;
import com.prep.flickr.utils.FragmentUtils;

public class SearchActivity extends BaseActivity {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_search;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            SearchFragment searchFragment = SearchFragment.newInstance();
            FragmentUtils.replaceFragment(this, searchFragment, R.id.fragment_container, false);
        }
    }
}
