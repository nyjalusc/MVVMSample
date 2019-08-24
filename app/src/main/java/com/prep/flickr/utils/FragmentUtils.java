package com.prep.flickr.utils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * Reference: https://github.com/sunaij/Android-NYTimes-Sample/blob/master/app/src/main/java/com/nytimes/articles/utils/FragmentUtils.java
 */
public class FragmentUtils {

    public static void replaceFragment(@NonNull AppCompatActivity activity, Fragment fragment, int fragmentContainer, boolean addToBackStack) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (addToBackStack)
            transaction.addToBackStack(fragment.getClass().getCanonicalName());

        transaction.replace(fragmentContainer, fragment, fragment.getClass().getCanonicalName());
        transaction.commit();
    }

}
