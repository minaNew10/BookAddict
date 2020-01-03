package com.example.bookaddict.Model;

import java.util.List;

/**
 * this class is the main received from the api
 * */
public class VolumesResponse {
    public int totalItems;
    public String kind;
    public List<Item> items;

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
