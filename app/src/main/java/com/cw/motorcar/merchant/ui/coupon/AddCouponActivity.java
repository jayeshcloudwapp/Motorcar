package com.cw.motorcar.merchant.ui.coupon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.cw.motorcar.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddCouponActivity extends AppCompatActivity {

    private Context context;
    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_coupon);
        initView();
    }

    private void initView() {
        context = AddCouponActivity.this;
        ButterKnife.bind(this);
        tvTitle.setText("Apply Coupon");

    }

    @OnClick(R.id.iv_back)
    void back() {
        onBackPressed();
    }
}