package com.cw.motorcar.shop.ui.home;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cw.motorcar.R;
import com.cw.motorcar.shop.adapter.AllBrandAdapter;
import com.cw.motorcar.shop.adapter.PopularBrandAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BrandFragment extends Fragment {

    private Context context;
    @BindView(R.id.rv_brand)
    RecyclerView rvBrand;
    @BindView(R.id.rv_allbrand)
    RecyclerView rvAllbrand;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_brand, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        context = getContext();
        ButterKnife.bind(this, view);
        setData();
    }

    private void setData() {
        rvBrand.setLayoutManager(new GridLayoutManager(context, 4));
        PopularBrandAdapter popularBrandAdapter = new PopularBrandAdapter(context);
        rvBrand.setAdapter(popularBrandAdapter);
        rvAllbrand.setLayoutManager(new GridLayoutManager(context, 2));
        AllBrandAdapter allBrandAdapter = new AllBrandAdapter(context);
        rvAllbrand.setAdapter(allBrandAdapter);
    }
}