package com.cw.motorcar.shop.ui.cart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.cw.motorcar.R;
import com.cw.motorcar.driver.ui.activity.HomeActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SuccessOrderActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_order);
        initView();
    }

    private void initView() {
        context = SuccessOrderActivity.this;
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_shop_more)
    void shopMore() {
        startActivity(new Intent(context, HomeActivity.class).putExtra("shop","shop").addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
}