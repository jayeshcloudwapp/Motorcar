package com.cw.motorcar.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cw.motorcar.R;
import com.cw.motorcar.shop.ui.product.ProductDetailsActivity;

import org.jetbrains.annotations.NotNull;

public class AllProductAdapter extends RecyclerView.Adapter<AllProductAdapter.AllProductViewHolder> {
    private Context context;

    public AllProductAdapter(Context context) {
        this.context = context;
    }

    @Override
    public AllProductViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_all_product, parent, false);
        return new AllProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AllProductViewHolder holder, int position) {


        holder.linAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.linAdd.setVisibility(View.GONE);
                holder.linQty.setVisibility(View.VISIBLE);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, ProductDetailsActivity.class));
            }
        });
        holder.tvMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qty = Integer.parseInt(holder.tvQty.getText().toString());
                if (qty == 1) {
                    holder.linQty.setVisibility(View.GONE);
                    holder.linAdd.setVisibility(View.VISIBLE);
                } else {
                    qty = qty - 1;
                    holder.tvQty.setText(qty + "");
                }
            }
        });

        holder.tvPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qty = Integer.parseInt(holder.tvQty.getText().toString());
                qty = qty + 1;
                holder.tvQty.setText(qty + "");
            }
        });
    }


    @Override
    public int getItemCount() {
        return 15;
    }

    public class AllProductViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvMinus, tvQty, tvPlus;
        protected LinearLayout linAdd, linQty;

        public AllProductViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvMinus = itemView.findViewById(R.id.tv_minus);
            tvQty = itemView.findViewById(R.id.tv_qty);
            tvPlus = itemView.findViewById(R.id.tv_plus);
            linAdd = itemView.findViewById(R.id.lin_add);
            linQty = itemView.findViewById(R.id.lin_qty);
        }
    }
}
