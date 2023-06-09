package com.example.h071211003_finalproject;

import com.example.h071211003_finalproject.Models.TvShows;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowsResponse extends TvShows {

    @SerializedName("results")
    private final List<TvShows> tvShows;

    public TvShowsResponse(List<TvShows> tvShows) {
        this.tvShows = tvShows;
    }

    public List<TvShows> getTvShows() {
        return tvShows;
    }

}
