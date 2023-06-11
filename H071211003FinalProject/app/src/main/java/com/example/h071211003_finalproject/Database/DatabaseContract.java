package com.example.h071211003_finalproject.Database;

import android.provider.BaseColumns;

public class DatabaseContract {
    public static String TABLE_NAME = "favorites";

    public static final class FavoriteColumn implements BaseColumns{
        public static String TITLE = "title";
        public static String YEAR_RELEASE = "year_release";
        public static String POSTER_IMAGE = "poster_image";
        public static String SYNOPSIS = "synopsis";
        public static String RATING = "rating";
        public static String BACK_DROP = "back_drop";

    }
}
