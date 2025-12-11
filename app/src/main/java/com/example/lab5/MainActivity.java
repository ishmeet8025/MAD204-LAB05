// Documentation: This method determines whether the selected URI is an image or video and displays it accordingly.
// Export explanation: Converts Room favorites list into JSON for persistence or sharing.
package com.example.lab5;
// Documentation: Added detailed header comment describing the activity purpose and student info.
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.gson.Gson;

import java.util.List;

/**
 * Course: MAD204 - Lab 5
 * Student: YOUR NAME Ishmeet Singh- YOUR ID A00202436
 * Date: 2025-02-10
 * Description: Main activity that manages media picking, displaying, and favorites.
 */
public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<String> pickSingleMedia;
    private ImageView imageView;
    private VideoView videoView;
    private Uri selectedUri;
    private String selectedType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        videoView = findViewById(R.id.videoView);

        setupPickers();
        loadFavorites();
    }

    private void setupPickers() {
        pickSingleMedia = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                uri -> {
                    if (uri != null) {
                        selectedUri = uri;
                        displayMedia(uri);
                    }
                }
        );
    }

    public void pickMedia(View view) {
        pickSingleMedia.launch("*/*");
    }

    private void displayMedia(Uri uri) {
        String type = getContentResolver().getType(uri);

        if (type.startsWith("image")) {
            selectedType = "image";
            videoView.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageURI(uri);

        } else if (type.startsWith("video")) {
            selectedType = "video";
            imageView.setVisibility(View.GONE);
            videoView.setVisibility(View.VISIBLE);
            videoView.setVideoURI(uri);
            videoView.start();
        }
    }

    public void addToFavorites(View v){
        if(selectedUri == null) return;
        FavoriteMedia media = new FavoriteMedia(selectedUri.toString(), selectedType);
        FavoritesDatabase.getInstance(this).favoriteDao().insert(media);
        Toast.makeText(this,"Added to favorites",Toast.LENGTH_SHORT).show();
        loadFavorites();
    }

    public void loadFavorites(){
        List<FavoriteMedia> list = FavoritesDatabase.getInstance(this)
                .favoriteDao().getAllFavorites();

        RecyclerView rv = findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new FavoriteAdapter(this, list));
    }

    public void exportJSON(View v){
        List<FavoriteMedia> list = FavoritesDatabase.getInstance(this)
                .favoriteDao().getAllFavorites();

        Gson gson = new Gson();
        String json = gson.toJson(list);
        Log.d("EXPORT_JSON", json);
    }
}
