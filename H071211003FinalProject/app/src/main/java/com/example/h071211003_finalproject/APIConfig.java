package com.example.h071211003_finalproject;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIConfig {
    private static final String urlAPI = "https://api.themoviedb.org/3/";
    private static final String apiKey = "6cecb972a34fc716d8fe66b2c5804139";

    public static APIServices getApiService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlAPI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(APIServices.class);
    }

    public static String getApiKey() {
        return apiKey;
    }
}
