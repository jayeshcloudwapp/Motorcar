package com.cw.motorcar.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cw.motorcar.R;
import com.cw.motorcar.shop.ui.order.OrderDetailsActivity;

import org.jetbrains.annotations.NotNull;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.OrderViewHolder> {
    private Context context;

    public MyOrderAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_my_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull OrderViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, OrderDetailsActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        public OrderViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }
    }
}
