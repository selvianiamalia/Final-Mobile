package com.example.h071211003_finalproject.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.h071211003_finalproject.APIConfig;
import com.example.h071211003_finalproject.Adapter.AdapterTVShows;
import com.example.h071211003_finalproject.R;
import com.example.h071211003_finalproject.TvShowsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowsFragment extends Fragment {

    private RecyclerView rv_tv;
    AdapterTVShows adapterTVShows;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_shows, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_tv = view.findViewById(R.id.rv_tvShows);
        rv_tv.setHasFixedSize(true);
        adapterTVShows = new AdapterTVShows();

        APIConfig.getApiService().getTvShows(APIConfig.getApiKey()).enqueue(new Callback<TvShowsResponse>() {
            @Override
            public void onResponse(@NonNull Call<TvShowsResponse> call, @NonNull Response<TvShowsResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    adapterTVShows.addTvShows(response.body().getTvShows());
                    rv_tv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                    rv_tv.setAdapter(adapterTVShows);
                    Log.d("movies", response.body().toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<TvShowsResponse> call, @NonNull Throwable t) {

            }
        });

    }

}