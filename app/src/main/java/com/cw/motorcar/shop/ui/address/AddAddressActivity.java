package com.cw.motorcar.shop.ui.address;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.cw.motorcar.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddAddressActivity extends AppCompatActivity {

    private Context context;
    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        initView();
    }

    private void initView() {
        context = AddAddressActivity.this;
        ButterKnife.bind(this);
        tvTitle.setText(R.string.add_address);
    }

    @OnClick(R.id.iv_back)
    void back() {
        onBackPressed();
    }

    @OnClick(R.id.btn_save)
    void save() {
        finish();
    }
}