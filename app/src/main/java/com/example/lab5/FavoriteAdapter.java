/**
 * Course: MAD204 - Lab 5
 * Student: YOUR NAME - YOUR ID
 * Date: 2025-02-10
 * Description: RecyclerView Adapter used for displaying favorite media items.
 */


package com.example.lab5;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyViewHolder> {

    List<FavoriteMedia> list;
    Context context;

    public FavoriteAdapter(Context c,List<FavoriteMedia> l){
        context=c; list=l;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType){
        View v= LayoutInflater.from(context)
                .inflate(R.layout.favorite_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder h,int i){
        FavoriteMedia m=list.get(i);
        h.typeText.setText(m.type);
        h.thumb.setImageURI(Uri.parse(m.uri));

        h.itemView.setOnLongClickListener(v->{
            deleteItem(i);
            return true;
        });
    }

    @Override
    public int getItemCount(){ return list.size(); }

    public void deleteItem(int pos){
        FavoriteMedia removed=list.get(pos);
        FavoritesDatabase.getInstance(context).favoriteDao().delete(removed);
        list.remove(pos);
        notifyItemRemoved(pos);

        Snackbar.make(((Activity)context).findViewById(android.R.id.content),
                "Deleted",Snackbar.LENGTH_LONG)
                .setAction("UNDO",v->{
                    FavoritesDatabase.getInstance(context).favoriteDao().insert(removed);
                    list.add(pos,removed);
                    notifyItemInserted(pos);
                }).show();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView thumb;
        TextView typeText;

        public MyViewHolder(View v){
            super(v);
            thumb=v.findViewById(R.id.thumb);
            typeText=v.findViewById(R.id.typeText);
        }
    }
}
