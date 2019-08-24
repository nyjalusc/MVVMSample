package com.prep.flickr.api.responses;

import com.google.gson.annotations.SerializedName;
import com.prep.flickr.models.FlickrPhoto;

import java.util.List;

public class FlickrPhotosResponse {
    /**
     * Sample Response:
     * { "page": 1, "pages": "3527", "perpage": 100, "total": "352674", "photo": [..]}
     */

    int page;

    String pages;

    @SerializedName("perpage")
    int pageSize;

    String total;

    @SerializedName("photo")
    List<FlickrPhoto> photos;

    public FlickrPhotosResponse(int page, String pages, int perpage, String total, List<FlickrPhoto> photos) {
        this.page = page;
        this.pages = pages;
        this.pageSize = perpage;
        this.total = total;
        this.photos = photos;
    }

    public int getPage() {
        return page;
    }

    public String getPages() {
        return pages;
    }

    public int getPageSize() {
        return pageSize;
    }

    public String getTotal() {
        return total;
    }

    public List<FlickrPhoto> getPhotos() {
        return photos;
    }

    @Override
    public String toString() {
        return "FlickrPhotosResponse{" +
                "page=" + page +
                ", pages='" + pages + '\'' +
                ", pageSize=" + pageSize +
                ", total='" + total + '\'' +
                ", photos=" + photos +
                '}';
    }
}
