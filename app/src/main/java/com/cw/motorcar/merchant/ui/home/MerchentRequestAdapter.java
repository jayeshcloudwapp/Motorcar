package com.cw.motorcar.merchant.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cw.motorcar.R;

import org.jetbrains.annotations.NotNull;

public class MerchentRequestAdapter extends RecyclerView.Adapter<MerchentRequestAdapter.RequestViewHolder> {

    private Context context;

    public MerchentRequestAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_merchant_request, parent, false);
        return new RequestViewHolder(view);
    }

    public void startBlinkingAnimation(TextView textView) {
        Animation startAnimation = AnimationUtils.loadAnimation(context.getApplicationContext(), R.anim.blinking_animation);
        textView.startAnimation(startAnimation);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RequestViewHolder holder, int position) {
        startBlinkingAnimation(holder.tv_category_name);
//        startBlinkingAnimation(holder.tv_date);
//        startBlinkingAnimation(holder.tv_km);
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class RequestViewHolder extends RecyclerView.ViewHolder {
        protected TextView tv_category_name, tv_date, tv_km;

        public RequestViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tv_category_name = itemView.findViewById(R.id.tv_category_name);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_km = itemView.findViewById(R.id.tv_km);
        }
    }
}
