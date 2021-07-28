package com.cw.motorcar.merchant.ui.offer;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.cw.motorcar.R;
import com.google.android.material.button.MaterialButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddOfferActivity extends AppCompatActivity {

    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;
    @BindView(R.id.tv_main_service)
    TextView tvMainService;
    @BindView(R.id.tv_date)
    TextView tvDate;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_offer);
        initView();
    }

    private void initView() {
        context = AddOfferActivity.this;
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent.hasExtra("action")) {
            tvTitle.setText(R.string.edit_offer);
        } else {
            tvTitle.setText(R.string.add_offer);
        }

    }

    @OnClick(R.id.iv_back)
    void back() {
        onBackPressed();
    }

    @OnClick(R.id.btn_add)
    void add() {
        finish();
    }

    @OnClick(R.id.tv_main_service)
    void selectMainService() {
        openPopupService();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @OnClick(R.id.tv_date)
    void selectDate() {
        setDate();
    }

    private void openPopupService() {
        final Dialog mBottomSheetDialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.popup_offer_main_service, null);
        mBottomSheetDialog.setContentView(view); // your custom view.
        AppCompatImageView ivCross = mBottomSheetDialog.findViewById(R.id.iv_cross);
        RadioGroup radioGroup = mBottomSheetDialog.findViewById(R.id.radioGroup);
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
                RadioButton rb_battery_replace = mBottomSheetDialog.findViewById(R.id.rb_battery_replace);
                RadioButton rb_car_care = mBottomSheetDialog.findViewById(R.id.rb_car_care);
                RadioButton rb_car_repair = mBottomSheetDialog.findViewById(R.id.rb_car_repair);
                RadioButton rb_towing = mBottomSheetDialog.findViewById(R.id.rb_towing);
                if (rb_battery_replace.isChecked()) {
                    tvMainService.setText(rb_battery_replace.getText().toString());
                } else if (rb_car_care.isChecked()) {
                    tvMainService.setText(rb_car_care.getText().toString());
                } else if (rb_car_repair.isChecked()) {
                    tvMainService.setText(rb_car_repair.getText().toString());
                } else if (rb_towing.isChecked()) {
                    tvMainService.setText(rb_towing.getText().toString());
                }
                mBottomSheetDialog.dismiss();
            }
        });

        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.setCanceledOnTouchOutside(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setDate() {

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        tvDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }
}