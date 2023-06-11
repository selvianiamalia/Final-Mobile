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
    public static final int RESULT_DELETE = 301;
    private FavoriteHelper favoriteHelper;
    private Favorites favorites;

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
                Toast.makeText(MainActivity2.this, "Berhasil menambahkan ke Favorite", Toast.LENGTH_SHORT).show();

                long result = favoriteHelper.insert(values);
                long result2 = favoriteHelper.deleteById(String.valueOf(favorites.getId()));
                if (result > 0){
                    setResult(RESULT_ADD, intent);
                    finish();
                    startActivity(intent);
                    System.out.println("ini movie");
                }
                else if (result2 > 0) {
                    Intent delete = new Intent(MainActivity2.this, MainActivity.class);
                    setResult(RESULT_DELETE, delete);
                    finish();
                    startActivity(delete);
                    System.out.println("ini hapus");
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
                Toast.makeText(MainActivity2.this, "Berhasil menambahkan ke Favorite", Toast.LENGTH_SHORT).show();

                long result = favoriteHelper.insert(values);
                if (result > 0){
                    setResult(RESULT_ADD, intent);
                    finish();
                    startActivity(intent);
                    System.out.println("tvshow ini");
                }
            });
        }

        cv_back.setOnClickListener(view -> {
            Intent back = new Intent(MainActivity2.this, MainActivity.class);
            startActivity(back);
            finish();
        });



        }
}