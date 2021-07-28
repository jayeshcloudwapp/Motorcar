package com.cw.motorcar.merchant.ui.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.cw.motorcar.R;
import com.cw.motorcar.merchant.adapter.CustomerIntrestAdapter;
import com.cw.motorcar.merchant.ui.home.HomeMerchantActivity;
import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker;
import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomerIntrestActivity extends AppCompatActivity {

    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_merchant_toolbar_title)
    TextView tvMerchantToolbarTitle;

    @BindView(R.id.rv_cust_int_list)
    RecyclerView rvCustInst;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_intrest);
        initView();
    }

    private void initView() {
        context = CustomerIntrestActivity.this;
        ButterKnife.bind(this);
        tvMerchantToolbarTitle.setText("Customer Interests");
        setData();
    }


    @OnClick(R.id.iv_back)
    void back() {
        onBackPressed();
    }

    @OnClick(R.id.tv_date)
    void getdate() {
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
                            simpleDateFormat = new SimpleDateFormat("d MMM YYYY HH:mm", Locale.getDefault());
                            tvDate.setText(simpleDateFormat.format(date));
                        }
                    }
                }).display();
    }

    private void setData() {
        rvCustInst.setLayoutManager(new LinearLayoutManager(context));
        CustomerIntrestAdapter customerIntrestAdapter = new CustomerIntrestAdapter(context);
        rvCustInst.setAdapter(customerIntrestAdapter);
    }
}