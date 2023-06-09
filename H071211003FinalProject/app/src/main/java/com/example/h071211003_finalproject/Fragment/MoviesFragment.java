package com.example.h071211003_finalproject.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.h071211003_finalproject.APIConfig;
import com.example.h071211003_finalproject.Adapter.AdapterMovies;
import com.example.h071211003_finalproject.MainActivity;
import com.example.h071211003_finalproject.MoviesResponse;
import com.example.h071211003_finalproject.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesFragment extends Fragment {
    private RecyclerView rv_movies;
    private AdapterMovies adapter_movies;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_movies = view.findViewById(R.id.rv_movies);
        rv_movies.setHasFixedSize(true);
        adapter_movies = new AdapterMovies();
        APIConfig.getApiService().getMovies(APIConfig.getApiKey()).enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    adapter_movies.addMovies(response.body().getMovies());
                    rv_movies.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                    rv_movies.setAdapter(adapter_movies);
                    Log.d("movies", response.body().toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MoviesResponse> call, @NonNull Throwable t) {
                System.out.println("test");
//                Toast.makeText(MoviesFragment.this, "OnFailure " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}