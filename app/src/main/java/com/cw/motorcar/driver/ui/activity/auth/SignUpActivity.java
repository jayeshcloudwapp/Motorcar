package com.cw.motorcar.driver.ui.activity.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.cw.motorcar.R;
import com.cw.motorcar.custom.ProgressDialog;
import com.cw.motorcar.custom.UiUtils;
import com.cw.motorcar.databinding.ActivitySignUpBinding;
import com.cw.motorcar.driver.ui.activity.HomeActivity;
import com.cw.motorcar.driver.ui.activity.auth.model.CommonResponce;
import com.theartofdev.edmodo.cropper.CropImage;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpActivity extends AppCompatActivity implements AuthListener {

    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;
    @BindView(R.id.frameLayout2)
    FrameLayout imageFram;
    @BindView(R.id.civ_profile)
    CircleImageView civProfile;
    private Context context;
    private String picturePath;
    RegisterViewModel registerViewModel;
    private ProgressDialog progressDialog;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sign_up);
        ActivitySignUpBinding activitySignUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
        activitySignUpBinding.setSignupviewmodel(registerViewModel);
        registerViewModel.authListener = this;
        initView();
    }

    @OnClick(R.id.iv_back)
    void goToBack() {
        onBackPressed();
    }

    @OnClick(R.id.btn_save)
    void goToHome() {
        registerViewModel.image = picturePath;
        registerViewModel.token = token;
        registerViewModel.onRegisterClickButtion();
//        startActivity(new Intent(context, HomeActivity.class));
        // openPopupTerms();
    }

    @OnClick(R.id.frameLayout2)
    void openImage() {
        openImagePicker();
    }

    private void openImagePicker() {
        CropImage.activity()
                .start(this);
    }

    private void initView() {
        context = SignUpActivity.this;
        progressDialog = new ProgressDialog((Activity) context);
        ButterKnife.bind(this);
        tvTitle.setText(R.string.register_account);
        Intent intent = getIntent();
        if (intent.hasExtra("token")) {
            token = intent.getStringExtra("token");
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK) {
                    Uri resultUri = result.getUri();
                    picturePath = resultUri.getPath();
                    Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                    Bitmap bitmap = Bitmap.createScaledBitmap(thumbnail, 720, 1280, true);
                    civProfile.setImageBitmap(bitmap);
                }
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
            public void onChanged(CommonResponce commonResponce) {
                progressDialog.dismiss();
                UiUtils.showMessage(context, civProfile, commonResponce.getMessage());
                if (commonResponce.getStatus()) {
                    startActivity(new Intent(context, HomeActivity.class));
                }
            }
        });
    }

    @Override
    public void onFailure(String meessage) {
        progressDialog.dismiss();
        UiUtils.showMessage(context, civProfile, meessage);
    }
}