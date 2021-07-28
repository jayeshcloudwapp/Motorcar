package com.cw.motorcar.merchant.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cw.motorcar.R;
import com.cw.motorcar.merchant.ui.Activity.MerchantRequestDetailsActivity;

import org.jetbrains.annotations.NotNull;

public class MerCancelledAdapter extends RecyclerView.Adapter<MerCancelledAdapter.ViewHolder> {
    public Context context;

    public MerCancelledAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mer_cancelled, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, MerchantRequestDetailsActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }
    }
}
