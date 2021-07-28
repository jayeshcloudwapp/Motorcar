package com.cw.motorcar.driver.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.cw.motorcar.R;
import com.cw.motorcar.driver.model.Category;
import com.cw.motorcar.driver.ui.activity.car.CarListActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CategoryHomeAdapter extends RecyclerView.Adapter<CategoryHomeAdapter.HomeCatViewHolder> {
    private Context context;
    private ArrayList<Category> categories;

    public CategoryHomeAdapter(Context context, ArrayList<Category> data) {
        this.context = context;
        this.categories = data;
    }


    @Override
    public HomeCatViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_category, parent, false);
        return new HomeCatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HomeCatViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.tvCatName.setText(category.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, CarListActivity.class)
                        .putExtra("cat_id", category.getId())
                        .putExtra("cat_name", category.getName())
                );
            }
        });
    }
    

    @Override
    public int getItemCount() {
        return categories == null ? 0 : categories.size();
    }

    public class HomeCatViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvCatName;

        public HomeCatViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvCatName = itemView.findViewById(R.id.tv_cat_name);
        }
    }
}
