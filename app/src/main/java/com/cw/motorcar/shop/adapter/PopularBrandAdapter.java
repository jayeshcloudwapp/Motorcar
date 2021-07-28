package com.cw.motorcar.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cw.motorcar.R;

import org.jetbrains.annotations.NotNull;

public class PopularBrandAdapter extends RecyclerView.Adapter<PopularBrandAdapter.BrandViewHolder> {

    private Context context;

    public PopularBrandAdapter(Context context) {
        this.context = context;
    }

    @Override
    public BrandViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_popular_brand, parent, false);
        return new BrandViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull BrandViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class BrandViewHolder extends RecyclerView.ViewHolder {
        public BrandViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }
    }
}
