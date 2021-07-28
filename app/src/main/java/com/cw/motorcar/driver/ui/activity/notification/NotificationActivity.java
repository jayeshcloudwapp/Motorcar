package com.cw.motorcar.driver.ui.activity.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.cw.motorcar.R;
import com.cw.motorcar.driver.adapter.NotificationAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotificationActivity extends AppCompatActivity {

    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;
    @BindView(R.id.tv_toolbar_right_title)
    TextView tv_toolbar_right_title;
    @BindView(R.id.rv_notification)
    RecyclerView rvNotification;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        initview();
    }

    private void initview() {
        context = NotificationActivity.this;
        ButterKnife.bind(this);
        tvTitle.setText(R.string.notification);
        tv_toolbar_right_title.setText(R.string.clear_all);
        setData();
    }

    private void setData() {
        rvNotification.setLayoutManager(new LinearLayoutManager(context));
        NotificationAdapter notificationAdapter = new NotificationAdapter(context);
        rvNotification.setAdapter(notificationAdapter);
    }

    @OnClick(R.id.iv_back)
    void back() {
        onBackPressed();
    }
}