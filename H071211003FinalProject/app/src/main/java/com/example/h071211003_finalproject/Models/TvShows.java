package com.example.h071211003_finalproject.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class TvShows implements Parcelable {
    @SerializedName("name")
    private String name;
    @SerializedName("first_air_date")
    private String air_date;
    @SerializedName("poster_path")
    private String poster_path;
    @SerializedName("id")
    private int id;
    @SerializedName("backdrop_path")
    private String backdrop_path;
    @SerializedName("vote_average")
    private String vote_average;
    @SerializedName("overview")
    private String overview;

    public TvShows() {
    }

    protected TvShows(Parcel in) {
        name = in.readString();
        air_date = in.readString();
        poster_path = in.readString();
        id = in.readInt();
        backdrop_path = in.readString();
        vote_average = in.readString();
        overview = in.readString();
    }

    public static final Creator<TvShows> CREATOR = new Creator<TvShows>() {
        @Override
        public TvShows createFromParcel(Parcel in) {
            return new TvShows(in);
        }

        @Override
        public TvShows[] newArray(int size) {
            return new TvShows[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getAir_date() {
        return air_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public int getId() {
        return id;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getVote_average() {
        return vote_average;
    }

    public String getOverview() {
        return overview;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(air_date);
        parcel.writeString(poster_path);
        parcel.writeInt(id);
        parcel.writeString(backdrop_path);
        parcel.writeString(vote_average);
        parcel.writeString(overview);
    }
}
