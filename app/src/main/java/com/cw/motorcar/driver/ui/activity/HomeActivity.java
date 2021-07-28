package com.cw.motorcar.driver.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.cw.motorcar.R;
import com.cw.motorcar.driver.ui.activity.mycar.MyCarsActivity;
import com.cw.motorcar.driver.ui.fragment.home.HomeFragment;
import com.cw.motorcar.driver.ui.fragment.chat.ChatUserFragment;
import com.cw.motorcar.driver.ui.fragment.request.MyRequestFragment;
import com.cw.motorcar.shop.ui.home.ShopHomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {


    @BindView(R.id.nv_bottom_tab)
    BottomNavigationView navigation;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {
        context = HomeActivity.this;
        ButterKnife.bind(this);
        // navigation_view.setItemIconTintList(R.color.drawer_item);
        navigation.setItemBackgroundResource(R.color.color_tab);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Intent intent = getIntent();
        if (intent.hasExtra("pagename")) {
            //loadFragment(new RequestItemFragment());
            navigation.setSelectedItemId(R.id.bottom_request);
        } else if (intent.hasExtra("shop")) {
            navigation.setSelectedItemId(R.id.bottom_shop);
        } else {
            {
                navigation.setSelectedItemId(R.id.bottom_home);
            }
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
                    loadFragment(new HomeFragment());
                    break;
                case R.id.bottom_add_car:
                    startActivity(new Intent(context, MyCarsActivity.class));
                    break;
                case R.id.bottom_request:
                    loadFragment(new MyRequestFragment());
                    break;
                case R.id.bottom_chat:
                    loadFragment(new ChatUserFragment());
                    break;
                case R.id.bottom_shop:
                    loadFragment(new ShopHomeFragment());
                    break;
            }
            return true;
        }
    };

}