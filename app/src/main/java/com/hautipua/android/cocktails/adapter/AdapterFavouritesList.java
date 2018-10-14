package com.hautipua.android.cocktails.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hautipua.android.cocktails.R;
import com.hautipua.android.cocktails.model.Favourite;

import java.util.ArrayList;
import java.util.List;

public class AdapterFavouritesList extends RecyclerView.Adapter<AdapterFavouritesList.FavouriteListViewHolder> {

    private List<Favourite> favouritesList = new ArrayList<>();
    private Context _context;

    public AdapterFavouritesList(List<Favourite> list, Context context) {

        this.favouritesList = list;
        _context = context;
    }

    public AdapterFavouritesList.FavouriteListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_favourites_list, parent, false);

        AdapterFavouritesList.FavouriteListViewHolder myViewHolder = new AdapterFavouritesList.FavouriteListViewHolder(itemList);

        return myViewHolder;
    }

    public void onBindViewHolder(@NonNull AdapterFavouritesList.FavouriteListViewHolder myViewHolder, int position) {

        Favourite favourite = favouritesList.get(position);

        myViewHolder.tvNameFavouritesList.setText(favourite.getName());
    }

    public int getItemCount()
    {
        return favouritesList.size();
    }

    public class FavouriteListViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvNameFavouritesList;

        public FavouriteListViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameFavouritesList = itemView.findViewById(R.id.tvNameFavouritesList);
        }
    }
}
