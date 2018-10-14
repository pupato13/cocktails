package com.hautipua.android.cocktails.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hautipua.android.cocktails.R;
import com.hautipua.android.cocktails.model.Cocktail;

import java.util.ArrayList;
import java.util.List;

public class AdapterCocktailList extends RecyclerView.Adapter<AdapterCocktailList.CocktailsListViewHolder> {

    private List<Cocktail> cocktailsList = new ArrayList<>();
    private Context _context;

    public AdapterCocktailList(List<Cocktail> list, Context context) {

        this.cocktailsList = list;
        _context = context;
    }

    @NonNull
    @Override
    public CocktailsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_cocktails_list, parent, false);

        CocktailsListViewHolder myViewHolder = new CocktailsListViewHolder(itemList);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CocktailsListViewHolder myViewHolder, int position) {

        Cocktail cocktail = cocktailsList.get(position);

        myViewHolder.tvNameCocktailList.setText(cocktail.getName());
        myViewHolder.tvSpiritCocktailList.setText(cocktail.getSpiritName());

        int _photoId = _context.getResources().getIdentifier(cocktail.getPhotoId().replace(".png", ""), "drawable", _context.getPackageName());
        myViewHolder.ivPhotoCocktailList.setImageResource(_photoId);
    }

    @Override
    public int getItemCount()
    {
        return cocktailsList.size();
    }

    public class CocktailsListViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ivPhotoCocktailList;
        TextView tvNameCocktailList;
        TextView tvSpiritCocktailList;
        //int _idPhoto;

        public CocktailsListViewHolder(@NonNull View itemView) {
            super(itemView);

            ivPhotoCocktailList = itemView.findViewById(R.id.ivPhotoCocktailList);
            tvNameCocktailList = itemView.findViewById(R.id.tvNameCocktailList);
            tvSpiritCocktailList = itemView.findViewById(R.id.tvSpiritCocktailList);
        }
    }
}
