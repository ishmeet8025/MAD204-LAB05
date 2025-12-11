// DAO explanation: Provides database access methods used by the favorites feature.
package com.example.lab5;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * DAO for favorites.
 */
@Dao
public interface FavoriteDao {

    @Insert
    void insert(FavoriteMedia media);

    @Query("SELECT * FROM favorites")
    List<FavoriteMedia> getAllFavorites();

    @Delete
    void delete(FavoriteMedia media);
}
