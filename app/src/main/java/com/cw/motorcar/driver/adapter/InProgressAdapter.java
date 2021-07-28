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
import com.cw.motorcar.driver.model.MyRequest;
import com.cw.motorcar.driver.ui.fragment.chat.ChatMessageActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class InProgressAdapter extends RecyclerView.Adapter<InProgressAdapter.InProgressViewHolder> {
    private Context context;
    private ArrayList<MyRequest> myRequestResponce;


    public InProgressAdapter(Context context, ArrayList<MyRequest> myRequestResponce) {
        this.context = context;
        this.myRequestResponce = myRequestResponce;
    }

    @NotNull
    @Override
    public InProgressViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_inprogress, parent, false);
        return new InProgressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull InProgressViewHolder holder, int position) {
        MyRequest myRequest = myRequestResponce.get(position);
        holder.tvCategoryName.setText(myRequest.getCategory());
        holder.tvPrice.setText("Price : 00AED");
        holder.tvUsername.setText("Username");
        holder.tvAttribute.setText("Attributes : " + (myRequest.getAttribute().isEmpty() ? "Na" : myRequest.getAttribute()));
        holder.tvRequestId.setText("Request ID : " + myRequest.getRequestId());
        holder.tvChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, ChatMessageActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return myRequestResponce == null ? 0 : myRequestResponce.size();
    }

    public class InProgressViewHolder extends RecyclerView.ViewHolder {
        private TextView tvChat, tvCategoryName, tvPrice, tvUsername, tvAttribute, tvRequestId;
        private ImageView ivImg;

        public InProgressViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvChat = itemView.findViewById(R.id.tv_chat);
            tvCategoryName = itemView.findViewById(R.id.tv_category_name);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvUsername = itemView.findViewById(R.id.tv_username);
            tvAttribute = itemView.findViewById(R.id.tv_attribute);
            tvRequestId = itemView.findViewById(R.id.tv_request_id);
        }
    }
}
