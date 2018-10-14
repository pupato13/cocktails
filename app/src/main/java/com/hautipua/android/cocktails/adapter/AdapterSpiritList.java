package com.hautipua.android.cocktails.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hautipua.android.cocktails.R;
import com.hautipua.android.cocktails.model.Cocktail;
import com.hautipua.android.cocktails.model.Spirit;

import java.util.ArrayList;
import java.util.List;

public class AdapterSpiritList extends RecyclerView.Adapter<AdapterSpiritList.SpiritListViewHolder> {

    private List<Spirit> spiritList = new ArrayList<>();
    private Context _context;

    public AdapterSpiritList(List<Spirit> list, Context context) {

        this.spiritList = list;
        _context = context;
    }

    public AdapterSpiritList.SpiritListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_spirit_list, parent, false);

        AdapterSpiritList.SpiritListViewHolder myViewHolder = new AdapterSpiritList.SpiritListViewHolder(itemList);

        return myViewHolder;
    }

    public void onBindViewHolder(@NonNull AdapterSpiritList.SpiritListViewHolder myViewHolder, int position) {

        Spirit spirit = spiritList.get(position);

        myViewHolder.tvNameSpiritList.setText(spirit.getName());

        String quantitySpirits = spirit.getQuantity() + " cocktails";
        myViewHolder.tvQuantitySpiritList.setText(quantitySpirits);
    }

    @Override
    public int getItemCount()
    {
        return spiritList.size();
    }

    public class SpiritListViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvNameSpiritList;
        TextView tvQuantitySpiritList;

        public SpiritListViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameSpiritList = itemView.findViewById(R.id.tvNameSpiritList);
            tvQuantitySpiritList = itemView.findViewById(R.id.tvQuantitySpiritList);
        }
    }
}
