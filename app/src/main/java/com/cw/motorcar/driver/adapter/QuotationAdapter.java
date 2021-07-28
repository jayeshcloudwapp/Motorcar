package com.cw.motorcar.driver.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cw.motorcar.R;
import com.cw.motorcar.driver.ui.activity.request.SellerDetailsActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import org.jetbrains.annotations.NotNull;

public class QuotationAdapter extends RecyclerView.Adapter<QuotationAdapter.QuotationViewHolder> {

    private Context context;

    public QuotationAdapter(Context context) {
        this.context = context;
    }


    @Override
    public QuotationViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_quotation, parent, false);
        return new QuotationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull QuotationViewHolder holder, int position) {

        holder.tvDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, SellerDetailsActivity.class));
            }
        });

        holder.tvAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acceptPopup();
            }
        });
        if (position == 1) {
            holder.ivImg.setImageResource(R.drawable.ic_preferred);
        } else {
            holder.ivImg.setImageResource(R.drawable.ic_featured);
        }

    }

    private void acceptPopup() {
        final Dialog mBottomSheetDialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.popup_custom, null);
        mBottomSheetDialog.setContentView(view); // your custom view.

        MaterialButton no_btn = mBottomSheetDialog.findViewById(R.id.no_btn);
        MaterialButton yes_button = mBottomSheetDialog.findViewById(R.id.yes_button);
        TextView tvTitle = mBottomSheetDialog.findViewById(R.id.tv_title);
        TextView tvMsg = mBottomSheetDialog.findViewById(R.id.tv_msg);

        tvTitle.setText(R.string.accept);
        tvMsg.setText(R.string.do_you_want_to_accept);
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
        return 5;
    }

    public class QuotationViewHolder extends RecyclerView.ViewHolder {
        protected MaterialTextView tvDetails, tvAccept;
        protected ImageView ivImg;

        public QuotationViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvDetails = itemView.findViewById(R.id.tv_details);
            ivImg = itemView.findViewById(R.id.iv_img);
            tvAccept = itemView.findViewById(R.id.tv_accept);
        }
    }
}
