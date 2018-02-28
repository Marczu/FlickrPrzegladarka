package com.marcinmejner.flickrprzegladarka;

/**
 * Created by Marc on 13.01.2018.
 */

class Photo {

    private String mTitle;
    private String mAuthor;
    private String mAutohorId;
    private String mLink;
    private String mTags;
    private String mImage;

    public Photo(String title, String author, String autohorId, String link, String tags, String image) {
        mTitle = title;
        mAuthor = author;
        mAutohorId = autohorId;
        mLink = link;
        mTags = tags;
        mImage = image;
    }

    String getTitle() {
        return mTitle;
    }

    String getAuthor() {
        return mAuthor;
    }

    String getAutohorId() {
        return mAutohorId;
    }

    String getLink() {
        return mLink;
    }

    String getTags() {
        return mTags;
    }

    String getImage() {
        return mImage;
    }


    @Override
    public String toString() {
        return "Photo{" +
                "mTitle='" + mTitle + '\'' +
                ", mAuthor='" + mAuthor + '\'' +
                ", mAutohorId='" + mAutohorId + '\'' +
                ", mLink='" + mLink + '\'' +
                ", mTags='" + mTags + '\'' +
                ", mImage='" + mImage + '\'' +
                '}';
    }
}
