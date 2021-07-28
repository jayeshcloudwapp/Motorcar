package com.cw.motorcar.shop.ui.order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.widget.TextView;

import com.cw.motorcar.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderDetailsActivity extends AppCompatActivity {

    private Context context;
    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        initView();
    }

    private void initView() {
        context = OrderDetailsActivity.this;
        ButterKnife.bind(this);
        tvTitle.setText(R.string.order_details);
    }

    @OnClick(R.id.iv_back)
    void back() {
        onBackPressed();
    }
}