package com.example.h071211003_finalproject.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.h071211003_finalproject.Activity.MainActivity2;
import com.example.h071211003_finalproject.Models.Favorites;
import com.example.h071211003_finalproject.Models.TvShows;
import com.example.h071211003_finalproject.R;

import java.util.ArrayList;

public class AdapterFavorites extends RecyclerView.Adapter<AdapterFavorites.ViewHolder> {

    public static ArrayList<Favorites> myFavorites = new ArrayList<>();

    @NonNull
    @Override
    public AdapterFavorites.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFavorites.ViewHolder holder, int position) {

        Favorites favs = myFavorites.get(position);
        holder.tv_title.setText(favs.getTitle());
        String tahun = favs.getReleaseDate();
        if(tahun != null && !tahun.isEmpty()){
            String tahunn = tahun.substring(0, 4);
            holder.tv_year.setText(tahunn);
        }
        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500" + favs.getImagePoster()).into(holder.iv_poster);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), MainActivity2.class);
            intent.putExtra("favs", favs);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return myFavorites.size();
    }
    public static void setFavorites(ArrayList<Favorites> dataFavorites){
        myFavorites.clear();
        if (dataFavorites !=null){
            myFavorites.addAll(dataFavorites);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_poster;
        TextView tv_year, tv_title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title2);
            tv_year = itemView.findViewById(R.id.yearRelease);
            iv_poster = itemView.findViewById(R.id.iv_poster2);
        }
    }
}
