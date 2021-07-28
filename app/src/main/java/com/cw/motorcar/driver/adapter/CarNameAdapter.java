package com.cw.motorcar.driver.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.cw.motorcar.R;
import com.cw.motorcar.driver.model.Brand;
import com.cw.motorcar.driver.model.BrandModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CarNameAdapter extends RecyclerView.Adapter<CarNameAdapter.ViewHolder> implements Filterable {

    private Context context;
    String[] carNames;
    int Selected = -1;
    final int sdk;
    SelectItemListener selectItemListener;
    ArrayList<Brand> brands;
    ArrayList<Brand> brandsFilters;

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                 String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    brandsFilters = brands;
                } else {
                    ArrayList<Brand> filteredList = new ArrayList<>();
                    for (Brand row : brands) {
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    brandsFilters = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = brandsFilters;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                brandsFilters = (ArrayList<Brand>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface SelectItemListener {
        void onSelect(String name, int id);
    }

    public CarNameAdapter(Context context, ArrayList<Brand> brands, SelectItemListener selectItemListener) {
        this.context = context;
        this.carNames = carNames;
        sdk = android.os.Build.VERSION.SDK_INT;
        this.selectItemListener = selectItemListener;
        this.brands = brands;
        this.brandsFilters = brands;

    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_car_select, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Brand brand = brandsFilters.get(position);
        holder.tvName.setText(brand.getName());
        if (position == Selected) {
            if (selectItemListener != null) {
                selectItemListener.onSelect(brand.getName(), brand.getId());
            }
            if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                holder.tvName.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.border_rectangle_selected));
            } else {
                holder.tvName.setBackground(ContextCompat.getDrawable(context, R.drawable.border_rectangle_selected));
            }
        } else {
            if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                holder.tvName.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.border_rectangle));
            } else {
                holder.tvName.setBackground(ContextCompat.getDrawable(context, R.drawable.border_rectangle));
            }
        }

        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Selected = position;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return brandsFilters == null ? 0 : brandsFilters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvName;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
