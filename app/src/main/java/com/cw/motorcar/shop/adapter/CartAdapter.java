package com.cw.motorcar.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cw.motorcar.R;

import org.jetbrains.annotations.NotNull;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private Context context;
    public CartAdapter(Context context) {
        this.context=context;
    }

    @NonNull
    @NotNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_my_cart,parent,false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CartViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        public CartViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }
    }
}
