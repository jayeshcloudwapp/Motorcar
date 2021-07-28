package com.cw.motorcar.merchant.ui.offer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.cw.motorcar.R;
import com.cw.motorcar.merchant.adapter.MerchantOfferAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MerchantOfferActivity extends AppCompatActivity {

    private Context context;
    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;
    @BindView(R.id.tv_add_offer)
    TextView tv_add_offer;
    @BindView(R.id.rv_merchant_offer)
    RecyclerView rvMerchantOffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_offer);
        initView();
    }

    private void initView() {
        context = MerchantOfferActivity.this;
        ButterKnife.bind(this);
        setData();
    }

    @OnClick(R.id.tv_add_offer)
    void addOffer() {
        startActivity(new Intent(context, AddOfferActivity.class));
    }

    @OnClick(R.id.iv_back)
    void back() {
        onBackPressed();
    }

    private void setData() {
        rvMerchantOffer.setLayoutManager(new LinearLayoutManager(context));
        MerchantOfferAdapter merchantOfferAdapter = new MerchantOfferAdapter(context);
        rvMerchantOffer.setAdapter(merchantOfferAdapter);
    }
}