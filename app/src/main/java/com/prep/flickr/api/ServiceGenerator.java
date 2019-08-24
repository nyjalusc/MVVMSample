package com.prep.flickr.api;

import com.prep.flickr.api.requests.SearchRequest;
import com.prep.flickr.utils.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Responsible for instantiating OkHttp client, Retrofit and the {@link SearchRequest} class
 */
public class ServiceGenerator {

    private static final OkHttpClient httpClient;

    private static final Retrofit.Builder retrofitBuilder;

    private static final Retrofit retrofit;

    private static final SearchRequest searchRequest;

    static {
        // Add logging to make it easy to debug
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.level(HttpLoggingInterceptor.Level.BASIC);
        httpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        // Generate retrofit client
        retrofitBuilder = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create());

        retrofit = retrofitBuilder.build();

        // Generate the Request class
        searchRequest = retrofit.create(SearchRequest.class);
    }

    public static SearchRequest getSearchRequest() {
        return searchRequest;
    }
}
