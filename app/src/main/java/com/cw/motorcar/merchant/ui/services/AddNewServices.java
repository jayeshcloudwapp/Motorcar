package com.cw.motorcar.merchant.ui.services;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.cw.motorcar.R;
import com.google.android.material.button.MaterialButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNewServices extends AppCompatActivity {

    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_services);
        initView();
    }

    private void initView() {
        context = AddNewServices.this;
        ButterKnife.bind(this);
        tvTitle.setText(R.string.add_new_services);
    }

    @OnClick(R.id.iv_back)
    void back() {
        onBackPressed();
    }

    @OnClick(R.id.btn_add)
    void add() {
        finish();
    }

    @OnClick(R.id.tv_sub_service)
    void subService() {
        openPopupSubService();
    }


    private void openPopupSubService() {
        final Dialog mBottomSheetDialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.popup_sub_service, null);
        mBottomSheetDialog.setContentView(view); // your custom view.
        AppCompatImageView ivCross = mBottomSheetDialog.findViewById(R.id.iv_cross);
        MaterialButton login_button = mBottomSheetDialog.findViewById(R.id.login_button);

        ivCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomSheetDialog.dismiss();
            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {
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
}