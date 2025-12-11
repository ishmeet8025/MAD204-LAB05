/**
 * Course: MAD204 - Lab 5
 * Student: YOUR NAME - YOUR ID
 * Date: 2025-02-10
 * Description: Room Database that stores favorite media.
 */


package com.example.lab5;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities={FavoriteMedia.class},version=1)
public abstract class FavoritesDatabase extends RoomDatabase {

    private static FavoritesDatabase instance;

    public abstract FavoriteDao favoriteDao();

    public static synchronized FavoritesDatabase getInstance(Context ctx){
        if(instance==null){
            instance = Room.databaseBuilder(ctx.getApplicationContext(),
                    FavoritesDatabase.class,
                    "favorites_db").allowMainThreadQueries().build();
        }
        return instance;
    }
}
