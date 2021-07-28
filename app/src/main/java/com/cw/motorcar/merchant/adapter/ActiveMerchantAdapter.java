package com.cw.motorcar.merchant.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cw.motorcar.R;
import com.cw.motorcar.merchant.ui.Activity.MerchantRequestDetailsActivity;
import com.google.android.material.textview.MaterialTextView;

import org.jetbrains.annotations.NotNull;

public class ActiveMerchantAdapter extends RecyclerView.Adapter<ActiveMerchantAdapter.MactiveViewHolder> {

    private Context context;

    public ActiveMerchantAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MactiveViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_active_merchant, parent, false);
        return new MactiveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MactiveViewHolder holder, int position) {

        if (position == 0) {
            holder.tvPause.setVisibility(View.VISIBLE);
            holder.btnSubQuotes.setClickable(false);
            holder.btnSubQuotes.setBackgroundTintList(context.getResources().getColorStateList(R.color.red));

        } else {
            holder.tvPause.setVisibility(View.GONE);
            holder.btnSubQuotes.setClickable(true);
            holder.btnSubQuotes.setBackgroundTintList(context.getResources().getColorStateList(R.color.green));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, MerchantRequestDetailsActivity.class));
            }
        });
        holder.btnSubQuotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, MerchantRequestDetailsActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class MactiveViewHolder extends RecyclerView.ViewHolder {
        protected MaterialTextView btnSubQuotes;
        protected TextView tvPause;

        public MactiveViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            btnSubQuotes = itemView.findViewById(R.id.btn_sub_quotes);
            tvPause = itemView.findViewById(R.id.tv_pause);
        }
    }
}
