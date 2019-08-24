package com.prep.flickr.api;

import com.prep.flickr.api.requests.SearchRequest;
import com.prep.flickr.utils.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static final OkHttpClient httpClient;

    private static final Retrofit.Builder retrofitBuilder;

    private static final Retrofit retrofit;

    private static final SearchRequest searchRequest;

    static {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        httpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        retrofitBuilder = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create());

        retrofit = retrofitBuilder.build();

        searchRequest = retrofit.create(SearchRequest.class);
    }

    public static SearchRequest getSearchRequest() {
        return searchRequest;
    }
}
