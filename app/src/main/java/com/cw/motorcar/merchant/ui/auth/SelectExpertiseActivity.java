package com.cw.motorcar.merchant.ui.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.cw.motorcar.R;
import com.cw.motorcar.merchant.ui.home.HomeMerchantActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectExpertiseActivity extends AppCompatActivity {

    @BindView(R.id.tv_toolbar_title)
    TextView tvTollbarTitle;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_expertise);
        initView();
    }

    private void initView() {
        context = SelectExpertiseActivity.this;
        ButterKnife.bind(this);
        tvTollbarTitle.setText(R.string.select_your_expertise);
    }

    @OnClick(R.id.iv_back)
    void back() {
        onBackPressed();
    }

    @OnClick(R.id.btn_submit)
    void goToMerchantHome() {
        startActivity(new Intent(context, HomeMerchantActivity.class));
    }
}