package com.cw.motorcar.merchant.ui.home.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cw.motorcar.R;
import com.cw.motorcar.merchant.ui.home.HomeMerchantActivity;
import com.cw.motorcar.merchant.ui.home.MerchentRequestAdapter;
import com.cw.motorcar.merchant.ui.merchentrequest.MerchentRequestFragment;
import com.cw.motorcar.merchant.ui.offer.MerchantOfferActivity;
import com.cw.motorcar.merchant.ui.packages.PackagesActivity;
import com.cw.motorcar.driver.ui.activity.notification.NotificationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeMerchantFragment extends Fragment {

    private Context context;
    @BindView(R.id.rv_request)
    RecyclerView rvRequest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_merchant, container, false);
        initview(view);
        return view;
    }

    private void initview(View view) {
        context = getContext();
        ButterKnife.bind(this, view);
        setData();
    }

    private void setData() {
        rvRequest.setLayoutManager(new LinearLayoutManager(context));
        MerchentRequestAdapter merchentRequestAdapter = new MerchentRequestAdapter(context);
        rvRequest.setAdapter(merchentRequestAdapter);
    }

    @OnClick(R.id.iv_menu)
    void openMenu() {
        HomeMerchantActivity homeMerchantActivity = new HomeMerchantActivity();
        homeMerchantActivity.openCloseDrawer();
    }

    @OnClick(R.id.iv_notification)
    void notification() {
        startActivity(new Intent(context, NotificationActivity.class));
    }

    @OnClick({R.id.mcv_package, R.id.mcv_working, R.id.mcv_complete, R.id.mcv_offer, R.id.mcv_new_requet})
    void goToPage(View view) {
        switch (view.getId()) {
            case R.id.mcv_package:
                startActivity(new Intent(context, PackagesActivity.class));
                break;
            case R.id.mcv_working:
                Fragment fragment = new MerchentRequestFragment();
                Bundle bundle = new Bundle();
                bundle.putString("action", "Inprogress");
                fragment.setArguments(bundle);
                loadFragment(fragment);
                break;
            case R.id.mcv_new_requet:
                Fragment fragment3 = new MerchentRequestFragment();
                Bundle bundle3 = new Bundle();
                bundle3.putString("action", "New");
                fragment3.setArguments(bundle3);
                loadFragment(fragment3);
                break;
            case R.id.mcv_complete:
                Fragment fragment2 = new MerchentRequestFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putString("action", "Complete");
                fragment2.setArguments(bundle2);
                loadFragment(fragment2);
                break;
            case R.id.mcv_offer:
                startActivity(new Intent(context, MerchantOfferActivity.class));
                break;
        }
    }

    public void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_container, fragment).addToBackStack("tag");
        transaction.commit();
    }
}