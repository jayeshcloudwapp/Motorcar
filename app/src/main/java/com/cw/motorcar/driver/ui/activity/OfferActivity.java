package com.cw.motorcar.driver.ui.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.cw.motorcar.R;
import com.cw.motorcar.driver.adapter.OfferAdapter;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OfferActivity extends AppCompatActivity {

    private Context context;
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolBarTitle;
    @BindView(R.id.rv_offer_list)
    RecyclerView rv_offer_list;
    @BindView(R.id.tv_filter)
    TextView tv_filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);
        initView();
    }

    private void initView() {
        context = OfferActivity.this;
        ButterKnife.bind(this);
        //tvToolBarTitle.setText("Offer");
        setData();
    }

    private void setData() {
        rv_offer_list.setLayoutManager(new LinearLayoutManager(context));
        OfferAdapter offerAdapter = new OfferAdapter(context);
        rv_offer_list.setAdapter(offerAdapter);
    }

    @OnClick(R.id.iv_back)
    void goToBack() {
        onBackPressed();
    }

    @OnClick(R.id.tv_filter)
    void filteroffer() {
        popUpOffer();
    }

    private void popUpOffer() {
        final Dialog mBottomSheetDialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.popup_offer, null);
        mBottomSheetDialog.setContentView(view); // your custom view.
        AppCompatImageView ivCross = mBottomSheetDialog.findViewById(R.id.iv_cross);
        MaterialButton btnOk = mBottomSheetDialog.findViewById(R.id.btn_ok);
        MaterialButton btnCancel = mBottomSheetDialog.findViewById(R.id.btn_cancel);
        CheckBox cb_battery_replace = mBottomSheetDialog.findViewById(R.id.cb_battery_replace);
        CheckBox cb_car_care = mBottomSheetDialog.findViewById(R.id.cb_car_care);
        CheckBox cb_car_repair = mBottomSheetDialog.findViewById(R.id.cb_car_repair);
        CheckBox cb_towing = mBottomSheetDialog.findViewById(R.id.cb_towing);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                mBottomSheetDialog.dismiss();
                List<String> otherService;
                otherService = new ArrayList<>();
                if (cb_battery_replace.isChecked()) {
                    otherService.add(cb_battery_replace.getText().toString());
                }
                if (cb_car_care.isChecked()) {
                    otherService.add(cb_car_care.getText().toString());
                }
                if (cb_car_repair.isChecked()) {
                    otherService.add(cb_car_repair.getText().toString());
                }
                if (cb_towing.isChecked()) {
                    otherService.add(cb_towing.getText().toString());
                }

                String value = String.join(",", otherService);
                tv_filter.setText(value);
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

}