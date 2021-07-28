package com.cw.motorcar.driver.adapter;

import android.content.Context;
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

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MyCarListAdapter extends RecyclerView.Adapter<MyCarListAdapter.MyCarViewHolder> implements Filterable {

    private Context context;
    ArrayList<Carlist> data;
    ArrayList<Carlist> dataFilter;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Carlist item, View v);
    }

    public MyCarListAdapter(Context context, ArrayList<Carlist> data, OnItemClickListener listener) {
        this.context = context;
        this.data = data;
        this.dataFilter = data;
        this.listener = listener;
    }

    @Override
    public MyCarViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_my_car, parent, false);
        return new MyCarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyCarViewHolder holder, int position) {
        Carlist carlist = dataFilter.get(position);
        holder.tvCarNo.setText(carlist.getVinNo());
        holder.tvBrand.setText(carlist.getBrand());
        holder.tvModel.setText(carlist.getModel());
        Glide.with(context).load(ApiClient.SITE_URL + carlist.getImage()).into(holder.iv_img);
        holder.bind(carlist, listener);
    }

    @Override
    public int getItemCount() {
        return dataFilter == null ? 0 : dataFilter.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    dataFilter = data;
                } else {
                    ArrayList<Carlist> filterlist = new ArrayList<>();
                    for (Carlist row : data) {
                        if (row.getVinNo().toLowerCase().contains(charString)) {
                            filterlist.add(row);
                        }
                    }
                    dataFilter = filterlist;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = dataFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                dataFilter = (ArrayList<Carlist>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MyCarViewHolder extends RecyclerView.ViewHolder {
        protected ImageView ivEdit, ivDelete, iv_img;
        protected TextView tvCarNo, tvBrand, tvModel;

        public MyCarViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            ivEdit = itemView.findViewById(R.id.iv_edit);
            ivDelete = itemView.findViewById(R.id.iv_delete);
            iv_img = itemView.findViewById(R.id.iv_img);
            tvCarNo = itemView.findViewById(R.id.tv_car_no);
            tvBrand = itemView.findViewById(R.id.tv_brand);
            tvModel = itemView.findViewById(R.id.tv_model);
        }

        public void bind(final Carlist item, final OnItemClickListener listener) {

            ivEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item, v);
                }
            });
            ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item, v);
                }
            });
        }
    }
}
