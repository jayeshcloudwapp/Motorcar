package com.cw.motorcar.driver.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.cw.motorcar.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private Context context;
    private List<Uri> uris;
    private Deletelistener deletelistener;

   public interface Deletelistener {
        void onDelete(int pos);
    }

    public ImageAdapter(Context context, List<Uri> images, Deletelistener deletelistener) {
        this.context = context;
        this.uris = images;
        this.deletelistener = deletelistener;
    }

    @NonNull
    @NotNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ImageViewHolder holder, int position) {

        holder.ivImg.setImageURI(uris.get(position));
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uris.remove(position);
                notifyDataSetChanged();
                if (deletelistener != null) {
                    deletelistener.onDelete(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return uris == null ? 0 : uris.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        protected AppCompatImageView ivImg, ivDelete;

        public ImageViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            ivImg = itemView.findViewById(R.id.iv_img);
            ivDelete = itemView.findViewById(R.id.iv_delete);
        }
    }
}
