package com.cw.motorcar.merchant.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cw.motorcar.R;
import com.cw.motorcar.merchant.ui.home.HomeMerchantActivity;
import com.cw.motorcar.merchant.ui.services.AddNewServices;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileMerchantFragment extends Fragment {

    private Context context;
    @BindView(R.id.tv_merchant_toolbat_title)
    TextView tvTitle;
    @BindView(R.id.tv_profile)
    TextView tvProfile;
    @BindView(R.id.tv_service)
    TextView tvService;
    @BindView(R.id.lin_profile)
    LinearLayout linprofile;
    @BindView(R.id.lin_services)
    LinearLayout linServices;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_merchant, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        context = getContext();
        ButterKnife.bind(this, view);
        tvTitle.setText(R.string.profile);
    }

    @OnClick(R.id.iv_menu)
    void openMenu() {
        HomeMerchantActivity homeMerchantActivity = new HomeMerchantActivity();
        homeMerchantActivity.openCloseDrawer();
    }

    @OnClick(R.id.tv_btn_add_service)
    void addNewService() {
        startActivity(new Intent(context, AddNewServices.class));
    }

    @OnClick(R.id.tv_profile)
    void isProfile() {
        linprofile.setVisibility(View.VISIBLE);
        linServices.setVisibility(View.GONE);
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            tvProfile.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.border_bottom_selected));
            tvService.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.border_bottom));
        } else {
            tvProfile.setBackground(ContextCompat.getDrawable(context, R.drawable.border_bottom_selected));
            tvService.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.border_bottom));
        }
        tvProfile.setTextColor(getResources().getColor(R.color.black));
        tvService.setTextColor(getResources().getColor(R.color.text2));

    }

    @OnClick(R.id.tv_service)
    void isService() {
        linprofile.setVisibility(View.GONE);
        linServices.setVisibility(View.VISIBLE);
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            tvProfile.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.border_bottom));
            tvService.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.border_bottom_selected));
        } else {
            tvProfile.setBackground(ContextCompat.getDrawable(context, R.drawable.border_bottom));
            tvService.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.border_bottom_selected));
        }
        tvProfile.setTextColor(getResources().getColor(R.color.text2));
        tvService.setTextColor(getResources().getColor(R.color.black));
    }

}