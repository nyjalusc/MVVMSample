package com.prep.flickr.api.responses;

import com.google.gson.annotations.SerializedName;

public class FlickrSearchResponse {
    /**
     * Sample Model
     * {"photos": FlickrPhotosResponse, "stat": "ok"}
     */

    @SerializedName("photos")
    FlickrPhotosResponse photosResponse;

    String stat;
}
