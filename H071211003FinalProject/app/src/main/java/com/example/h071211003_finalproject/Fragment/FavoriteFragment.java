package com.example.h071211003_finalproject.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.h071211003_finalproject.Adapter.AdapterFavorites;
import com.example.h071211003_finalproject.Database.FavoriteHelper;
import com.example.h071211003_finalproject.Models.Favorites;
import com.example.h071211003_finalproject.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FavoriteFragment extends Fragment {
    private RecyclerView rv_fav;
    AdapterFavorites adapter_fav;
    private ProgressBar progressBar1;
    ArrayList<Favorites> myFav = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private static class LoadStudentsAsync {
        private final WeakReference<Context> weakContext;
        private final WeakReference<LoadStudentsCallback> weakCallback;

        private LoadStudentsAsync(FavoriteFragment context, LoadStudentsCallback callback) {
            weakContext = new WeakReference<>(context.getContext());
            weakCallback = new WeakReference<>(callback);
        }
        void execute(){
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());
            executor.execute(() -> {
                Context context = weakContext.get();
                FavoriteHelper favoriteHelper = FavoriteHelper.getInstance(context);
                favoriteHelper.open();
                ArrayList<Favorites> favorites= favoriteHelper.getAllFavorites();
                favoriteHelper.close();
                handler.post(() -> weakCallback.get().postExecute(favorites));
            });
        }
    }

    interface LoadStudentsCallback{
        void postExecute(ArrayList<Favorites> favorites);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar1 = view.findViewById(R.id.progressBar1);
        rv_fav = view.findViewById(R.id.rv_favorite);
        rv_fav.setHasFixedSize(true);
        rv_fav.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter_fav = new AdapterFavorites();



        new LoadStudentsAsync(this, favs -> {
            if (favs.size() > 0){
                myFav = favs;
            }else {
                myFav = null;
            }
            if (myFav != null) {
                showCurrentUser(myFav);
                rv_fav.setAdapter(adapter_fav);
            }
            else {
                showCurrentUser(new ArrayList<>());
                Toast.makeText(getActivity(), "kosong?", Toast.LENGTH_SHORT).show();
            }
        }).execute();

    }

    private void showCurrentUser(ArrayList<Favorites> myFavs) {
        AdapterFavorites.setFavorites(myFavs);
    }
}