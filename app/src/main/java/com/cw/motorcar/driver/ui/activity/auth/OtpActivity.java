package com.cw.motorcar.driver.ui.activity.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.cw.motorcar.R;
import com.cw.motorcar.custom.UiUtils;
import com.cw.motorcar.merchant.ui.auth.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OtpActivity extends AppCompatActivity {

    private Context context;
    @BindView(R.id.tv_resend)
    TextView tv_resend;
    @BindView(R.id.tv_resend_txt)
    TextView tv_resend_txt;
    String action = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        initView();
    }

    private void initView() {
        context = OtpActivity.this;
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent.hasExtra("action")) {
            action = intent.getStringExtra("action");
        }
        new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                tv_resend.setText(String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                UiUtils.animateFadeHide(context, tv_resend);
                UiUtils.animateFadeShow(context, tv_resend_txt);

            }

        }.start();

    }

    @OnClick(R.id.iv_back)
    void goToBack() {
        onBackPressed();
    }

    @OnClick(R.id.btn_otp)
    void goToHome() {
        switch (action) {
            case "Driver":
                startActivity(new Intent(context, SignUpActivity.class));
                break;
            case "Merchant":
                startActivity(new Intent(context, RegisterActivity.class));
                break;
        }

    }
}