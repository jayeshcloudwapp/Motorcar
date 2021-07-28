package com.cw.motorcar.merchant.adapter;

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
import com.cw.motorcar.merchant.ui.offer.AddOfferActivity;
import com.cw.motorcar.merchant.ui.offer.OfferDetailsActivity;
import com.google.android.material.button.MaterialButton;

import org.jetbrains.annotations.NotNull;

public class MerchantOfferAdapter extends RecyclerView.Adapter<MerchantOfferAdapter.ViewHolder> {
    private Context context;

    public MerchantOfferAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_merchant_offer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, AddOfferActivity.class).putExtra("action", "Edit"));
            }
        });

        holder.ivView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, OfferDetailsActivity.class));
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, OfferDetailsActivity.class));
            }
        });
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePopup();
            }
        });


    }


    private void deletePopup() {
        final Dialog mBottomSheetDialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.popup_custom, null);
        mBottomSheetDialog.setContentView(view); // your custom view.
        MaterialButton no_btn = mBottomSheetDialog.findViewById(R.id.no_btn);
        MaterialButton yes_button = mBottomSheetDialog.findViewById(R.id.yes_button);
        TextView tvTitle = mBottomSheetDialog.findViewById(R.id.tv_title);
        TextView tvMsg = mBottomSheetDialog.findViewById(R.id.tv_msg);

        tvTitle.setText(R.string.delete);
        tvMsg.setText(R.string.do_you_want_to_delete);

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

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected ImageView ivEdit, ivView, ivDelete;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            ivEdit = itemView.findViewById(R.id.iv_edit);
            ivView = itemView.findViewById(R.id.iv_view);
            ivDelete = itemView.findViewById(R.id.iv_delete);
        }
    }
}
