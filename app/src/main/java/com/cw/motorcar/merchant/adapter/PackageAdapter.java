package com.cw.motorcar.merchant.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cw.motorcar.R;
import com.cw.motorcar.merchant.ui.coupon.AddCouponActivity;
import com.cw.motorcar.merchant.ui.packages.PackageDetailsActivity;
import com.google.android.material.button.MaterialButton;

import org.jetbrains.annotations.NotNull;

public class PackageAdapter extends RecyclerView.Adapter<PackageAdapter.PackageViewHolder> {
    private Context context;

    public PackageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public PackageViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_package, parent, false);
        return new PackageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PackageViewHolder holder, int position) {

        holder.btnFeature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, PackageDetailsActivity.class));
            }
        });
        holder.btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, AddCouponActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 4;
    }


    public class PackageViewHolder extends RecyclerView.ViewHolder {
        protected MaterialButton btnBuy,btnFeature;
        public PackageViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            btnBuy=itemView.findViewById(R.id.btn_buy);
            btnFeature=itemView.findViewById(R.id.btn_feature);

        }
    }
}
