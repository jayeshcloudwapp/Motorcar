package com.cw.motorcar.driver.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cw.motorcar.R;
import com.cw.motorcar.data.network.ApiClient;
import com.cw.motorcar.driver.model.Image;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ViewImagesAdapter extends RecyclerView.Adapter<ViewImagesAdapter.ImagesViewHolder> {
    private Context context;
    private List<Image> images;

    public ViewImagesAdapter(Context context, List<Image> images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public ImagesViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image_view, parent, false);
        return new ImagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ImagesViewHolder holder, int position) {
        Image image = images.get(position);
        Glide.with(context).load(ApiClient.SITE_URL + image.getPath()).into(holder.iv_img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popUpFullImage(position);
            }
        });
    }

    private void popUpFullImage(int pos) {
        final Dialog mBottomSheetDialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.popup_image, null);
        mBottomSheetDialog.setContentView(view); // your custom view.
        AppCompatImageView ivCross = mBottomSheetDialog.findViewById(R.id.iv_cross);
        ImageView imageView9 = mBottomSheetDialog.findViewById(R.id.imageView9);
        Glide.with(context).load(ApiClient.SITE_URL + images.get(pos).getPath()).into(imageView9);
        ivCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomSheetDialog.dismiss();
            }
        });

        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.setCanceledOnTouchOutside(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.show();
    }

    @Override
    public int getItemCount() {
        return images == null ? 0 : images.size();
    }

    public class ImagesViewHolder extends RecyclerView.ViewHolder {
        protected ImageView iv_img;

        public ImagesViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            iv_img = itemView.findViewById(R.id.iv_img);
        }
    }
}
