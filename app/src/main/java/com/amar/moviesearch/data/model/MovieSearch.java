package com.amar.moviesearch.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieSearch implements Parcelable {

    @SerializedName("Search")
    private List<MovieSearchDetails> search;

    public MovieSearch() {
    }

    protected MovieSearch(Parcel in) {
        search = in.createTypedArrayList(MovieSearchDetails.CREATOR);
    }

    public List<MovieSearchDetails> getSearch() {
        return search;
    }

    public void setSearch(List<MovieSearchDetails> search) {
        this.search = search;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.search);
    }

    public static final Creator<MovieSearch> CREATOR = new Creator<MovieSearch>() {
        @Override
        public MovieSearch createFromParcel(Parcel in) {
            return new MovieSearch(in);
        }

        @Override
        public MovieSearch[] newArray(int size) {
            return new MovieSearch[size];
        }
    };
}
