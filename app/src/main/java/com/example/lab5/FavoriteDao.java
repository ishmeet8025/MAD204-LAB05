/**
 * Course: MAD204 - Lab 5
 * Student: YOUR NAME - YOUR ID
 * Date: 2025-02-10
 * Description: DAO interface providing database operations for favorites.
 */


package com.example.lab5;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoriteDao {
    @Insert
    void insert(FavoriteMedia media);

    @Query("SELECT * FROM favorites")
    List<FavoriteMedia> getAllFavorites();

    @Delete
    void delete(FavoriteMedia media);
}
