package com.cw.motorcar.merchant.ui.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.cw.motorcar.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.tv_toolbar_title)
    TextView tvTollbarTitle;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        context = RegisterActivity.this;
        ButterKnife.bind(this);
        tvTollbarTitle.setText(R.string.add_account);
    }

    @OnClick(R.id.iv_back)
    void back() {
        onBackPressed();
    }

    @OnClick(R.id.btn_next)
    void goToExpertise() {
        startActivity(new Intent(context, SelectExpertiseActivity.class));
    }
}