package com.example.h071211003_finalproject.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.h071211003_finalproject.Database.DatabaseContract;
import com.example.h071211003_finalproject.Database.FavoriteHelper;
import com.example.h071211003_finalproject.Models.Favorites;
import com.example.h071211003_finalproject.Models.Movies;
import com.example.h071211003_finalproject.Models.TvShows;
import com.example.h071211003_finalproject.R;

public class MainActivity2 extends AppCompatActivity {
    public static final String EXTRA_FAV = "extra_fav";
    public static final int RESULT_ADD = 101;
    private FavoriteHelper favoriteHelper;
    private Favorites favorites;
    private Movies moviess;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView tv_title = findViewById(R.id.tv_title);
        TextView tv_rate = findViewById(R.id.tv_rating);
        TextView tv_synopsis = findViewById(R.id.tv_description);
        TextView tv_releaseDate = findViewById(R.id.tv_Releasedate);
        ImageView iv_poster = findViewById(R.id.iv_poster);
        ImageView iv_backposter = findViewById(R.id.backdrop);
        ImageView iv_icon = findViewById(R.id.iv_icon);
        CardView cv_back = findViewById(R.id.cv_back);
        CardView cv_fav = findViewById(R.id.cv_fav);

            favoriteHelper = FavoriteHelper.getInstance(getApplicationContext());
            favoriteHelper.open();

            favorites = getIntent().getParcelableExtra(EXTRA_FAV);

        Intent intent = getIntent();
        if (intent.getParcelableExtra("moviess") !=null){
            Movies moviess = intent.getParcelableExtra("moviess");

            int icon = R.drawable.baseline_movie_24;
            tv_title.setText(moviess.getTitle());
            tv_releaseDate.setText(moviess.getReleaseDate());
            tv_rate.setText(moviess.getVote_average());
            tv_synopsis.setText(moviess.getOverview());
            iv_icon.setImageResource(icon);
            Glide.with(this).load("https://image.tmdb.org/t/p/w500" + moviess.getImagePoster()).into(iv_poster);
            Glide.with(this).load("https://image.tmdb.org/t/p/w500" + moviess.getBackdrop_path()).into(iv_backposter);


            cv_fav.setOnClickListener(view -> {
                Intent fav = new Intent();
                fav.putExtra(EXTRA_FAV, favorites);
                ContentValues values = new ContentValues();
                values.put(DatabaseContract.FavoriteColumn.POSTER_IMAGE, moviess.getImagePoster());
                values.put(DatabaseContract.FavoriteColumn.TITLE, moviess.getTitle());
                values.put(DatabaseContract.FavoriteColumn.YEAR_RELEASE, moviess.getReleaseDate());
                values.put(DatabaseContract.FavoriteColumn._ID, moviess.getId());
                values.put(DatabaseContract.FavoriteColumn.RATING, moviess.getVote_average());
                values.put(DatabaseContract.FavoriteColumn.SYNOPSIS, moviess.getOverview());
                values.put(DatabaseContract.FavoriteColumn.BACK_DROP, moviess.getBackdrop_path());

                Toast.makeText(MainActivity2.this, "Berhasil menambahkan ke Favorite", Toast.LENGTH_SHORT).show();

                long result = favoriteHelper.insert(values);
//                long result2 = favoriteHelper.deleteById(String.valueOf(favorites.getId()));
                if (result > 0){
                    Toast.makeText(MainActivity2.this, "Berhasil menambahkan ke Favorite", Toast.LENGTH_SHORT).show();
                }else {
                    favoriteHelper.deleteByTitle(moviess.getTitle());
                    Toast.makeText(MainActivity2.this, "Berhasil menghapus ke Favorite", Toast.LENGTH_SHORT).show();
                }
            });
        } else if (intent.getParcelableExtra("tvshowss") !=null) {
            TvShows tvShows = intent.getParcelableExtra("tvshowss");
            int icontv = R.drawable.baseline_tv_24;

            tv_title.setText(tvShows.getName());
            tv_releaseDate.setText(tvShows.getAir_date());
            tv_rate.setText(tvShows.getVote_average());
            tv_synopsis.setText(tvShows.getOverview());
            iv_icon.setImageResource(icontv);
            Glide.with(this).load("https://image.tmdb.org/t/p/w500" + tvShows.getPoster_path()).into(iv_poster);
            Glide.with(this).load("https://image.tmdb.org/t/p/w500" + tvShows.getBackdrop_path()).into(iv_backposter);

            cv_fav.setOnClickListener(view -> {
                Intent fav = new Intent();
                fav.putExtra(EXTRA_FAV, favorites);
                ContentValues values = new ContentValues();
                values.put(DatabaseContract.FavoriteColumn.POSTER_IMAGE, tvShows.getPoster_path());
                values.put(DatabaseContract.FavoriteColumn.TITLE, tvShows.getName());
                values.put(DatabaseContract.FavoriteColumn.YEAR_RELEASE, tvShows.getAir_date());
                values.put(DatabaseContract.FavoriteColumn._ID, tvShows.getId());
                values.put(DatabaseContract.FavoriteColumn.RATING, tvShows.getVote_average());
                values.put(DatabaseContract.FavoriteColumn.SYNOPSIS, tvShows.getOverview());
                values.put(DatabaseContract.FavoriteColumn.BACK_DROP, tvShows.getBackdrop_path());
                Toast.makeText(MainActivity2.this, "Berhasil menambahkan ke Favorite", Toast.LENGTH_SHORT).show();

                long result = favoriteHelper.insert(values);
                if (result > 0){
                    Toast.makeText(MainActivity2.this, "Berhasil menambahkan ke Favorite", Toast.LENGTH_SHORT).show();
                } else {
                    favoriteHelper.deleteByTitle(tvShows.getName());
                    Toast.makeText(MainActivity2.this, "Berhasil menghapus dari Favorite", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Favorites favorites1 = intent.getParcelableExtra("favs");
            tv_title.setText(favorites1.getTitle());
            tv_releaseDate.setText(favorites1.getReleaseDate());
            tv_rate.setText(favorites1.getVote_average());
            tv_synopsis.setText(favorites1.getOverview());
            Glide.with(this).load("https://image.tmdb.org/t/p/w500" + favorites1.getImagePoster()).into(iv_poster);
            Glide.with(this).load("https://image.tmdb.org/t/p/w500" + favorites1.getBackdrop_path()).into(iv_backposter);

            cv_fav.setOnClickListener(view -> {
                Intent fav = new Intent();
                fav.putExtra(EXTRA_FAV, favorites);
                ContentValues values = new ContentValues();
                values.put(DatabaseContract.FavoriteColumn.POSTER_IMAGE, favorites1.getImagePoster());
                values.put(DatabaseContract.FavoriteColumn.TITLE, favorites1.getTitle());
                values.put(DatabaseContract.FavoriteColumn.YEAR_RELEASE, favorites1.getReleaseDate());
                values.put(DatabaseContract.FavoriteColumn._ID, favorites1.getId());
                values.put(DatabaseContract.FavoriteColumn.RATING, favorites1.getVote_average());
                values.put(DatabaseContract.FavoriteColumn.SYNOPSIS, favorites1.getOverview());
                values.put(DatabaseContract.FavoriteColumn.BACK_DROP, favorites1.getBackdrop_path());

                long result = favoriteHelper.insert(values);
                if (result > 0){
                    Toast.makeText(MainActivity2.this, "Berhasil menambahkan ke Favorite", Toast.LENGTH_SHORT).show();
                } else {
                    favoriteHelper.deleteByTitle(favorites1.getTitle());
                    Toast.makeText(MainActivity2.this, "Berhasil menghapus dari Favorite", Toast.LENGTH_SHORT).show();
                }
            });
        }

        cv_back.setOnClickListener(view -> {
            onBackPressed();
        });

        }
}