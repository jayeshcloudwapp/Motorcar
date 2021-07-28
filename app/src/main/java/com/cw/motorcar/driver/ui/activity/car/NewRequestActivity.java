package com.cw.motorcar.driver.ui.activity.car;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cw.motorcar.R;
import com.cw.motorcar.custom.ProgressDialog;
import com.cw.motorcar.custom.UiUtils;
import com.cw.motorcar.data.network.ApiClient;
import com.cw.motorcar.databinding.ActivityNewRequestBinding;
import com.cw.motorcar.driver.adapter.ImageAdapter;
import com.cw.motorcar.driver.model.Carlist;
import com.cw.motorcar.driver.ui.activity.HomeActivity;
import com.cw.motorcar.driver.ui.activity.auth.AuthListener;
import com.cw.motorcar.driver.ui.activity.auth.model.CommonResponce;
import com.cw.motorcar.storage.SharedPrefManager;
import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker;
import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog;
import com.google.android.material.button.MaterialButton;
import com.theartofdev.edmodo.cropper.CropImage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewRequestActivity extends AppCompatActivity implements AuthListener {

    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;
    @BindView(R.id.tv_date_time)
    TextView tv_date_time;
    @BindView(R.id.tv_search_radius)
    TextView tvSearchRadius;
    @BindView(R.id.tv_additional_det)
    TextView tv_additional_det;
    @BindView(R.id.rv_images)
    RecyclerView rvImages;
    @BindView(R.id.frameLayout)
    FrameLayout framlayout;
    @BindView(R.id.iv_img)
    ImageView ivImg;
    @BindView(R.id.tv_car_name)
    TextView tvCarName;
    @BindView(R.id.tv_vin_no)
    TextView tvVinNo;
    @BindView(R.id.tv_reg_no)
    TextView tvRegNo;
    @BindView(R.id.tv_model)
    TextView tvModel;
    @BindView(R.id.tv_sub_service)
    TextView tvSubService;

    private Context context;
    private RadioButton radioButton;
    private List<Uri> images = new ArrayList<>();
    private String catId = "";
    private String catName = "";
    private String subCatId = "";
    private Carlist carlist;
    private NewRequstViewModel newRequstViewModel;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_new_request);
        ActivityNewRequestBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_new_request);
        newRequstViewModel = ViewModelProviders.of(this).get(NewRequstViewModel.class);
        binding.setNewrequestviewmodel(newRequstViewModel);
        newRequstViewModel.authListener = this;

        initview();
    }

    private void initview() {
        context = NewRequestActivity.this;
        ButterKnife.bind(this);
        progressDialog = new ProgressDialog((Activity) context);
        tvTitle.setText(getString(R.string.new_request));
        Intent intent = getIntent();
        if (intent.hasExtra("cat_id")) {
            catId = intent.getStringExtra("cat_id");
            subCatId = intent.getStringExtra("sub_cat_id");
            carlist = (Carlist) intent.getSerializableExtra("mycar");
            catName = intent.getStringExtra("cat_name");
            Glide.with(context).load(ApiClient.SITE_URL + carlist.getImage()).into(ivImg);
            tvCarName.setText(carlist.getBrand() + "|" + carlist.getModel() + "|" + carlist.getFuelType());
            tvModel.setText(carlist.getYear());
            tvVinNo.setText(carlist.getVinNo());
            tvRegNo.setText(carlist.getRegNo());
            tvSubService.setText(catName);
            newRequstViewModel.catId = catId;
            newRequstViewModel.subCatId = subCatId;
            newRequstViewModel.token = SharedPrefManager.getInstance(context).getUser().getToken();
        }
    }

    @OnClick(R.id.iv_back)
    void back() {
        onBackPressed();
    }
//
//    @OnClick(R.id.btn_post_requirement)
//    void postRequirement() {
//        popSuccess();
//    }

    @OnClick(R.id.tv_search_radius)
    void getRadius() {
        popUpRadius();
    }

    @OnClick(R.id.tv_additional_det)
    void tv_additional_det() {
        startActivityForResult(new Intent(context, OtherRequirementActivity.class), 301);
    }

    @OnClick(R.id.btn_cancel)
    void popupcancel() {
        deletePopup();
    }

    @OnClick(R.id.frameLayout)
    void popupImage() {
        if (images.size() < 3) {
            CropImage.activity().start(NewRequestActivity.this);
        }
    }


    private void deletePopup() {
        final Dialog mBottomSheetDialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.popup_custom, null);
        mBottomSheetDialog.setContentView(view); // your custom view.
        MaterialButton no_btn = mBottomSheetDialog.findViewById(R.id.no_btn);
        MaterialButton yes_button = mBottomSheetDialog.findViewById(R.id.yes_button);
        TextView tvTitle = mBottomSheetDialog.findViewById(R.id.tv_title);
        TextView tvMsg = mBottomSheetDialog.findViewById(R.id.tv_msg);
        tvTitle.setText(R.string.cancel);
        tvMsg.setText(R.string.do_you_want_to_cancel);
        no_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomSheetDialog.dismiss();

            }
        });

        yes_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomSheetDialog.dismiss();
                startActivity(new Intent(context, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });


        mBottomSheetDialog.setCancelable(false);
        mBottomSheetDialog.setCanceledOnTouchOutside(false);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @OnClick(R.id.tv_date_time)
    void getDate() {
//        setData();

        setDateTime();

    }

    private void setDateTime() {
        new SingleDateAndTimePickerDialog.Builder(context)
                .bottomSheet()
//                .curved()
                //.stepSizeMinutes(15)
                //.displayHours(false)
                //.displayMinutes(false)
                //.todayText("aujourd'hui")
                .backgroundColor(Color.WHITE)
                .mainColor(Color.BLUE)
                .displayListener(new SingleDateAndTimePickerDialog.DisplayListener() {
                    @Override
                    public void onDisplayed(SingleDateAndTimePicker picker) {
                        // Retrieve the SingleDateAndTimePicker
                    }
                })
                .title("Preferred Date & Time")
                .listener(new SingleDateAndTimePickerDialog.Listener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onDateSelected(Date date) {
                        SimpleDateFormat simpleDateFormat;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());
                            tv_date_time.setText(simpleDateFormat.format(date));
                            newRequstViewModel.dateTime = simpleDateFormat.format(date);
                        }
                    }
                }).display();
    }

    private void popUpRadius() {
        final Dialog mBottomSheetDialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.popup_radiuss, null);
        mBottomSheetDialog.setContentView(view); // your custom view.
        AppCompatImageView ivCross = mBottomSheetDialog.findViewById(R.id.iv_cross);
        MaterialButton btnOk = mBottomSheetDialog.findViewById(R.id.btn_ok);
        MaterialButton btnCancel = mBottomSheetDialog.findViewById(R.id.materialButton);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton rd_none = mBottomSheetDialog.findViewById(R.id.rd_none);
                RadioButton rd_5 = mBottomSheetDialog.findViewById(R.id.rd_5);
                RadioButton rd_10 = mBottomSheetDialog.findViewById(R.id.rd_10);
                RadioButton rd_20 = mBottomSheetDialog.findViewById(R.id.rd_20);
                if (rd_none.isChecked()) {
                    tvSearchRadius.setText(rd_none.getText().toString());
                    newRequstViewModel.radius = "";
                } else if (rd_5.isChecked()) {
                    tvSearchRadius.setText(rd_5.getText().toString());
                    newRequstViewModel.radius = "5";
                } else if (rd_10.isChecked()) {
                    tvSearchRadius.setText(rd_10.getText().toString());
                    newRequstViewModel.radius = "10";
                } else if (rd_20.isChecked()) {
                    tvSearchRadius.setText(rd_20.getText().toString());
                    newRequstViewModel.radius = "20";
                }
                mBottomSheetDialog.dismiss();
            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomSheetDialog.dismiss();
            }
        });

        ivCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomSheetDialog.dismiss();
            }
        });

        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.setCanceledOnTouchOutside(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setData() {

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        tv_date_time.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    private void popSuccess() {
        final Dialog mBottomSheetDialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.popup_success, null);
        mBottomSheetDialog.setContentView(view); // your custom view.
        MaterialButton btn_ok = mBottomSheetDialog.findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomSheetDialog.dismiss();
                startActivity(new Intent(context, HomeActivity.class).putExtra("pagename", "Request"));
            }
        });

        mBottomSheetDialog.setCancelable(false);
        mBottomSheetDialog.setCanceledOnTouchOutside(false);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                images.add(resultUri);
                newRequstViewModel.images = images;
                setImages();

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        } else {
            if (data != null) {
                String service = data.getStringExtra("otherservice");
                tv_additional_det.setText(service);
            }
        }
    }

    private void setImages() {
        rvImages.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        ImageAdapter imageAdapter = new ImageAdapter(context, images, new ImageAdapter.Deletelistener() {
            @Override
            public void onDelete(int pos) {
                images.remove(pos);
            }
        });
        rvImages.setAdapter(imageAdapter);
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
                if (commonResponce.getStatus()) {
                    popSuccess();
                } else {
                    UiUtils.showMessage(context, tvTitle, commonResponce.getMessage());
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