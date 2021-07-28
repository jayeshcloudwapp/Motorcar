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
import com.cw.motorcar.driver.adapter.CompleteAdapter;
import com.cw.motorcar.driver.model.MyRequestResponce;
import com.cw.motorcar.storage.SharedPrefManager;
import com.facebook.shimmer.ShimmerFrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CompleteFragment extends Fragment implements RequestListener {
    private Context context;
    @BindView(R.id.rv_rq_complete_list)
    RecyclerView rvRqCompleteList;
    @BindView(R.id.shimmer_view_container)
    ShimmerFrameLayout shimmer_view_container;
    private MyRequestViewModel myRequestViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_complete, container, false);
        myRequestViewModel = ViewModelProviders.of(CompleteFragment.this).get(MyRequestViewModel.class);
        myRequestViewModel.requestListener = this;
        initView(view);
        return view;
    }

    private void initView(View view) {
        context = getContext();
        ButterKnife.bind(this, view);
        myRequestViewModel.token = SharedPrefManager.getInstance(context).getUser().getToken();
        myRequestViewModel.action = "3";
    }


    @Override
    public void onStarted() {
        shimmer_view_container.startShimmer();
    }

    @Override
    public void onResume() {
        super.onResume();
        myRequestViewModel.getMyRequest();
    }

    @Override
    public void onSuccess(LiveData<MyRequestResponce> requestResponce) {
        requestResponce.observe(this, new Observer<MyRequestResponce>() {
            @Override
            public void onChanged(MyRequestResponce myRequestResponce) {
                shimmer_view_container.stopShimmer();
                shimmer_view_container.setVisibility(View.GONE);
                rvRqCompleteList.setLayoutManager(new LinearLayoutManager(context));
                CompleteAdapter completeAdapter = new CompleteAdapter(context, myRequestResponce.getMyRequests());
                rvRqCompleteList.setAdapter(completeAdapter);
            }
        });

    }

    @Override
    public void onFailure(String meessage) {
        shimmer_view_container.stopShimmer();
        shimmer_view_container.setVisibility(View.GONE);
    }
}