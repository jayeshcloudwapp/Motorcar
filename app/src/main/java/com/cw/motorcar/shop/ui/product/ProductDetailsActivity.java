package com.cw.motorcar.shop.ui.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.cw.motorcar.R;
import com.cw.motorcar.shop.adapter.Sliding_adapter;
import com.cw.motorcar.shop.ui.cart.MyCartActivity;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductDetailsActivity extends AppCompatActivity {

    private Context context;
    @BindView(R.id.pager)
    ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details2);
        initView();
    }

    private void initView() {
        context = ProductDetailsActivity.this;
        ButterKnife.bind(this);
        initslider();
    }

    @OnClick(R.id.btn_add_to_cart)
    void gotToAddToCart() {
        startActivity(new Intent(context, MyCartActivity.class));
    }

    private void initslider() {
        mPager.setAdapter(new Sliding_adapter(this));
        TabLayout indicator = (TabLayout) findViewById(R.id.indicator);
        indicator.setupWithViewPager(mPager, true);

        indicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}