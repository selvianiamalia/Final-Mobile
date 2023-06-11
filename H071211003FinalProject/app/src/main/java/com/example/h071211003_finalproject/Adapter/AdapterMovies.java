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
import com.example.h071211003_finalproject.Models.Movies;
import com.example.h071211003_finalproject.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterMovies extends RecyclerView.Adapter<AdapterMovies.ViewHolder> {
    private final ArrayList<Movies> moviesList = new ArrayList<>();
    public void addMovies(List<Movies> datamovie){
        this.moviesList.addAll(datamovie);
    }

    @NonNull
    @Override
    public AdapterMovies.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_film, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMovies.ViewHolder holder, int position) {
        holder.onBind(moviesList.get(position));
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void onBind(Movies movies) {
            ImageView iv_poster = itemView.findViewById(R.id.iv_filmposter);
            TextView tv_title = itemView.findViewById(R.id.tv_title2);
            TextView tv_year = itemView.findViewById(R.id.tv_yearRelease);

            tv_title.setText(movies.getTitle());
            String tahun = movies.getReleaseDate();
            if(tahun != null && !tahun.isEmpty()){
                String tahunn = tahun.substring(0, 4);
                tv_year.setText(tahunn);
            }
            Glide.with(itemView.getContext()).load("https://image.tmdb.org/t/p/w500" + movies.getImagePoster()).into(iv_poster);
            System.out.println();

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(itemView.getContext(), MainActivity2.class);
                intent.putExtra("moviess", movies);
                itemView.getContext().startActivity(intent);
            });
        }
    }
}
