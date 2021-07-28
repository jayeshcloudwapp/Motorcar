package com.cw.motorcar.driver.ui.activity.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cw.motorcar.R;
import com.cw.motorcar.custom.ProgressDialog;
import com.cw.motorcar.custom.UiUtils;
import com.cw.motorcar.databinding.ActivityLoginBinding;
import com.cw.motorcar.driver.ui.activity.HomeActivity;
import com.cw.motorcar.driver.ui.activity.auth.model.CommonResponce;
import com.cw.motorcar.storage.SharedPrefManager;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements AuthListener {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_forgot)
    TextView tv_forgot;
    @BindView(R.id.et_pass)
    EditText et_pass;
    private Context context;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
        ActivityLoginBinding loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        LoginViewModel loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        loginBinding.setViewmodel(loginViewModel);
        loginViewModel.authListener = this;
        initView();
    }

    private void initView() {
        context = LoginActivity.this;
        ButterKnife.bind(this);
        progressDialog = new ProgressDialog((Activity) context);
        //tvTitle.setText(getString(R.string.login));
    }

    @OnClick(R.id.tv_forgot)
    void goTOForgot() {
        startActivity(new Intent(context, ForgotPasswordActivity.class));
    }

    @OnClick(R.id.btn_signup)
    void clickSignup() {
//        startActivity(new Intent(context, SignUpActivity.class));
        openPopupTerms("Driver");
    }

    @OnClick(R.id.tv_merchant_signup)
    void clickMerchantSignup() {
        //startActivity(new Intent(context, RegisterActivity.class));
        openPopupTerms("Merchant");
    }

    private void openPopupTerms(String action) {
        View view = LayoutInflater.from(context).inflate(R.layout.popup_terms, null);
        final BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(context, R.style.MaterialDialogSheet);
        mBottomSheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.alpha(Color.WHITE)));
        mBottomSheetDialog.setContentView(view); // your custom view.
        MaterialButton btn_agree = mBottomSheetDialog.findViewById(R.id.btn_agree);
        ImageView iv_cross = mBottomSheetDialog.findViewById(R.id.iv_cross);

        btn_agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomSheetDialog.dismiss();
                startActivity(new Intent(context, VerifyActivity.class).putExtra("action", action));
            }
        });

        iv_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomSheetDialog.dismiss();
            }
        });

        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.setCanceledOnTouchOutside(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
        mBottomSheetDialog.show();
    }

    public void ShowHidePass(View view) {
        if (view.getId() == R.id.iv_pass) {
            if (et_pass.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                ((ImageView) (view)).setImageResource(R.drawable.ic_hide_eye);
                //Show Password
                et_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                ((ImageView) (view)).setImageResource(R.drawable.ic_eye);
                //Hide Password
                et_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }
    }

    @Override
    public void onStarted() {
        progressDialog.show();
    }

    @Override
    public void onSuccess(LiveData<CommonResponce> loginResponce) {
        loginResponce.observe(this, new Observer<CommonResponce>() {
            @Override
            public void onChanged(CommonResponce result) {
                progressDialog.dismiss();
                UiUtils.showMessage(context, tvTitle, result.getMessage());
                if (result.getStatus()) {
                    SharedPrefManager.getInstance(context).userLogin(result.getData());
                    startActivity(new Intent(context, HomeActivity.class));
                }
            }
        });
    }

    @Override
    public void onFailure(String meessage) {
        progressDialog.dismiss();
        UiUtils.showMessage(context, tvTitle, meessage);
    }
}