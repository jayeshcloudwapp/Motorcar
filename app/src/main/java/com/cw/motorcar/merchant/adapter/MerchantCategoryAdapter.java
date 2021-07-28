package com.cw.motorcar.merchant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cw.motorcar.R;
import com.cw.motorcar.shop.adapter.ShopCategoryAdapter;

import org.jetbrains.annotations.NotNull;

public class MerchantCategoryAdapter extends RecyclerView.Adapter<MerchantCategoryAdapter.ViewHolder> {
    private Context context;
    private String[] cat = {"Car Wash", "A/C Checkup", "Full Car Checkup", "Car Maintenance Service", "Window Tinting", "Oil Service"};
    private OnCarCategorySelectListenre onCategorySelectListenre;

    public interface OnCarCategorySelectListenre {
        void onCatSelect(String cat);
    }

    public MerchantCategoryAdapter(Context context, OnCarCategorySelectListenre onCategorySelectListenre) {
        this.context = context;
        this.onCategorySelectListenre = onCategorySelectListenre;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shop_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.tvCatName.setText(cat[position]);
        holder.tvCatName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onCategorySelectListenre!=null){
                    onCategorySelectListenre.onCatSelect(cat[position]);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cat.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvCatName;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvCatName = itemView.findViewById(R.id.tv_cat_name);

        }
    }
}
