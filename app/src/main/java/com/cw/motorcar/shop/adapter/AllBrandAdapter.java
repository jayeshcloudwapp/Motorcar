package com.cw.motorcar.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cw.motorcar.R;

import org.jetbrains.annotations.NotNull;

public class AllBrandAdapter extends RecyclerView.Adapter<AllBrandAdapter.AllBrandViewHolder> {

    private Context context;

    public AllBrandAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public AllBrandViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_all_brand, parent, false);
        return new AllBrandViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AllBrandViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class AllBrandViewHolder extends RecyclerView.ViewHolder {
        public AllBrandViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }
    }
}
