package com.cw.motorcar.driver.ui.activity.auth;

import android.content.Context;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.cw.motorcar.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePasswordActivity extends AppCompatActivity {

    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;
    @BindView(R.id.iv_old_pass)
    ImageView iv_old_pass;
    @BindView(R.id.iv_new_pass)
    ImageView iv_new_pass;
    @BindView(R.id.iv_new_cpass)
    ImageView iv_new_cpass;
    @BindView(R.id.et_old_password)
    EditText et_old_password;
    @BindView(R.id.et_new_password)
    EditText et_new_password;
    @BindView(R.id.et_new_cpassword)
    EditText et_new_cpassword;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initview();
    }

    private void initview() {
        context = ChangePasswordActivity.this;
        ButterKnife.bind(this);
        tvTitle.setText(getString(R.string.change_password));
    }


    @OnClick(R.id.iv_back)
    void back() {
        onBackPressed();
    }

    public void ShowHideOldPass(View view) {
        if (view.getId() == R.id.iv_old_pass) {

            if (et_old_password.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                ((ImageView) (view)).setImageResource(R.drawable.ic_hide_eye);
                //Show Password
                et_old_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                ((ImageView) (view)).setImageResource(R.drawable.ic_eye);

                //Hide Password
                et_old_password.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }

    public void ShowHidenewPass(View view) {
        if (view.getId() == R.id.iv_new_pass) {

            if (et_new_password.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                ((ImageView) (view)).setImageResource(R.drawable.ic_hide_eye);
                //Show Password
                et_new_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                ((ImageView) (view)).setImageResource(R.drawable.ic_eye);

                //Hide Password
                et_new_password.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }

    public void ShowHidenewcPass(View view) {
        if (view.getId() == R.id.iv_new_cpass) {

            if (et_new_cpassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                ((ImageView) (view)).setImageResource(R.drawable.ic_hide_eye);
                //Show Password
                et_new_cpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                ((ImageView) (view)).setImageResource(R.drawable.ic_eye);

                //Hide Password
                et_new_cpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }
}