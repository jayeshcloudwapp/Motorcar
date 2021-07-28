package com.cw.motorcar.merchant.adapter;

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
import com.cw.motorcar.merchant.ui.Activity.MerchantRequestDetailsActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import org.jetbrains.annotations.NotNull;

public class MerInprogressAdapter extends RecyclerView.Adapter<MerInprogressAdapter.InprogressViewHolder> {
    private Context context;

    public MerInprogressAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public InprogressViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mer_inprogress, parent, false);
        return new InprogressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull InprogressViewHolder holder, int position) {

        holder.btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                completePopup();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, MerchantRequestDetailsActivity.class));
            }
        });
    }

    private void completePopup() {
        final Dialog mBottomSheetDialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.popup_custom, null);
        mBottomSheetDialog.setContentView(view); // your custom view.
        MaterialButton no_btn = mBottomSheetDialog.findViewById(R.id.no_btn);
        MaterialButton yes_button = mBottomSheetDialog.findViewById(R.id.yes_button);
        TextView tvTitle = mBottomSheetDialog.findViewById(R.id.tv_title);
        TextView tvMsg = mBottomSheetDialog.findViewById(R.id.tv_msg);

        tvTitle.setText(R.string.complete);
        tvMsg.setText(R.string.do_you_want_to_complete);

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
        return 4;
    }

    public class InprogressViewHolder extends RecyclerView.ViewHolder {
        protected MaterialTextView btnComplete;

        public InprogressViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            btnComplete = itemView.findViewById(R.id.btn_complet);
        }
    }
}
