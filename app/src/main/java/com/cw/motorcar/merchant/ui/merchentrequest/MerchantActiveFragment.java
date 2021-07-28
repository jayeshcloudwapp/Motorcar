package com.cw.motorcar.merchant.ui.merchentrequest;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.cw.motorcar.R;
import com.cw.motorcar.merchant.adapter.ActiveMerchantAdapter;
import com.cw.motorcar.merchant.adapter.MerchantCategoryAdapter;
import com.cw.motorcar.shop.adapter.ShopCategoryAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MerchantActiveFragment extends Fragment {

    private Context context;
    @BindView(R.id.rv_active)
    RecyclerView rvActive;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_category)
    TextView tvCategory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_merchant_active, container, false);
        initview(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setData();
    }

    private void initview(View view) {
        context = getContext();
        ButterKnife.bind(this, view);
    }

    private void setData() {
        rvActive.setLayoutManager(new LinearLayoutManager(context));
        ActiveMerchantAdapter activeMerchantAdapter = new ActiveMerchantAdapter(context);
        rvActive.setAdapter(activeMerchantAdapter);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @OnClick(R.id.tv_date)
    void selectDate() {
        setDate();
    }

    @OnClick(R.id.tv_category)
    void selectCategory() {
        selectCategorypopup();
    }

    private void selectCategorypopup() {
        View view = LayoutInflater.from(context).inflate(R.layout.popup_categories, null);
        final Dialog mBottomSheetDialog = new Dialog(context);
        mBottomSheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.alpha(Color.WHITE)));
        mBottomSheetDialog.setContentView(view); // your custom view.
        RecyclerView rvCategory = mBottomSheetDialog.findViewById(R.id.rv_category);
        rvCategory.setLayoutManager(new LinearLayoutManager(context));
        rvCategory.setAdapter(new MerchantCategoryAdapter(context, new MerchantCategoryAdapter.OnCarCategorySelectListenre() {
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setDate() {

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        tvDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }
}