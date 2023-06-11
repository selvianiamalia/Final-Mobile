package com.example.h071211003_finalproject.API;

import com.example.h071211003_finalproject.Models.Movies;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesResponse {
    @SerializedName("results")
    private final List<Movies> movies;

    public MoviesResponse(List<Movies> movies) {
        this.movies = movies;
    }

    public List<Movies> getMovies() {
        return movies;
    }
}
