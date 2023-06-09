package com.example.h071211003_finalproject.Models;

import com.google.gson.annotations.SerializedName;

public class TvShows {
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

}
