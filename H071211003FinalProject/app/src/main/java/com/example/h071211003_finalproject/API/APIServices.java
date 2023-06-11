package com.example.h071211003_finalproject.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIServices {
    @GET("movie/now_playing?")
    Call<MoviesResponse> getMovies (@Query("api_key") String movies);
    @GET("tv/top_rated?")
    Call<TvShowsResponse> getTvShows(@Query("api_key") String tvshows);

}
