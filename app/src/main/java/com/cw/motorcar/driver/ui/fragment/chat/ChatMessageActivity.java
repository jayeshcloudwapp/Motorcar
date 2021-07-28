package com.cw.motorcar.driver.ui.fragment.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.cw.motorcar.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatMessageActivity extends AppCompatActivity {

    private Context context;
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolBarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_message);
        initView();
    }
    private void initView() {
        context = ChatMessageActivity.this;
        ButterKnife.bind(this);
         tvToolBarTitle.setText("Amara");

    }

    @OnClick(R.id.iv_back)
    void goToBack() {
        onBackPressed();
    }
}