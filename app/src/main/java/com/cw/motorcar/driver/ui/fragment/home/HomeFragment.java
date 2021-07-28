package com.cw.motorcar.driver.ui.fragment.home;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cw.motorcar.R;
import com.cw.motorcar.custom.ProgressDialog;
import com.cw.motorcar.custom.UiUtils;
import com.cw.motorcar.data.network.ApiClient;
import com.cw.motorcar.data.network.MyApi;
import com.cw.motorcar.driver.adapter.CategoryHomeAdapter;
import com.cw.motorcar.driver.model.Category;
import com.cw.motorcar.driver.model.CategoryResponce;
import com.cw.motorcar.driver.ui.activity.OfferActivity;
import com.cw.motorcar.driver.ui.activity.auth.ChangePasswordActivity;
import com.cw.motorcar.driver.ui.activity.auth.LoginActivity;
import com.cw.motorcar.driver.ui.activity.auth.model.UserData;
import com.cw.motorcar.driver.ui.activity.contactadmin.ContactAdminActivity;
import com.cw.motorcar.driver.ui.activity.notification.NotificationActivity;
import com.cw.motorcar.driver.ui.fragment.profile.ProfileFragment;
import com.cw.motorcar.shop.ui.order.MyOrderActivity;
import com.cw.motorcar.storage.SharedPrefManager;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class HomeFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {

    private Context context;
    @BindView(R.id.image_slider)
    ImageSlider imageSlider;
    ArrayList<SlideModel> imageList = new ArrayList<>(); // Create image list
    @BindView(R.id.navigation_view)
    NavigationView navigation_view;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.drawer)
    DrawerLayout drawer;
    @BindView(R.id.rv_home_category)
    RecyclerView rv_home_category;
    @BindView(R.id.shimmer_view_container)
    ShimmerFrameLayout shimmer_view_container;
    UserData userData;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
//        FragmentHomeBinding binding = DataBindingUtil.setContentView(getActivity(), R.layout.fragment_home);
//        HomeViewHolder homeViewHolder = ViewModelProviders.of(HomeFragment.this).get(HomeViewHolder.class);
//        binding.setHomeviewholder(homeViewHolder);
        initView(view);
        return view;
    }

    private void initView(View view) {
        context = getContext();
        progressDialog = new ProgressDialog((Activity) context);
        userData = SharedPrefManager.getInstance(context).getUser();
        ButterKnife.bind(this, view);
        navigation_view.setNavigationItemSelectedListener(this);
        View headerLayout = navigation_view.getHeaderView(0); // 0-index header
        CircleImageView circleImageView = headerLayout.findViewById(R.id.imageView);
        TextView textusername = headerLayout.findViewById(R.id.textusername);
        Glide.with(context).load(ApiClient.SITE_URL + userData.getImage()).into(circleImageView);
        tvUsername.setText(userData.getName());
        textusername.setText(userData.getName());
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCloseDrawer();
                startActivity(new Intent(context, ProfileFragment.class));
            }
        });
        setSlider();
        getCategoryData();
    }

    @Override
    public void onResume() {
        super.onResume();
        shimmer_view_container.startShimmer();
    }

    private void getCategoryData() {
        //progressDialog.show();
        Retrofit retrofit = ApiClient.getClient("");
        MyApi service = retrofit.create(MyApi.class);
        String token = SharedPrefManager.getInstance(context).getUser().getToken();
        Call<CategoryResponce> call = service.getCategory(token);
        call.enqueue(new Callback<CategoryResponce>() {
            @Override
            public void onResponse(Call<CategoryResponce> call, Response<CategoryResponce> response) {
                //   progressDialog.dismiss();
                if (response.body().getStatus()) {
                    setData(response.body().getData());
                } else {
                    UiUtils.showMessage(context, rv_home_category, response.body().getMessage());
                }
                // Stopping Shimmer Effect's animation after data is loaded to ListView
                shimmer_view_container.stopShimmer();
                shimmer_view_container.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<CategoryResponce> call, Throwable t) {
                //  progressDialog.dismiss();
                shimmer_view_container.stopShimmer();
                shimmer_view_container.setVisibility(View.GONE);
                UiUtils.showMessage(context, rv_home_category, t.getMessage());
            }
        });
    }

    private void setData(ArrayList<Category> data) {
        rv_home_category.setLayoutManager(new GridLayoutManager(context, 3));
        CategoryHomeAdapter categoryHomeAdapter = new CategoryHomeAdapter(context, data);
        rv_home_category.setAdapter(categoryHomeAdapter);
    }


    private void setSlider() {
        imageList.add(new SlideModel("https://bit.ly/2YoJ77H", ScaleTypes.FIT));
        imageList.add(new SlideModel("https://bit.ly/2BteuF2", ScaleTypes.FIT));
        imageList.add(new SlideModel("https://bit.ly/3fLJf72", ScaleTypes.FIT));
        imageSlider.setImageList(imageList);
    }

    @OnClick(R.id.iv_menu)
    void menuopen() {
        openCloseDrawer();
    }

    @OnClick(R.id.iv_notification)
    void goToNotification() {
        startActivity(new Intent(context, NotificationActivity.class));
    }

    public void openCloseDrawer() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.openDrawer(GravityCompat.START);
        }
    }

//    @OnClick(R.id.lin_car)
//    void goToCarList() {
//        startActivity(new Intent(context, CarListActivity.class));
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        drawer.closeDrawer(Gravity.LEFT, true);
        switch (item.getItemId()) {
            case R.id.menu_home:

                break;
            case R.id.menu_my_order:
                startActivity(new Intent(context, MyOrderActivity.class));
                break;
            case R.id.menu_change_pass:
                startActivity(new Intent(context, ChangePasswordActivity.class));
                break;
            case R.id.menu_offer:
                startActivity(new Intent(context, OfferActivity.class));
                break;
            case R.id.menu_contact_admin:
                startActivity(new Intent(context, ContactAdminActivity.class));
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
                SharedPrefManager.getInstance(context).logout();
                startActivity(new Intent(context, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
        mBottomSheetDialog.setCancelable(false);
        mBottomSheetDialog.setCanceledOnTouchOutside(false);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.show();
    }
}