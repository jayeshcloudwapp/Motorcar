package com.cw.motorcar.driver.ui.fragment.request;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cw.motorcar.R;
import com.cw.motorcar.driver.adapter.RequestAdapter;
import com.cw.motorcar.driver.model.MyRequestResponce;
import com.cw.motorcar.storage.SharedPrefManager;
import com.facebook.shimmer.ShimmerFrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RequestItemFragment extends Fragment implements RequestListener {

    @BindView(R.id.rv_request_list)
    RecyclerView rvRequestList;
    private Context context;
    @BindView(R.id.shimmer_view_container)
    ShimmerFrameLayout shimmer_view_container;
    MyRequestViewModel myRequestViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_request_item, container, false);
        myRequestViewModel = ViewModelProviders.of(RequestItemFragment.this).get(MyRequestViewModel.class);
        myRequestViewModel.requestListener = this;
        initView(view);
        return view;
    }

    private void initView(View view) {
        context = getContext();
        ButterKnife.bind(this, view);
        myRequestViewModel.token = SharedPrefManager.getInstance(context).getUser().getToken();
        myRequestViewModel.action = "1";
        //setData();
    }

    @Override
    public void onResume() {
        super.onResume();
        myRequestViewModel.getMyRequest();
    }

    @Override
    public void onStarted() {
        shimmer_view_container.startShimmer();
    }

    @Override
    public void onSuccess(LiveData<MyRequestResponce> myRequestResponce) {

        myRequestResponce.observe(this, new Observer<MyRequestResponce>() {
            @Override
            public void onChanged(MyRequestResponce myRequestResponce) {
                shimmer_view_container.stopShimmer();
                shimmer_view_container.setVisibility(View.GONE);
                rvRequestList.setLayoutManager(new LinearLayoutManager(context));
                RequestAdapter requestAdapter = new RequestAdapter(context, myRequestResponce.getMyRequests());
                rvRequestList.setAdapter(requestAdapter);
            }
        });
    }

    @Override
    public void onFailure(String meessage) {
        shimmer_view_container.stopShimmer();
    }
}