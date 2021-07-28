package com.cw.motorcar.driver.ui.activity.request;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cw.motorcar.R;
import com.cw.motorcar.driver.adapter.ViewImagesAdapter;
import com.cw.motorcar.driver.model.MyRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RequestDetailsActivity extends AppCompatActivity {

    private Context context;
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.tv_toolbar_right_title)
    TextView tv_toolbar_right_title;
    @BindView(R.id.iv_back)
    ImageView ivBack;

    @BindView(R.id.tv_main_service)
    TextView tv_main_service;
    @BindView(R.id.tv_subcat)
    TextView tv_subcat;
    @BindView(R.id.tv_additional_det)
    TextView tv_additional_det;
    @BindView(R.id.tv_additional_req)
    TextView tv_additional_req;
    @BindView(R.id.tv_location)
    TextView tv_location;
    @BindView(R.id.tv_posting_date)
    TextView tv_posting_date;
    @BindView(R.id.tv_preferred_date)
    TextView tv_preferred_date;
    @BindView(R.id.tv_brand)
    TextView tv_brand;
    @BindView(R.id.tv_model)
    TextView tv_model;
    @BindView(R.id.tv_register_no)
    TextView tv_register_no;
    @BindView(R.id.tv_make)
    TextView tv_make;
    @BindView(R.id.tv_fuel_type)
    TextView tv_fuel_type;
    @BindView(R.id.tv_vin_no)
    TextView tv_vin_no;
    @BindView(R.id.rv_images)
    RecyclerView rvImages;

    MyRequest myRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_details);
        initview();
    }

    private void initview() {
        context = RequestDetailsActivity.this;
        ButterKnife.bind(this);
        tvToolbarTitle.setText(getString(R.string.request_details));
        Intent intent = getIntent();
        if (intent.hasExtra("myrequest")) {
            myRequest = (MyRequest) intent.getSerializableExtra("myrequest");
            tv_toolbar_right_title.setText("Request ID " + myRequest.getRequestId());
            setData();
        }
    }

    private void setData() {
        tv_main_service.setText(myRequest.getCategory());
        tv_subcat.setText(myRequest.getSubcategory());
        tv_additional_det.setText(myRequest.getAdditionalReq());
        tv_additional_req.setText(myRequest.getAdditionalReq());
        tv_location.setText(myRequest.getLocation());
        tv_posting_date.setText(myRequest.getCreatedAt());
        tv_preferred_date.setText(myRequest.getDateTime());
        tv_brand.setText(myRequest.getCardata().getBrand());
        tv_model.setText(myRequest.getCardata().getModel());
        tv_register_no.setText(myRequest.getCardata().getRegNo());
        tv_make.setText(myRequest.getCardata().getYear());
        tv_fuel_type.setText(myRequest.getCardata().getFuelType());
        tv_vin_no.setText(myRequest.getCardata().getVinNo());
        rvImages.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        ViewImagesAdapter viewImagesAdapter = new ViewImagesAdapter(context, myRequest.getImages());
        rvImages.setAdapter(viewImagesAdapter);
    }

    @OnClick(R.id.iv_back)
    void gotoBack() {
        onBackPressed();
    }

//    @OnClick(R.id.iv_img)
//    void imagefull() {
//        popUpFullImage();
//    }

}