package com.cw.motorcar.driver.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cw.motorcar.R;
import com.cw.motorcar.driver.model.MyRequest;
import com.cw.motorcar.driver.ui.activity.request.QuotationActivity;
import com.cw.motorcar.driver.ui.activity.request.RequestDetailsActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequestViewHolder> {
    private Context context;
    private ArrayList<MyRequest> myRequests;

    public RequestAdapter(Context context, ArrayList<MyRequest> myRequests) {
        this.context = context;
        this.myRequests = myRequests;
    }

    @NonNull
    @NotNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_request, parent, false);
        return new RequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RequestViewHolder holder, int position) {

        MyRequest myRequest = myRequests.get(position);
        holder.tvCategoryName.setText(myRequest.getCategory());
        holder.tvRegNo.setText("Reg no:" + myRequest.getCardata().getRegNo());
        holder.tvRequestNo.setText("Request ID :" + myRequest.getRequestId());
        holder.tvRequestDate.setText(myRequest.getDateTime());
        holder.mcv_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, RequestDetailsActivity.class)
                        .putExtra("myrequest", myRequest));
            }
        });

        holder.btn_no_quotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, QuotationActivity.class));
            }
        });

        holder.tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
            }
        });

        holder.tv_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupPause();
            }
        });

    }

    private void cancel() {
        final Dialog mBottomSheetDialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.popup_custom, null);
        mBottomSheetDialog.setContentView(view); // your custom view.

        MaterialButton no_btn = mBottomSheetDialog.findViewById(R.id.no_btn);
        MaterialButton yes_button = mBottomSheetDialog.findViewById(R.id.yes_button);
        TextView tvTitle = mBottomSheetDialog.findViewById(R.id.tv_title);
        TextView tvMsg = mBottomSheetDialog.findViewById(R.id.tv_msg);

        tvTitle.setText(R.string.cancel);
        tvMsg.setText(R.string.do_you_want_to_cancel);
        no_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomSheetDialog.dismiss();
            }
        });

        yes_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomSheetDialog.dismiss();
            }
        });
        mBottomSheetDialog.setCancelable(false);
        mBottomSheetDialog.setCanceledOnTouchOutside(false);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.show();
    }

    private void popupPause() {
        final Dialog mBottomSheetDialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.popup_custom, null);
        mBottomSheetDialog.setContentView(view); // your custom view.

        MaterialButton no_btn = mBottomSheetDialog.findViewById(R.id.no_btn);
        MaterialButton yes_button = mBottomSheetDialog.findViewById(R.id.yes_button);
        TextView tvTitle = mBottomSheetDialog.findViewById(R.id.tv_title);
        TextView tvMsg = mBottomSheetDialog.findViewById(R.id.tv_msg);

        tvTitle.setText(R.string.pause);
        tvMsg.setText(R.string.do_you_want_to_pause);
        no_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomSheetDialog.dismiss();
            }
        });

        yes_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomSheetDialog.dismiss();
            }
        });
        mBottomSheetDialog.setCancelable(false);
        mBottomSheetDialog.setCanceledOnTouchOutside(false);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.show();
    }

    @Override
    public int getItemCount() {
        return myRequests == null ? 0 : myRequests.size();
    }

    public class RequestViewHolder extends RecyclerView.ViewHolder {
        MaterialTextView btn_no_quotes, tv_cancel, tv_pause;
        MaterialCardView mcv_card;
        TextView tvCategoryName, tvRequestDate, tvRegNo, tvRequestNo;

        public RequestViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            btn_no_quotes = itemView.findViewById(R.id.btn_no_quotes);
            mcv_card = itemView.findViewById(R.id.mcv_card);
            tv_cancel = itemView.findViewById(R.id.tv_cancel);
            tv_pause = itemView.findViewById(R.id.tv_pause);
            tvCategoryName = itemView.findViewById(R.id.tv_category_name);
            tvRequestNo = itemView.findViewById(R.id.tv_request_no);
            tvRequestDate = itemView.findViewById(R.id.tv_request_date);
            tvRegNo = itemView.findViewById(R.id.tv_reg_no);
        }
    }
}
