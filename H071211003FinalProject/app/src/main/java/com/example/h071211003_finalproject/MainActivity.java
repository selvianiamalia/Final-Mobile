package com.example.h071211003_finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.h071211003_finalproject.Fragment.FavoriteFragment;
import com.example.h071211003_finalproject.Fragment.MoviesFragment;
import com.example.h071211003_finalproject.Fragment.TvShowsFragment;

public class MainActivity extends AppCompatActivity {

    private ImageView btn_movies, btn_tvShows, btn_fav;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_movies = findViewById(R.id.moviesButton);
        btn_tvShows = findViewById(R.id.TvShowsButton);
        btn_fav = findViewById(R.id.FavButton);

        fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(MoviesFragment.class.getSimpleName());

        if (!(fragment instanceof MoviesFragment)){
            navigateFragment(new MoviesFragment());
        }

        btn_movies.setOnClickListener(view -> {
            navigateFragment(new MoviesFragment());
        });

        btn_tvShows.setOnClickListener(view -> {
            navigateFragment(new TvShowsFragment());
        });

        btn_fav.setOnClickListener(view -> {
            navigateFragment(new FavoriteFragment());
        });

    }

    private void navigateFragment(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.frame_main, fragment, fragment.getClass().getSimpleName())
                .commit();
    }
}