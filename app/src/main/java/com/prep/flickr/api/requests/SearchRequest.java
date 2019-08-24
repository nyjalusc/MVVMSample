package com.prep.flickr.api.requests;

import com.prep.flickr.api.responses.FlickrSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchRequest {

    @GET("services/rest")
    Call<FlickrSearchResponse> searchPhotos(
            @Query("method") String method,
            @Query("api_key") String apiKey,
            @Query("text") String text,
            @Query("url_s") String extras,
            @Query("format") String format,
            @Query("nojsoncallback") String nojsoncallback
    );
}
