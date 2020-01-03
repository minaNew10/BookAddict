package com.example.bookaddict.Model;

/**
 * This class is the member variable of {@link VolumesResponse}*/
public class Item {
    public String kind;
    public String id;
    public String selfLink;
    public VolumeInfo volumeInfo;
    public SaleInfo saleInfo;
    public UserInfo userInfo;
    public AccessInfo accessInfo;
    public SearchInfo searchInfo;

    public String getKind() {
        return kind;
    }

    public String getId() {
        return id;
    }

    public String getSelfLink() {
        return selfLink;
    }

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public SaleInfo getSaleInfo() {
        return saleInfo;
    }

    public AccessInfo getAccessInfo() {
        return accessInfo;
    }

    public SearchInfo getSearchInfo() {
        return searchInfo;
    }
}
