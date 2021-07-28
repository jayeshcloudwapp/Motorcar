package com.cw.motorcar.driver.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.cw.motorcar.R;
import com.cw.motorcar.driver.ui.activity.auth.LoginActivity;
import com.google.android.material.card.MaterialCardView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private Context context;

    @BindView(R.id.materialCardView)
    MaterialCardView materialCardView;
    @BindView(R.id.materialCardView2)
    MaterialCardView materialCardView2;
    @BindView(R.id.iv_image_driver)
    ImageView iv_image_driver;
    @BindView(R.id.iv_image3)
    ImageView iv_image4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        context = MainActivity.this;
        ButterKnife.bind(this);
        isDriver();
    }

    @OnClick(R.id.tv_login)
    void login() {
        startActivity(new Intent(context, LoginActivity.class));
    }

    @OnClick(R.id.materialCardView)
    void isDriver() {
        materialCardView.setBackgroundResource(R.drawable.selector_type);
        materialCardView2.setBackgroundResource(R.drawable.unselect_type);
        iv_image_driver.setImageResource(R.drawable.ic_user_black);
        iv_image4.setImageResource(R.drawable.ic_marchent);
    }

    @OnClick(R.id.materialCardView2)
    void isMerchant() {
        materialCardView.setBackgroundResource(R.drawable.unselect_type);
        materialCardView2.setBackgroundResource(R.drawable.selector_type);
        iv_image_driver.setImageResource(R.drawable.ic_user_grey);
        iv_image4.setImageResource(R.drawable.ic_marchent_black);
    }
}