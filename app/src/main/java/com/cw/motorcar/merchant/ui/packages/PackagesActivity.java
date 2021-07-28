package com.cw.motorcar.merchant.ui.packages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.cw.motorcar.R;
import com.cw.motorcar.merchant.adapter.PackageAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PackagesActivity extends AppCompatActivity {

    @BindView(R.id.rv_packages)
    RecyclerView rvPackages;
    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packages);
        initView();

    }

    private void initView() {
        context = PackagesActivity.this;
        ButterKnife.bind(this);
        tvTitle.setText("Packages");
        setData();
    }

    private void setData() {
        rvPackages.setLayoutManager(new LinearLayoutManager(context));
        PackageAdapter packageAdapter = new PackageAdapter(context);
        rvPackages.setAdapter(packageAdapter);

    }

    @OnClick(R.id.iv_back)
    void back() {
        onBackPressed();
    }
}