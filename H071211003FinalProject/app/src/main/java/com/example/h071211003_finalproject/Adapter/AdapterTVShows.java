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
import com.example.h071211003_finalproject.Models.TvShows;
import com.example.h071211003_finalproject.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterTVShows extends RecyclerView.Adapter<AdapterTVShows.ViewHolder> {
    private final ArrayList<TvShows> tvShows = new ArrayList<>();

    public void addTvShows (List<TvShows> tvShows){
        this.tvShows.addAll(tvShows);
    }
    @NonNull
    @Override
    public AdapterTVShows.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_film, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTVShows.ViewHolder holder, int position) {
        TvShows tvshow = tvShows.get(position);
        holder.tv_title.setText(tvshow.getName());
        holder.tv_year.setText(tvshow.getAir_date());
        Glide.with(holder.itemView.getContext()).load(tvshow.getPoster_path()).into(holder.iv_poster);
        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500" + tvshow.getPoster_path()).into(holder.iv_poster);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), MainActivity2.class);
            intent.putExtra("tvshowss", tvshow);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_poster;
        TextView tv_title, tv_year;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_poster = itemView.findViewById(R.id.iv_filmposter);
            tv_title = itemView.findViewById(R.id.tv_title2);
            tv_year = itemView.findViewById(R.id.tv_yearRelease);

        }
    }
}
