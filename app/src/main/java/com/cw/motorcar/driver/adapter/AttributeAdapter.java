package com.cw.motorcar.driver.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cw.motorcar.R;
import com.cw.motorcar.driver.model.Attributes;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AttributeAdapter extends RecyclerView.Adapter<AttributeAdapter.AttributeViewHolder> {

    private Context context;
    private ArrayList<Attributes> attributes;

    public AttributeAdapter(Context context, ArrayList<Attributes> attributes) {
        this.context = context;
        this.attributes = attributes;
    }

    @NonNull
    @NotNull
    @Override
    public AttributeViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_attribute, parent, false);
        return new AttributeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AttributeViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return attributes == null ? 0 : attributes.size();
    }

    public class AttributeViewHolder extends RecyclerView.ViewHolder {
        public AttributeViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }
    }
}
