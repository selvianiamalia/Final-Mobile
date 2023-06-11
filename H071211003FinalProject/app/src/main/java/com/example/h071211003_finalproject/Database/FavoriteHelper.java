package com.example.h071211003_finalproject.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.h071211003_finalproject.Models.Favorites;

import java.util.ArrayList;

public class FavoriteHelper {
    private static final String DATABASE_TABLE = DatabaseContract.TABLE_NAME;
    private static DatabaseHelper databaseHelper;
    private static SQLiteDatabase database;
    private static volatile FavoriteHelper INSTANCE;

    private FavoriteHelper(Context context){
        databaseHelper = new DatabaseHelper(context);
    }

    public static FavoriteHelper getInstance(Context context){
        if (INSTANCE == null){
            synchronized (SQLiteOpenHelper.class){
                if (INSTANCE == null){
                    INSTANCE = new FavoriteHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void close(){
        databaseHelper.close();
        if (database.isOpen()){
            database.close();
        }
    }

    public ArrayList<Favorites> getAllFavorites(){
        ArrayList<Favorites> favorites = new ArrayList<>();
        Cursor cursor = database.query(
                DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                DatabaseContract.FavoriteColumn._ID + " DESC "
        );
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Favorites favorite = getFavoritesFromCursor(cursor);
            favorites.add(favorite);
            cursor.moveToNext();
        }
        cursor.close();
        return favorites;
    }

    private Favorites getFavoritesFromCursor(Cursor cursor) {
        Favorites favorites = new Favorites();
        favorites.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn._ID)));
        favorites.setImagePoster(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn.POSTER_IMAGE)));
        favorites.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn.TITLE)));
        favorites.setReleaseDate(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn.YEAR_RELEASE)));
        return favorites;
    }

    public long insert(ContentValues values) {
        return database.insert(DATABASE_TABLE, null, values);
    }

    public int deleteByTitle(String title) {
        return database.delete(DATABASE_TABLE, DatabaseContract.FavoriteColumn.TITLE + " = "
                + '"' + title + '"', null);
    }
}
