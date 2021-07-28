package com.cw.motorcar.driver.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cw.motorcar.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import org.jetbrains.annotations.NotNull;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.OfferViewHolder> {
    private Context context;

    public OfferAdapter(Context context) {
        this.context = context;
    }

    @Override
    public OfferViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_offer, parent, false);
        return new OfferViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull OfferViewHolder holder, int position) {

        holder.mtvAvail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                availPopup();
            }
        });
    }

    private void availPopup() {
        final Dialog mBottomSheetDialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.popup_custom, null);
        mBottomSheetDialog.setContentView(view); // your custom view.
        MaterialButton no_btn = mBottomSheetDialog.findViewById(R.id.no_btn);
        MaterialButton yes_button = mBottomSheetDialog.findViewById(R.id.yes_button);
        TextView tvTitle = mBottomSheetDialog.findViewById(R.id.tv_title);
        TextView tvMsg = mBottomSheetDialog.findViewById(R.id.tv_msg);
        tvTitle.setText(R.string.avail_offer);
        tvMsg.setText(R.string.are_you_sure_you_want_to_avail_offer);
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

    public class OfferViewHolder extends RecyclerView.ViewHolder {
        protected MaterialTextView mtvAvail;

        public OfferViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            mtvAvail = itemView.findViewById(R.id.mtv_avail);
        }
    }
}
