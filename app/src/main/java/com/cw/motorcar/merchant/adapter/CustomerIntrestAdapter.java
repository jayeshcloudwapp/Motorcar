package com.cw.motorcar.merchant.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cw.motorcar.R;
import com.google.android.material.button.MaterialButton;

import org.jetbrains.annotations.NotNull;

public class CustomerIntrestAdapter extends RecyclerView.Adapter<CustomerIntrestAdapter.CustInstViewHolder> {
    private Context context;

    public CustomerIntrestAdapter(Context context) {
        this.context = context;
    }

    @Override
    public CustInstViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cust_inst, parent, false);
        return new CustInstViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CustInstViewHolder holder, int position) {
        holder.ivCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePopup(position);
            }
        });
    }

    private void deletePopup(int position) {
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
                notifyItemRemoved(position);
                notifyDataSetChanged();

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

    public class CustInstViewHolder extends RecyclerView.ViewHolder {
        protected ImageView ivCross;

        public CustInstViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            ivCross = itemView.findViewById(R.id.iv_cross);

        }
    }
}
