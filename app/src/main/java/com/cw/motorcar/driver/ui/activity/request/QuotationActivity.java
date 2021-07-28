package com.cw.motorcar.driver.ui.activity.request;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.cw.motorcar.R;
import com.cw.motorcar.driver.adapter.QuotationAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuotationActivity extends AppCompatActivity {

    private Context context;
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.rv_qt_list)
    RecyclerView rvQtList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quatation);
        initview();
    }

    private void initview() {
        context = QuotationActivity.this;
        ButterKnife.bind(this);
        tvToolbarTitle.setText(getString(R.string.quotations));
        setData();
    }

    private void setData() {
        QuotationAdapter quotationAdapter = new QuotationAdapter(context);
        rvQtList.setLayoutManager(new LinearLayoutManager(context));
        rvQtList.setAdapter(quotationAdapter);

    }

    @OnClick(R.id.iv_back)
    void gotoBack() {
        onBackPressed();
    }
}