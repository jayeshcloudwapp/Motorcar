package com.cw.motorcar.driver.ui.activity.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.cw.motorcar.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgotPasswordActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        initView();
    }

    private void initView() {
        context = ForgotPasswordActivity.this;
        ButterKnife.bind(this);
        tvTitle.setText(getString(R.string.forgot_password));
    }
}