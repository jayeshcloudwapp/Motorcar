package com.cw.motorcar.driver.ui.activity.contactadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.cw.motorcar.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactAdminActivity extends AppCompatActivity {

    private Context context;
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_admin);
        initView();
    }

    private void initView() {
        context = ContactAdminActivity.this;
        ButterKnife.bind(this);
        tvToolbarTitle.setText(getString(R.string.contact_admin));
    }

    @OnClick(R.id.iv_back)
    void back() {
        onBackPressed();
    }
}