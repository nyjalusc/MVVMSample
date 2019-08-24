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

    public FlickrSearchResponse(FlickrPhotosResponse photosResponse, String stat) {
        this.photosResponse = photosResponse;
        this.stat = stat;
    }

    public FlickrPhotosResponse getPhotosResponse() {
        return photosResponse;
    }

    public String getStat() {
        return stat;
    }

    @Override
    public String toString() {
        return "FlickrSearchResponse{" +
                "photosResponse=" + photosResponse +
                ", stat='" + stat + '\'' +
                '}';
    }
}
