package com.cw.motorcar.driver.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cw.motorcar.R;
import com.cw.motorcar.driver.model.MyRequest;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CompleteAdapter extends RecyclerView.Adapter<CompleteAdapter.CompleteViewHolder> {
    private Context context;
    private ArrayList<MyRequest> myRequests;

    public CompleteAdapter(Context context, ArrayList<MyRequest> myRequests) {
        this.context = context;
        this.myRequests = myRequests;
    }

    @NotNull
    @Override
    public CompleteViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_complete, parent, false);
        return new CompleteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CompleteViewHolder holder, int position) {
        MyRequest myRequest = myRequests.get(position);
        holder.tvCategoryName.setText(myRequest.getCategory());
        holder.tvPrice.setText("Price : 00AED");
        holder.tvUsername.setText("Username");
        holder.tvAttribute.setText("Attributes : " + (myRequest.getAttribute().isEmpty() ? "Na" : myRequest.getAttribute()));
        holder.tvRequestId.setText("Request ID : " + myRequest.getRequestId());
        holder.tvDateTime.setText(myRequest.getDateTime());
        holder.btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPopupRating();
            }
        });
    }

    private void openPopupRating() {
        //final Dialog mBottomSheetDialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.popup_rating, null);
        final BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(context, R.style.MaterialDialogSheet);
        mBottomSheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.alpha(Color.WHITE)));
        mBottomSheetDialog.setContentView(view); // your custom view.
        MaterialButton btn_agree = mBottomSheetDialog.findViewById(R.id.btn_agree);

        btn_agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomSheetDialog.dismiss();
            }
        });
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.setCanceledOnTouchOutside(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
        mBottomSheetDialog.show();
    }

    @Override
    public int getItemCount() {
        return myRequests == null ? 0 : myRequests.size();
    }

    public class CompleteViewHolder extends RecyclerView.ViewHolder {
        protected MaterialTextView btnRate, tvCategoryName, tvPrice, tvUsername, tvAttribute, tvDateTime, tvRequestId;

        public CompleteViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            btnRate = itemView.findViewById(R.id.btn_rate);
            tvCategoryName = itemView.findViewById(R.id.tv_category_name);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvUsername = itemView.findViewById(R.id.tv_username);
            tvAttribute = itemView.findViewById(R.id.tv_attribute);
            tvDateTime = itemView.findViewById(R.id.tv_date_time);
            tvRequestId = itemView.findViewById(R.id.tv_request_id);
        }
    }
}
