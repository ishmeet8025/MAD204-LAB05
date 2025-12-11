// Entity purpose: This class stores media (image/video) information in Room database.
package com.example.lab5;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Course: MAD204 - Lab 5
 * Entity for favorite media item.
 */
@Entity(tableName="favorites")
public class FavoriteMedia {

    @PrimaryKey(autoGenerate=true)
    public int id;
    public String uri;
    public String type;

    public FavoriteMedia(String uri, String type){
        this.uri = uri;
        this.type = type;
    }
}
