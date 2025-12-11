// Documentation: Room database singleton that stores favorite media items using the DAO.
// Singleton explanation: Ensures only one instance of the Room database is created.
package com.example.lab5;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Room database for favorites.
 */
@Database(entities={FavoriteMedia.class}, version=1)
public abstract class FavoritesDatabase extends RoomDatabase {

    private static FavoritesDatabase instance;

    public abstract FavoriteDao favoriteDao();

    public static synchronized FavoritesDatabase getInstance(Context ctx){
        if(instance == null){
            instance = Room.databaseBuilder(
                    ctx.getApplicationContext(),
                    FavoritesDatabase.class,
                    "favorites_db"
            ).allowMainThreadQueries().build();
        }
        return instance;
    }
}
