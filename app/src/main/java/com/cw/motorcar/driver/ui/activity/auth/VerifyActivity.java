package com.cw.motorcar.driver.ui.activity.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.cw.motorcar.R;
import com.cw.motorcar.custom.ProgressDialog;
import com.cw.motorcar.custom.UiUtils;
import com.cw.motorcar.databinding.ActivityVerifyBinding;
import com.cw.motorcar.driver.ui.activity.auth.model.CommonResponce;
import com.cw.motorcar.merchant.ui.auth.RegisterActivity;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VerifyActivity extends AppCompatActivity implements AuthListener {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_country)
    CountryCodePicker etCountry;
    private Context context;
    String action = "";
    VerifyViewModel verifyViewModel;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_verify);
        ActivityVerifyBinding verifyBinding = DataBindingUtil.setContentView(this, R.layout.activity_verify);
        verifyViewModel = ViewModelProviders.of(this).get(VerifyViewModel.class);
        verifyBinding.setVerifyviewmodel(verifyViewModel);
        verifyViewModel.authListener = this;
        initView();
    }

    private void initView() {
        context = VerifyActivity.this;
        progressDialog = new ProgressDialog((Activity) context);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent.hasExtra("action")) {
            action = intent.getStringExtra("action");
        }
    }

    @OnClick(R.id.btn_recive_code)
    void goToOtp() {
        verifyViewModel.countryCode = etCountry.getSelectedCountryCodeWithPlus().toString();
        verifyViewModel.onVerifyClickButtion();
//        startActivity(new Intent(context, OtpActivity.class).putExtra("action", action));
    }

    @OnClick(R.id.iv_back)
    void goToBack() {
        onBackPressed();
    }

    @Override
    public void onStarted() {
        progressDialog.show();
    }

    @Override
    public void onSuccess(LiveData<CommonResponce> loginResponce) {
        loginResponce.observe(this, new Observer<CommonResponce>() {
            @Override
            public void onChanged(CommonResponce commonResponce) {
                progressDialog.dismiss();
                UiUtils.showMessage(context, etCountry, commonResponce.getMessage());
                if (commonResponce.getStatus()) {
                    switch (action) {
                        case "Driver":
                            startActivity(new Intent(context, SignUpActivity.class).putExtra("token",commonResponce.getData().getToken()));
                            break;
                        case "Merchant":
                            startActivity(new Intent(context, RegisterActivity.class));
                            break;
                    }
                }
            }
        });
    }

    @Override
    public void onFailure(String meessage) {
        progressDialog.dismiss();
        UiUtils.showMessage(context, etCountry, meessage);
    }
}