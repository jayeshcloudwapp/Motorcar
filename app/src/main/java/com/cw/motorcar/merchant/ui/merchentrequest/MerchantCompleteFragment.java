package com.cw.motorcar.merchant.ui.merchentrequest;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cw.motorcar.R;
import com.cw.motorcar.merchant.adapter.MerCompletedapter;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MerchantCompleteFragment extends Fragment {


    private Context context;
    @BindView(R.id.rv_mer_complete)
    RecyclerView rvMerComplete;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_merchant_complete, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        context = getContext();
        ButterKnife.bind(this, view);
    }

    @Override
    public void onResume() {
        super.onResume();
        setData();
    }

    private void setData() {
        rvMerComplete.setLayoutManager(new LinearLayoutManager(context));
        MerCompletedapter merCompletedapter = new MerCompletedapter(context);
        rvMerComplete.setAdapter(merCompletedapter);
    }
}