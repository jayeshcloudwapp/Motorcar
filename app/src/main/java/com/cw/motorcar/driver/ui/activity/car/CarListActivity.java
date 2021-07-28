package com.cw.motorcar.driver.ui.activity.car;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cw.motorcar.R;
import com.cw.motorcar.custom.ProgressDialog;
import com.cw.motorcar.custom.UiUtils;
import com.cw.motorcar.data.network.ApiClient;
import com.cw.motorcar.data.network.MyApi;
import com.cw.motorcar.databinding.ActivityCarListBinding;
import com.cw.motorcar.driver.adapter.CarListAdapter;
import com.cw.motorcar.driver.model.Carlist;
import com.cw.motorcar.driver.model.CategoryResponce;
import com.cw.motorcar.driver.model.MyCarResponce;
import com.cw.motorcar.storage.SharedPrefManager;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CarListActivity extends AppCompatActivity implements LifecycleOwner, MyCarListener {

    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.shimmer_view_container)
    ShimmerFrameLayout shimmer_view_container;
    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;
    private Context context;
    private String catId = "";
    private String catName = "";
    ActivityCarListBinding binding;
    MyCarViewModel myCarViewModel;
    private ProgressDialog progressDialog;
    CarListAdapter carListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_car_list);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_car_list);
        myCarViewModel = ViewModelProviders.of(this).get(MyCarViewModel.class);
        binding.setMycarViewModel(myCarViewModel);
        myCarViewModel.authListener = this;
        initview();
    }


    private void initview() {
        context = CarListActivity.this;
        ButterKnife.bind(this);
        progressDialog = new ProgressDialog((Activity) context);
        tvTitle.setText(getString(R.string.my_car));
        Intent intent = getIntent();
        myCarViewModel.token = SharedPrefManager.getInstance(context).getUser().getToken();
        if (intent.hasExtra("cat_id")) {
            catId = String.valueOf(intent.getIntExtra("cat_id", 0));
            catName = String.valueOf(intent.getStringExtra("cat_name"));
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        myCarViewModel.getUserCarMutableLiveData();
        getSubCategoryData();
    }


    @OnTextChanged(R.id.et_search)
    void onNameChanged(CharSequence s) {
        if (carListAdapter != null) {
            carListAdapter.getFilter().filter(s);
        }
    }

    @OnClick(R.id.lin_add_car)
    void gotoAddCar() {
        startActivity(new Intent(context, AddCarActivity.class));
    }

    @OnClick(R.id.iv_back)
    void back() {
        onBackPressed();
    }

    @Override
    public void onStarted() {
        shimmer_view_container.startShimmer();
    }

    @Override
    public void onSuccess(LiveData<MyCarResponce> carResponce) {
        carResponce.observe(this, new Observer<MyCarResponce>() {
            @Override
            public void onChanged(MyCarResponce myCarResponce) {
                shimmer_view_container.stopShimmer();
                shimmer_view_container.setVisibility(View.GONE);
                try {
                    if (myCarResponce.getStatus()) {
                        rvList.setLayoutManager(new LinearLayoutManager(context));
                        carListAdapter = new CarListAdapter(context, catId, myCarResponce.getData(),catName);
                        rvList.setAdapter(carListAdapter);
                    }
                } catch (Exception e) {
                    UiUtils.showMessage(context, rvList, e.getMessage());
                }

            }
        });
    }

    @Override
    public void onFailure(String meessage) {
        UiUtils.showMessage(context, rvList, meessage);
        shimmer_view_container.stopShimmer();
    }

    private void getSubCategoryData() {
//        progressDialog.show();
        Retrofit retrofit = ApiClient.getClient("");
        MyApi service = retrofit.create(MyApi.class);
        String token = SharedPrefManager.getInstance(context).getUser().getToken();
        Call<CategoryResponce> call = service.getSubCategory(token, catId);
        call.enqueue(new Callback<CategoryResponce>() {
            @Override
            public void onResponse(Call<CategoryResponce> call, Response<CategoryResponce> response) {
//                progressDialog.dismiss();

                if (response.body().getStatus()) {

                }

            }

            @Override
            public void onFailure(Call<CategoryResponce> call, Throwable t) {
//                progressDialog.dismiss();

            }
        });
    }
}