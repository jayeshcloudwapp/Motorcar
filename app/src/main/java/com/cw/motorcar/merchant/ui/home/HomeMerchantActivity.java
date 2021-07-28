package com.cw.motorcar.merchant.ui.home;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.cw.motorcar.R;
import com.cw.motorcar.driver.ui.activity.auth.LoginActivity;
import com.cw.motorcar.merchant.ui.Activity.CustomerIntrestActivity;
import com.cw.motorcar.merchant.ui.home.Fragment.HomeMerchantFragment;
import com.cw.motorcar.merchant.ui.merchentrequest.MerchentRequestFragment;
import com.cw.motorcar.merchant.ui.offer.MerchantOfferActivity;
import com.cw.motorcar.merchant.ui.profile.ProfileMerchantFragment;
import com.cw.motorcar.driver.ui.activity.auth.ChangePasswordActivity;
import com.cw.motorcar.driver.ui.activity.contactadmin.ContactAdminActivity;
import com.cw.motorcar.driver.ui.fragment.chat.ChatUserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeMerchantActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.nav_merchant_bottom_tab)
    BottomNavigationView nav_merchant_bottom_tab;

    @BindView(R.id.merchant_navigation_view)
    NavigationView merchant_navigation_view;

    public static DrawerLayout drawer_merchant;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_merchant);
        initView();
    }

    private void initView() {
        context = HomeMerchantActivity.this;
        ButterKnife.bind(this);
        // navigation_view.setItemIconTintList(R.color.drawer_item);
        drawer_merchant = findViewById(R.id.drawer_merchant);
        merchant_navigation_view.setNavigationItemSelectedListener(this);
        nav_merchant_bottom_tab.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        nav_merchant_bottom_tab.setItemBackgroundResource(R.color.color_tab);

        Intent intent = getIntent();
        if (intent.hasExtra("pagename")) {
            //loadFragment(new RequestItemFragment());
            nav_merchant_bottom_tab.setSelectedItemId(R.id.bottom_request);
        } else {
            //loadFragment(new HomeFragment());
            nav_merchant_bottom_tab.setSelectedItemId(R.id.bottom_home);
        }
    }

    public void openCloseDrawer() {
        if (drawer_merchant.isDrawerOpen(GravityCompat.START)) {
            drawer_merchant.closeDrawer(GravityCompat.START);
        } else {
            drawer_merchant.openDrawer(GravityCompat.START);
        }
    }

    public void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_container, fragment).addToBackStack("tag");
        transaction.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.bottom_home:
                    loadFragment(new HomeMerchantFragment());
                    break;
                case R.id.bottom_request:
                    loadFragment(new MerchentRequestFragment());
                    break;
                case R.id.bottom_profile:
                    loadFragment(new ProfileMerchantFragment());
                    break;
                case R.id.bottom_chat:
                    loadFragment(new ChatUserFragment());
                    break;
            }
            return true;
        }
    };

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        openCloseDrawer();
        switch (item.getItemId()) {
            case R.id.menu_home:
                loadFragment(new HomeMerchantFragment());
                break;
            case R.id.menu_offer:
                startActivity(new Intent(context, MerchantOfferActivity.class));
                break;
            case R.id.menu_contact_admin:
                startActivity(new Intent(context, ContactAdminActivity.class));
                break;
            case R.id.menu_change_pass:
                startActivity(new Intent(context, ChangePasswordActivity.class));
                break;
            case R.id.menu_cust_inst:
                startActivity(new Intent(context, CustomerIntrestActivity.class));
                break;
            case R.id.menu_logout:
                logout();
                break;
        }

        return true;
    }

    private void logout() {
        final Dialog mBottomSheetDialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.popup_custom, null);
        mBottomSheetDialog.setContentView(view); // your custom view.

        MaterialButton no_btn = mBottomSheetDialog.findViewById(R.id.no_btn);
        MaterialButton yes_button = mBottomSheetDialog.findViewById(R.id.yes_button);
        TextView tvTitle = mBottomSheetDialog.findViewById(R.id.tv_title);
        TextView tvMsg = mBottomSheetDialog.findViewById(R.id.tv_msg);

        tvTitle.setText(R.string.logout);
        tvMsg.setText(R.string.do_you_want_to_logout);
        no_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomSheetDialog.dismiss();
            }
        });

        yes_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomSheetDialog.dismiss();
                startActivity(new Intent(context, LoginActivity.class));
            }
        });
        mBottomSheetDialog.setCancelable(false);
        mBottomSheetDialog.setCanceledOnTouchOutside(false);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.show();
    }
}