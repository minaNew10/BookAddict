package com.example.bookaddict.Model;

/**
 * This class is a memberVariable of {@link VolumeInfo}}
 * */
public class ImageLinks {
    public String smallThumbnail;
    public String thumbnail;
    public String small;
    public String medium;
    public String large;
    public String extraLarge;

    public String getSmallThumbnail() {
        return smallThumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getSmall() {
        return small;
    }

    public String getMedium() {
        return medium;
    }

    public String getLarge() {
        return large;
    }

    public String getExtraLarge() {
        return extraLarge;
    }

    public String getImageForThumbnail(){
     return smallThumbnail != null ? smallThumbnail :   thumbnail != null ? thumbnail : small != null ? small: medium;
    }
}
