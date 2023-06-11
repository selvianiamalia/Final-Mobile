package com.example.h071211003_finalproject.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "Note.db";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_TABLE_FAVORITE = String.format(
            "CREATE TABLE %s"
                    +"(%s INTEGER PRIMARY KEY AUTOINCREMENT, "
                    +"%s TEXT NOT NULL, "
                    +"%s TEXT NOT NULL, "
                    +"%s TEXT NOT NULL, "
                    +"%s TEXT NOT NULL, "
                    +"%s TEXT NOT NULL, "
                    +"%s TEXT NOT NULL)" ,
            DatabaseContract.TABLE_NAME,
            DatabaseContract.FavoriteColumn._ID,
            DatabaseContract.FavoriteColumn.TITLE,
            DatabaseContract.FavoriteColumn.YEAR_RELEASE,
            DatabaseContract.FavoriteColumn.POSTER_IMAGE,
            DatabaseContract.FavoriteColumn.BACK_DROP,
            DatabaseContract.FavoriteColumn.RATING,
            DatabaseContract.FavoriteColumn.SYNOPSIS
    );

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_FAVORITE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + DatabaseContract.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
