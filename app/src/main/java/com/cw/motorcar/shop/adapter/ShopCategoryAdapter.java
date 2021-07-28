package com.cw.motorcar.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cw.motorcar.R;

import org.jetbrains.annotations.NotNull;

public class ShopCategoryAdapter extends RecyclerView.Adapter<ShopCategoryAdapter.ShopViewHolder> {

    private Context context;

    private String[] shopcat = {"Categories", "Lubrication & Hydraulics", "Brake System", "Filters", "Lighting",
            "Clutch System", "Air Conditioning", "Suspension", "Cooling System", "Body Parts",
            "Car Accessories", "Drivatrain", "Electrical", "Engine Parts", "Exhaut System",
            "Fasteners", "Fuel System", "Gasket & Seals"};

    private OnCategorySelectListenre onCategorySelectListenre;

    public interface OnCategorySelectListenre {
        void onCatSelect(String cat);
    }

    public ShopCategoryAdapter(Context context, OnCategorySelectListenre onCategorySelectListenre) {
        this.context = context;
        this.onCategorySelectListenre = onCategorySelectListenre;
    }


    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shop_category, parent, false);
        return new ShopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ShopViewHolder holder, int position) {
        holder.tvCatName.setText(shopcat[position]);
        holder.tvCatName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onCategorySelectListenre!=null){
                    onCategorySelectListenre.onCatSelect(shopcat[position]);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return shopcat.length;
    }

    public class ShopViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvCatName;

        public ShopViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvCatName = itemView.findViewById(R.id.tv_cat_name);
        }
    }
}
