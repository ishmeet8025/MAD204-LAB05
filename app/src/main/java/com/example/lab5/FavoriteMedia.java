/**
 * Course: MAD204 - Lab 5
 * Student: YOUR NAME - YOUR ID
 * Date: 2025-02-10
 * Description: Room Entity class representing a favorite media item.
 */


package com.example.lab5;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="favorites")
public class FavoriteMedia {
    @PrimaryKey(autoGenerate=true)
    public int id;
    public String uri;
    public String type;

    public FavoriteMedia(String uri,String type){
        this.uri=uri;
        this.type=type;
    }
}
