package com.cw.motorcar.shop.ui.home;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cw.motorcar.R;
import com.cw.motorcar.shop.adapter.ProductAdapter;
import com.cw.motorcar.shop.adapter.ShopCategoryAdapter;
import com.cw.motorcar.shop.ui.product.ProductDetailsActivity;
import com.cw.motorcar.shop.ui.product.Products;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryFragment extends Fragment {

    private Context context;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.rv_list1)
    RecyclerView rvList1;
    @BindView(R.id.rv_list2)
    RecyclerView rvList2;
    @BindView(R.id.tv_category)
    TextView tvCategory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        context = getContext();
        ButterKnife.bind(this, view);
        setData();
    }

    @OnClick(R.id.rel_det_first)
    void gotoProductDetails() {
        startActivity(new Intent(context, Products.class));
    }

    private void setData() {
        rvList.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        rvList1.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        rvList2.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        ProductAdapter productAdapter = new ProductAdapter(context);
        rvList.setAdapter(productAdapter);
        rvList1.setAdapter(productAdapter);
        rvList2.setAdapter(productAdapter);
    }

    @OnClick(R.id.mcv_shop_category)
    void shopCategory() {
        popupCategories();
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
                tvCategory.setText(cat);
            }
        }));
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.setCanceledOnTouchOutside(true);
        mBottomSheetDialog.getWindow().setGravity(Gravity.TOP | Gravity.LEFT);
        mBottomSheetDialog.show();
    }
}