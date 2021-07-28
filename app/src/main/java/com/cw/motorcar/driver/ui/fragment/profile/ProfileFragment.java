package com.cw.motorcar.driver.ui.fragment.profile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cw.motorcar.R;
import com.cw.motorcar.custom.ProgressDialog;
import com.cw.motorcar.custom.UiUtils;
import com.cw.motorcar.data.network.ApiClient;
import com.cw.motorcar.databinding.FragmentProfileBinding;
import com.cw.motorcar.driver.ui.activity.auth.AuthListener;
import com.cw.motorcar.driver.ui.activity.auth.model.CommonResponce;
import com.cw.motorcar.driver.ui.activity.auth.model.UserData;
import com.cw.motorcar.storage.SharedPrefManager;
import com.theartofdev.edmodo.cropper.CropImage;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends AppCompatActivity implements AuthListener {

    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.civ_profile)
    CircleImageView civProfile;
    @BindView(R.id.editTextTextPersonName)
    EditText editTextTextPersonName;
    @BindView(R.id.editTextTextPersonEmail)
    EditText editTextTextPersonEmail;
    @BindView(R.id.editTextTextPersonMobile)
    EditText editTextTextPersonMobile;
    @BindView(R.id.editTextTextPersonAddress)
    EditText editTextTextPersonAddress;
    private Context context;
    ProfileViewModel profileViewModel;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_profile);
        FragmentProfileBinding binding = DataBindingUtil.setContentView(this, R.layout.fragment_profile);
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        binding.setProfileviewmodel(profileViewModel);
        profileViewModel.authListener = this;
        initview();
    }

    private void initview() {
        context = ProfileFragment.this;
        ButterKnife.bind(this);
        progressDialog = new ProgressDialog((Activity) context);

        tvTitle.setText(R.string.edit_profile);
        setData();
    }

    private void setData() {
        UserData userData = SharedPrefManager.getInstance(context).getUser();
        Glide.with(context).load(ApiClient.SITE_URL + userData.getImage()).into(civProfile);
        profileViewModel.token = userData.getToken();
        profileViewModel.name = userData.getName();
        profileViewModel.email = userData.getEmail();
        profileViewModel.address = userData.getAddress();
        profileViewModel.mobile = userData.getPhone();
    }

    @OnClick(R.id.iv_back)
    void gotoBack() {
        onBackPressed();
    }

    @OnClick(R.id.frm_image)
    void openImage() {
        openImagePicker();
    }

    private void openImagePicker() {
        CropImage.activity()
                .start(this);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK) {
                    Uri resultUri = result.getUri();
                    profileViewModel.image = resultUri.getPath();
                    Bitmap thumbnail = (BitmapFactory.decodeFile(resultUri.getPath()));
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
                UiUtils.showMessage(context, civProfile, commonResponce.getMessage());
                progressDialog.dismiss();
                if (commonResponce.getStatus()) {
                    SharedPrefManager.getInstance(context).userLogin(commonResponce.getData());
                    setData();
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