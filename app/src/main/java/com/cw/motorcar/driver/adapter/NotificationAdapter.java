package com.cw.motorcar.driver.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cw.motorcar.R;

import org.jetbrains.annotations.NotNull;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {
    private Context context;

    public NotificationAdapter(Context context) {
        this.context = context;
    }

    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notification, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NotificationViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {
        public NotificationViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }
    }
}
