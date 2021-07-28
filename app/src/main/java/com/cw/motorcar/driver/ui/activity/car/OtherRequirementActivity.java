package com.cw.motorcar.driver.ui.activity.car;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.cw.motorcar.R;
import com.cw.motorcar.databinding.ActivityOtherRequirementBinding;
import com.cw.motorcar.driver.adapter.AttributeAdapter;
import com.cw.motorcar.driver.model.AttributeResponce;
import com.cw.motorcar.storage.SharedPrefManager;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OtherRequirementActivity extends AppCompatActivity implements Attributelistener {

    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;
    private Context context;
    List<String> otherService;
    @BindView(R.id.rv_attribute)
    RecyclerView rvAttribute;
    @BindView(R.id.shimmer_view_container)
    ShimmerFrameLayout shimmer_item_attribute;
    AttributeViewModel attributeViewModel;
//    @BindView(R.id.cb_wax)
//    CheckBox cb_wax;
//    @BindView(R.id.cb_engine_wash)
//    CheckBox cb_engine_wash;
//    @BindView(R.id.cb_car_wash)
//    CheckBox cb_car_wash;
//    @BindView(R.id.cb_full_car_wash)
//    CheckBox cb_full_car_wash;
//    @BindView(R.id.cb_cleaning)
//    CheckBox cb_cleaning;
//    @BindView(R.id.cb_interior)
//    CheckBox cb_interior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_other_requirement);
        ActivityOtherRequirementBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_other_requirement);
        attributeViewModel = ViewModelProviders.of(this).get(AttributeViewModel.class);
        binding.setAttributeviewmodel(attributeViewModel);
        attributeViewModel.atributelistenre = this;
        initview();
    }

    private void initview() {
        context = OtherRequirementActivity.this;
        ButterKnife.bind(this);
        tvTitle.setText(R.string.other_requirement);
        Intent intent = getIntent();
        attributeViewModel.token = SharedPrefManager.getInstance(context).getUser().getToken();
        if (intent.hasExtra("subCatId")) {
            attributeViewModel.subCatId = intent.getStringExtra("subCatId");
            attributeViewModel.subCatId = "1";
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        attributeViewModel.getAttribute();
    }

    @OnClick(R.id.iv_back)
    void back() {
        onBackPressed();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @OnClick(R.id.btn_submit)
    void submit() {
        otherService = new ArrayList<>();

//        if (cb_wax.isChecked()) {
//            otherService.add(cb_wax.getText().toString());
//        }
//        if (cb_engine_wash.isChecked()) {
//            otherService.add(cb_engine_wash.getText().toString());
//        }
//        if (cb_car_wash.isChecked()) {
//            otherService.add(cb_car_wash.getText().toString());
//        }
//        if (cb_full_car_wash.isChecked()) {
//            otherService.add(cb_full_car_wash.getText().toString());
//        }
//        if (cb_cleaning.isChecked()) {
//            otherService.add(cb_cleaning.getText().toString());
//        }
//        if (cb_interior.isChecked()) {
//            otherService.add(cb_interior.getText().toString());
//        }

        Intent intent = new Intent();
        intent.putExtra("otherservice", String.join(",", otherService));
        setResult(301, intent);
        finish();
    }

    @Override
    public void onStarted() {
        shimmer_item_attribute.startShimmer();
    }

    @Override
    public void onSuccess(LiveData<AttributeResponce> attributeResponce) {
        attributeResponce.observe(this, new Observer<AttributeResponce>() {
            @Override
            public void onChanged(AttributeResponce attributeResponce) {
                rvAttribute.setLayoutManager(new LinearLayoutManager(context));
                AttributeAdapter attributeAdapter = new AttributeAdapter(context, attributeResponce.getAttributes());
                rvAttribute.setAdapter(attributeAdapter);
                shimmer_item_attribute.stopShimmer();
                shimmer_item_attribute.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onFailure(String meessage) {
        shimmer_item_attribute.stopShimmer();
        shimmer_item_attribute.setVisibility(View.GONE);
    }
}