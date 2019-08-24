package com.prep.flickr.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class FlickrPhoto implements Parcelable {
    /**
     *  Sample Model:
     *  {
     *      "id": "48609964126", "owner": "36812749@N00", "secret": "0fdf86aef9",
     *      "server": "65535", "farm": 66, "title": "An afternoon nap", "ispublic": 1,
     *      "isfriend": 0, "isfamily": 0,
     *      "url_s": "https:\/\/live.staticflickr.com\/65535\/48609964126_0fdf86aef9_m.jpg",
     *      "height_s": "160", "width_s": "240"
     *  }
     */

    private String id;

    private String owner;

    private String secret;

    private String server;

    private int farm;

    private String title;

    private int ispublic;

    private int isfriend;

    private int isfamily;

    @SerializedName("url_s")
    private String url;

    @SerializedName("height_s")
    private String height;

    @SerializedName("width_s")
    private String width;

    public FlickrPhoto(String id, String owner, String secret, String server,
                       int farm, String title, int ispublic, int isfriend,
                       int isfamily, String url, String height, String width) {
        this.id = id;
        this.owner = owner;
        this.secret = secret;
        this.server = server;
        this.farm = farm;
        this.title = title;
        this.ispublic = ispublic;
        this.isfriend = isfriend;
        this.isfamily = isfamily;
        this.url = url;
        this.height = height;
        this.width = width;
    }

    protected FlickrPhoto(Parcel in) {
        id = in.readString();
        owner = in.readString();
        secret = in.readString();
        server = in.readString();
        farm = in.readInt();
        title = in.readString();
        ispublic = in.readInt();
        isfriend = in.readInt();
        isfamily = in.readInt();
        url = in.readString();
        height = in.readString();
        width = in.readString();
    }

    public String getLargeFormatUrl() {
        return String.format("https://farm%s.staticflickr.com/%s/%s_%s_b.png", getFarm(), getServer(), getId(), getSecret());
    }

    public static final Creator<FlickrPhoto> CREATOR = new Creator<FlickrPhoto>() {
        @Override
        public FlickrPhoto createFromParcel(Parcel in) {
            return new FlickrPhoto(in);
        }

        @Override
        public FlickrPhoto[] newArray(int size) {
            return new FlickrPhoto[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public String getSecret() {
        return secret;
    }

    public String getServer() {
        return server;
    }

    public int getFarm() {
        return farm;
    }

    public String getTitle() {
        return title;
    }

    public int getIspublic() {
        return ispublic;
    }

    public int getIsfriend() {
        return isfriend;
    }

    public int getIsfamily() {
        return isfamily;
    }

    public String getUrl() {
        return url;
    }

    public String getHeight() {
        return height;
    }

    public String getWidth() {
        return width;
    }

    @Override
    public String toString() {
        return "FlickrPhoto{" +
                "id='" + id + '\'' +
                ", owner='" + owner + '\'' +
                ", secret='" + secret + '\'' +
                ", server='" + server + '\'' +
                ", farm=" + farm +
                ", title='" + title + '\'' +
                ", ispublic=" + ispublic +
                ", isfriend=" + isfriend +
                ", isfamily=" + isfamily +
                ", url='" + url + '\'' +
                ", height='" + height + '\'' +
                ", width='" + width + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(owner);
        parcel.writeString(secret);
        parcel.writeString(server);
        parcel.writeInt(farm);
        parcel.writeString(title);
        parcel.writeInt(ispublic);
        parcel.writeInt(isfriend);
        parcel.writeInt(isfamily);
        parcel.writeString(url);
        parcel.writeString(height);
        parcel.writeString(width);
    }
}
