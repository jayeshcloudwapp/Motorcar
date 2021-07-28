package com.cw.motorcar.driver.ui.fragment.chat;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cw.motorcar.R;
import com.cw.motorcar.driver.adapter.ChatUserAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ChatUserFragment extends Fragment {

    @BindView(R.id.rv_user_list)
    RecyclerView rvUserList;
    @BindView(R.id.tv_toolbar_title)
    TextView tv_toolbar_title;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat_user, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        context = getContext();
        ButterKnife.bind(this, view);
        tv_toolbar_title.setText(getString(R.string.messages));
        ivBack.setVisibility(View.GONE);
        setData();
    }

    private void setData() {
        rvUserList.setLayoutManager(new LinearLayoutManager(context));
        ChatUserAdapter chatUserAdapter = new ChatUserAdapter(context);
        rvUserList.setAdapter(chatUserAdapter);
    }
}