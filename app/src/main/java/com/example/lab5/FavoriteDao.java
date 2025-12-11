// Documentation: This DAO defines the operations used to store and retrieve favorites from Room.
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
