package com.amar.moviesearch.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class MovieSearchDetails implements Parcelable {

    @SerializedName("Title")
    private String title;
    @SerializedName("Poster")
    private String poster;
    @SerializedName("Year")
    private String year;

    public MovieSearchDetails() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.year);
        dest.writeString(this.poster);
    }

    protected MovieSearchDetails(Parcel in) {
        title = in.readString();
        poster = in.readString();
        year = in.readString();
    }

    public static final Creator<MovieSearchDetails> CREATOR = new Creator<MovieSearchDetails>() {
        @Override
        public MovieSearchDetails createFromParcel(Parcel in) {
            return new MovieSearchDetails(in);
        }

        @Override
        public MovieSearchDetails[] newArray(int size) {
            return new MovieSearchDetails[size];
        }
    };

}

