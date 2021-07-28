package com.cw.motorcar.driver.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cw.motorcar.R;
import com.cw.motorcar.driver.ui.fragment.chat.ChatMessageActivity;

import org.jetbrains.annotations.NotNull;

public class ChatUserAdapter extends RecyclerView.Adapter<ChatUserAdapter.ChatUserViewHolder> {
    private Context context;

    public ChatUserAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public ChatUserViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_chat_user, parent, false);
        return new ChatUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ChatUserViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, ChatMessageActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class ChatUserViewHolder extends RecyclerView.ViewHolder {
        public ChatUserViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }
    }

}
