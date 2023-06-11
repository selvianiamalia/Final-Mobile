package com.example.h071211003_finalproject.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Favorites implements Parcelable {
    private String title;
    private String imagePoster;
    private String ReleaseDate;
    private int id;
    private String backdrop_path;
    private String overview;
    private String vote_average;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagePoster() {
        return imagePoster;
    }

    public void setImagePoster(String imagePoster) {
        this.imagePoster = imagePoster;
    }

    public String getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        ReleaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public Favorites() {
    }

    protected Favorites(Parcel in) {
        title = in.readString();
        imagePoster = in.readString();
        ReleaseDate = in.readString();
        id = in.readInt();
        backdrop_path = in.readString();
        overview = in.readString();
        vote_average = in.readString();
    }

    public static final Creator<Favorites> CREATOR = new Creator<Favorites>() {
        @Override
        public Favorites createFromParcel(Parcel in) {
            return new Favorites(in);
        }

        @Override
        public Favorites[] newArray(int size) {
            return new Favorites[size];
        }
    };

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
