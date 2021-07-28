package com.cw.motorcar.shop.ui.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cw.motorcar.R;
import com.cw.motorcar.shop.adapter.CartAdapter;
import com.cw.motorcar.shop.ui.address.AddAddressActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyCartActivity extends AppCompatActivity {

    private Context context;
    @BindView(R.id.rv_cart_item)
    RecyclerView rvCartItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);
        initView();
    }

    private void initView() {
        context = MyCartActivity.this;
        ButterKnife.bind(this);
        setData();
    }

    private void setData() {
        rvCartItem.setLayoutManager(new LinearLayoutManager(context));
        CartAdapter cartAdapter = new CartAdapter(context);
        rvCartItem.setAdapter(cartAdapter);
    }

    @OnClick(R.id.iv_back)
    void goToBack() {
        onBackPressed();
    }

    @OnClick(R.id.tv_btn_change)
    void changeAddress() {
        bottomPopup();
    }

    @OnClick(R.id.btn_place_order)
    void orderPLaced() {
        startActivity(new Intent(context, SuccessOrderActivity.class));
    }

    private void bottomPopup() {
        View view = LayoutInflater.from(context).inflate(R.layout.popup_bottom_address, null);
        final BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(context, R.style.MaterialDialogSheet);
        mBottomSheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.alpha(Color.WHITE)));
        mBottomSheetDialog.setContentView(view); // your custom view.
        TextView tv_add_address = mBottomSheetDialog.findViewById(R.id.tv_add_address);
        tv_add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomSheetDialog.dismiss();
                startActivity(new Intent(context, AddAddressActivity.class));
            }
        });
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.setCanceledOnTouchOutside(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
        mBottomSheetDialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_up,
                R.anim.slide_down);
    }
}