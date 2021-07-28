package com.cw.motorcar.shop.ui.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cw.motorcar.R;
import com.cw.motorcar.driver.adapter.CarNameAdapter;
import com.cw.motorcar.shop.adapter.AllProductAdapter;
import com.cw.motorcar.shop.adapter.ShopCategoryAdapter;
import com.cw.motorcar.shop.ui.cart.MyCartActivity;
import com.whiteelephant.monthpicker.MonthPickerDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Products extends AppCompatActivity {

    private Context context;
    @BindView(R.id.rv_products)
    RecyclerView rvProducts;
    @BindView(R.id.tv_product_title)
    TextView tvTitle;
    @BindView(R.id.tv_year)
    TextView tvYear;
    @BindView(R.id.tv_model)
    TextView tvModel;
    @BindView(R.id.tv_brand)
    TextView tvBrand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        initView();
    }

    private void initView() {
        context = Products.this;
        ButterKnife.bind(this);
        setData();
    }

    private void setData() {
        rvProducts.setLayoutManager(new GridLayoutManager(context, 2));
        AllProductAdapter allProductAdapter = new AllProductAdapter(context);
        rvProducts.setAdapter(allProductAdapter);
    }

    @OnClick(R.id.iv_back)
    void back() {
        onBackPressed();
    }

    @OnClick(R.id.lin_view_cart)
    void goToMyCart() {
        Intent intent = new Intent(this, MyCartActivity.class);
        startActivity(intent);
//        overridePendingTransition(R.anim.slide_up,
//                R.anim.slide_down);
    }

    @OnClick(R.id.tv_product_title)
    void clickProductitle() {
        popupCategories();
    }

    @OnClick(R.id.tv_brand)
    void popupBrand() {
        popUpselectCar();
    }

    @OnClick(R.id.tv_model)
    void popupModel() {
        popUPselectModel();
    }

    @OnClick(R.id.tv_year)
    void popupYear() {
        showYearPicker();
    }

    private void popupCategories() {
        View view = LayoutInflater.from(context).inflate(R.layout.popup_categories, null);
        final Dialog mBottomSheetDialog = new Dialog(context);
        mBottomSheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.alpha(Color.WHITE)));
        mBottomSheetDialog.setContentView(view); // your custom view.
        RecyclerView rvCategory = mBottomSheetDialog.findViewById(R.id.rv_category);
        rvCategory.setLayoutManager(new LinearLayoutManager(context));
        rvCategory.setAdapter(new ShopCategoryAdapter(context, new ShopCategoryAdapter.OnCategorySelectListenre() {
            @Override
            public void onCatSelect(String cat) {
                mBottomSheetDialog.dismiss();
                tvTitle.setText(cat);
            }
        }));
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.setCanceledOnTouchOutside(true);
        mBottomSheetDialog.getWindow().setGravity(Gravity.TOP | Gravity.LEFT);
        mBottomSheetDialog.show();
    }

    private void showYearPicker() {
        new MonthPickerDialog.Builder(context, new
                MonthPickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(int selectedMonth, int selectedYear) {
                        tvYear.setText(selectedYear + "");
                    }
                }, 2021, 0)
                .setMinYear(1900)
                .setMaxYear(2021)
                .showYearOnly()
                .build()
                .show();
    }


    private void popUpselectCar() {

        String[] carNames = {"Audi", "BMW", "Aston Martin", "BMX", "Mini", "BYD", "Flat", "Alfa Romeo", "Chryster", "Dodge", "Ferrari", "Ford"};
        final Dialog mBottomSheetDialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.popup_your_car, null);
        mBottomSheetDialog.setContentView(view); // your custom view.
        AppCompatImageView ivCross = mBottomSheetDialog.findViewById(R.id.iv_cross);
        RecyclerView rvCarList = mBottomSheetDialog.findViewById(R.id.rv_car_list);
        rvCarList.setLayoutManager(new LinearLayoutManager(context));
        CarNameAdapter carNameAdapter = new CarNameAdapter(context, null, new CarNameAdapter.SelectItemListener() {
            @Override
            public void onSelect(String name,int id) {
                mBottomSheetDialog.dismiss();
                tvBrand.setText(name);
            }
        });
        rvCarList.setAdapter(carNameAdapter);

        ivCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomSheetDialog.dismiss();
            }
        });

        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.setCanceledOnTouchOutside(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mBottomSheetDialog.show();
    }

    private void popUPselectModel() {
        String[] carNames = {"A3 Cabrioet Quattro", "A3 Quattro", "A4 Allroad", "A6 Allroad", "A7", "A8", "A8L", "Q3", "Q5", "Q8", "Q7", "RS08", "S4 Sedan"};
        final Dialog mBottomSheetDialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.popup_your_model, null);
        mBottomSheetDialog.setContentView(view); // your custom view.
        AppCompatImageView ivCross = mBottomSheetDialog.findViewById(R.id.iv_cross);
        RecyclerView rvCarList = mBottomSheetDialog.findViewById(R.id.rv_car_list);
        rvCarList.setLayoutManager(new LinearLayoutManager(context));
        CarNameAdapter carNameAdapter = new CarNameAdapter(context, null, new CarNameAdapter.SelectItemListener() {
            @Override
            public void onSelect(String name,int id) {
                mBottomSheetDialog.dismiss();
                tvModel.setText(name);
            }
        });
        rvCarList.setAdapter(carNameAdapter);

        ivCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomSheetDialog.dismiss();
            }
        });

        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.setCanceledOnTouchOutside(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mBottomSheetDialog.show();
    }

}