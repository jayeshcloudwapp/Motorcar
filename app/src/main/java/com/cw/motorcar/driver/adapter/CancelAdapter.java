package com.cw.motorcar.driver.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cw.motorcar.R;
import com.cw.motorcar.driver.model.MyRequest;
import com.google.android.material.textview.MaterialTextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CancelAdapter extends RecyclerView.Adapter<CancelAdapter.CancelViewHolder> {

    private Context context;
    private ArrayList<MyRequest> myRequests;

    public CancelAdapter(Context context, ArrayList<MyRequest> myRequests) {
        this.context = context;
        this.myRequests = myRequests;
    }


    @Override
    public CancelViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_request_cancel, parent, false);
        return new CancelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CancelViewHolder holder, int position) {
        MyRequest myRequest = myRequests.get(position);
        holder.tvCategoryName.setText(myRequest.getCategory());
        holder.tvPrice.setText("Price : 00AED");
        holder.tvUsername.setText("Username");
        holder.tvAttribute.setText("Attributes : " + (myRequest.getAttribute().isEmpty() ? "Na" : myRequest.getAttribute()));
        holder.tvRequestId.setText("Request ID : " + myRequest.getRequestId());
        holder.tvDateTime.setText(myRequest.getDateTime());
    }

    @Override
    public int getItemCount() {
        return myRequests == null ? 0 : myRequests.size();
    }

    public class CancelViewHolder extends RecyclerView.ViewHolder {
        protected TextView btnRate, tvCategoryName, tvPrice, tvUsername, tvAttribute, tvDateTime, tvRequestId;

        public CancelViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvCategoryName = itemView.findViewById(R.id.tv_category_name);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvUsername = itemView.findViewById(R.id.tv_username);
            tvAttribute = itemView.findViewById(R.id.tv_attribute);
            tvDateTime = itemView.findViewById(R.id.tv_date_time);
            tvRequestId = itemView.findViewById(R.id.tv_request_id);
        }
    }
}
