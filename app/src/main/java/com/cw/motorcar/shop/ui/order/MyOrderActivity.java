package com.cw.motorcar.shop.ui.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.cw.motorcar.R;
import com.cw.motorcar.shop.adapter.MyOrderAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyOrderActivity extends AppCompatActivity {

    private Context context;
    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;
    @BindView(R.id.rv_myorder)
    RecyclerView rvMyorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        initView();
    }

    private void initView() {
        context = MyOrderActivity.this;
        ButterKnife.bind(this);
        tvTitle.setText("My Order");
        setData();
    }

    private void setData() {
        rvMyorder.setLayoutManager(new LinearLayoutManager(context));
        MyOrderAdapter myOrderAdapter = new MyOrderAdapter(context);
        rvMyorder.setAdapter(myOrderAdapter);
    }

    @OnClick(R.id.iv_back)
    void back() {
        onBackPressed();
    }
}