package com.cw.motorcar.driver.ui.activity.car;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cw.motorcar.R;
import com.cw.motorcar.custom.ProgressDialog;
import com.cw.motorcar.custom.UiUtils;
import com.cw.motorcar.data.network.ApiClient;
import com.cw.motorcar.data.network.MyApi;
import com.cw.motorcar.driver.adapter.CategoryAdapter;
import com.cw.motorcar.driver.model.Carlist;
import com.cw.motorcar.driver.model.Category;
import com.cw.motorcar.driver.model.CategoryResponce;
import com.cw.motorcar.storage.SharedPrefManager;
import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CategoryActivity extends AppCompatActivity {

    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;
    @BindView(R.id.rv_category)
    RecyclerView rvCategory;
    @BindView(R.id.shimmer_view_container)
    ShimmerFrameLayout shimmer_view_container;
    private Context context;
    private ProgressDialog progressDialog;
    private String catId = "";
    private String catName = "";
    private Carlist carlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        initview();
    }

    @Override
    protected void onResume() {
        super.onResume();
        shimmer_view_container.startShimmer();
    }

    private void initview() {
        context = CategoryActivity.this;
        progressDialog = new ProgressDialog((Activity) context);
        ButterKnife.bind(this);
        tvTitle.setText(getString(R.string.categories));
        Intent intent = getIntent();
        if (intent.hasExtra("cat_id")) {
            catId = intent.getStringExtra("cat_id");
            catName = intent.getStringExtra("cat_name");
            carlist = (Carlist) intent.getSerializableExtra("mycar");
        }
        getSubCategoryData();
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
                    setData(response.body().getData());
                } else {
                    UiUtils.showMessage(context, rvCategory, response.body().getMessage());
                    startActivity(new Intent(context, NewRequestActivity.class)
                            .putExtra("cat_id", catId)
                            .putExtra("cat_name", catName)
                            .putExtra("sub_cat_id", "")
                            .putExtra("sub_cat_name", "")
                            .putExtra("mycar", carlist));
                }
                shimmer_view_container.stopShimmer();
                shimmer_view_container.setVisibility(View.GONE);
                finish();
            }

            @Override
            public void onFailure(Call<CategoryResponce> call, Throwable t) {
//                progressDialog.dismiss();
                shimmer_view_container.stopShimmer();
                shimmer_view_container.setVisibility(View.GONE);
                UiUtils.showMessage(context, rvCategory, t.getMessage());
            }
        });
    }

    private void setData(ArrayList<Category> data) {
        rvCategory.setLayoutManager(new LinearLayoutManager(context));
        CategoryAdapter categoryAdapter = new CategoryAdapter(context, data, carlist, catName);
        rvCategory.setAdapter(categoryAdapter);
    }

    @OnClick(R.id.iv_back)
    void back() {
        onBackPressed();
    }
}