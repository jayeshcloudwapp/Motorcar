package com.cw.motorcar.driver.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cw.motorcar.R;
import com.cw.motorcar.driver.model.Carlist;
import com.cw.motorcar.driver.model.Category;
import com.cw.motorcar.driver.ui.activity.car.NewRequestActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private Context context;
    private int[] images = {R.drawable.car_wash, R.drawable.air_conditioner, R.drawable.car_full_checkup, R.drawable.maintenance, R.drawable.tinting, R.drawable.oil_service};
    private ArrayList<Category> data;
    Carlist carlist;
    String catName;

    public CategoryAdapter(Context context, ArrayList<Category> data, Carlist carlist, String catName) {
        this.context = context;
        this.data = data;
        this.carlist = carlist;
        this.catName = catName;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Category category = data.get(position);
        holder.tvCategoryName.setText(category.getName());
        holder.ivImg.setImageResource(images[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, NewRequestActivity.class)
                        .putExtra("cat_id", category.getCat_id())
                        .putExtra("cat_name", catName)
                        .putExtra("sub_cat_id", carlist.getId())
                        .putExtra("sub_cat_name", category.getName())
                        .putExtra("mycar", carlist));
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvCategoryName;
        protected ImageView ivImg;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvCategoryName = itemView.findViewById(R.id.tv_category_name);
            ivImg = itemView.findViewById(R.id.iv_img);
        }
    }
}
