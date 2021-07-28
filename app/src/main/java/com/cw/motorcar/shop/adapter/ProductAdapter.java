package com.cw.motorcar.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cw.motorcar.R;

import org.jetbrains.annotations.NotNull;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private Context context;

    public ProductAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ProductViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        public ProductViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }
    }
}
