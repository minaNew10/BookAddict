package com.example.bookaddict.Model;

/**
 * This class is a memberVariable of @Link{{@link Item}}
 * */
public class VolumeInfo {
    public String title;
    public String mainCategory;
    public String subtitle;
    public double averageRating;
    public int ratingsCount;
    public String[] authors;
    public String publisher;
    public String publishedDate;
    public ReadingModes readingModes;
    public int pageCount;
    public String printType;
    public String[] categories;
    public ImageLinks imageLinks;
    public String language;
    public String previewlink;
    public String infoLink;
    public String canonicalVolumeLink;
    public String maturityRating;
    public String description;


    public String getTitle() {
        return title;
    }

    public String[] getAuthors() {
        return authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public ReadingModes getReadingModes() {
        return readingModes;
    }

    public int getPageCount() {
        return pageCount;
    }

    public String getPrintType() {
        return printType;
    }

    public String[] getCategories() {
        return categories;
    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    public String getLanguage() {
        return language;
    }

    public String getPreviewlink() {
        return previewlink;
    }

    public String getInfoLink() {
        return infoLink;
    }

    public String getCanonicalVolumeLink() {
        return canonicalVolumeLink;
    }
}
