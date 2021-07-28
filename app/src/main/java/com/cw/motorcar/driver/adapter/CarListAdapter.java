package com.cw.motorcar.driver.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cw.motorcar.R;
import com.cw.motorcar.data.network.ApiClient;
import com.cw.motorcar.driver.model.Carlist;
import com.cw.motorcar.driver.ui.activity.car.CategoryActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.ViewHolder> implements Filterable {

    private Context context;
    private String catId;
    private ArrayList<Carlist> userCarArrayList;
    private ArrayList<Carlist> userCarFilterableArrayList;
    private String catName;

    public CarListAdapter(Context context, String catId, ArrayList<Carlist> userCarArrayList, String catName) {
        this.context = context;
        this.catId = catId;
        this.userCarArrayList = userCarArrayList;
        this.userCarFilterableArrayList = userCarArrayList;
        this.catName = catName;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_car, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Carlist carlist = userCarFilterableArrayList.get(position);
        holder.tvcarNo.setText(carlist.getRegNo());
        holder.tvBrand.setText(carlist.getBrand());
        holder.tvModel.setText(carlist.getModel());
        Glide.with(context).load(ApiClient.SITE_URL + carlist.getImage()).into(holder.ivImg);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, CategoryActivity.class)
                        .putExtra("cat_id", catId)
                        .putExtra("mycar", carlist)
                        .putExtra("cat_name", catName)
                );
            }
        });
    }

    @Override
    public int getItemCount() {
        return userCarFilterableArrayList == null ? 0 : userCarFilterableArrayList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    userCarFilterableArrayList = userCarArrayList;
                } else {
                    ArrayList<Carlist> filteredList = new ArrayList<>();
                    for (Carlist row : userCarArrayList) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getRegNo().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    userCarFilterableArrayList = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = userCarFilterableArrayList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                userCarFilterableArrayList = (ArrayList<Carlist>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvcarNo, tvBrand, tvModel;
        protected ImageView ivImg;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvcarNo = itemView.findViewById(R.id.tv_car_no);
            tvBrand = itemView.findViewById(R.id.tv_brand);
            tvModel = itemView.findViewById(R.id.tv_model);
            ivImg = itemView.findViewById(R.id.iv_img);

        }
    }
}
