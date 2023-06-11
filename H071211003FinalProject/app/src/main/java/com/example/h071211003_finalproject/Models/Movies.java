package com.example.h071211003_finalproject.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Movies implements Parcelable {

    @SerializedName("title")
    private String title;
    @SerializedName("poster_path")
    private String imagePoster;
    @SerializedName("release_date")
    private String ReleaseDate;
    @SerializedName("id")
    private int id;
    @SerializedName("backdrop_path")
    private String backdrop_path;
    @SerializedName("overview")
    private String overview;
    @SerializedName("vote_average")
    private String vote_average;

    protected Movies(Parcel in) {
        title = in.readString();
        imagePoster = in.readString();
        ReleaseDate = in.readString();
        id = in.readInt();
        backdrop_path = in.readString();
        overview = in.readString();
        vote_average = in.readString();
    }

    public static final Creator<Movies> CREATOR = new Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel in) {
            return new Movies(in);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };

    public String getReleaseDate() {
        return ReleaseDate;
    }

    public String getTitle() {
        return title;
    }

    public String getImagePoster() {
        return imagePoster;
    }

    public int getId() {
        return id;
    }


    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getOverview() {
        return overview;
    }

    public String getVote_average() {
        return vote_average;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(imagePoster);
        parcel.writeString(ReleaseDate);
        parcel.writeInt(id);
        parcel.writeString(backdrop_path);
        parcel.writeString(overview);
        parcel.writeString(vote_average);
    }
}
