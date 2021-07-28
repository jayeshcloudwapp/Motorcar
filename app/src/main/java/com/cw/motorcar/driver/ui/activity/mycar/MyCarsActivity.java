package com.cw.motorcar.driver.ui.activity.mycar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cw.motorcar.R;
import com.cw.motorcar.custom.UiUtils;
import com.cw.motorcar.databinding.ActivityCarListBinding;
import com.cw.motorcar.databinding.ActivityMyCarsBinding;
import com.cw.motorcar.driver.adapter.CarListAdapter;
import com.cw.motorcar.driver.adapter.MyCarListAdapter;
import com.cw.motorcar.driver.model.Carlist;
import com.cw.motorcar.driver.model.MyCarResponce;
import com.cw.motorcar.driver.ui.activity.car.AddCarActivity;
import com.cw.motorcar.driver.ui.activity.car.MyCarListener;
import com.cw.motorcar.driver.ui.activity.car.MyCarViewModel;
import com.cw.motorcar.storage.SharedPrefManager;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.button.MaterialButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MyCarsActivity extends AppCompatActivity implements MyCarListener {

    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;
    private Context context;
    private MyCarViewModel myCarViewModel;
    @BindView(R.id.shimmer_view_container)
    ShimmerFrameLayout shimmer_view_container;
    MyCarListAdapter myCarListAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMyCarsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_my_cars);
        myCarViewModel = ViewModelProviders.of(this).get(MyCarViewModel.class);
        binding.setMycarViewModel(myCarViewModel);
        myCarViewModel.authListener = this;
        initview();
    }

    private void initview() {
        context = MyCarsActivity.this;
        ButterKnife.bind(this);
        tvTitle.setText(getString(R.string.my_car));
        myCarViewModel.token = SharedPrefManager.getInstance(context).getUser().getToken();
    }

    @Override
    protected void onResume() {
        super.onResume();
        myCarViewModel.getUserCarMutableLiveData();
    }


    @OnTextChanged(R.id.et_search)
    void onNameChanged(CharSequence s) {
        if (myCarListAdapter != null) {
            myCarListAdapter.getFilter().filter(s);
        }
    }

    @OnClick(R.id.iv_back)
    void back() {
        onBackPressed();
    }

    @OnClick(R.id.tv_add_car)
    void addCar() {
        startActivity(new Intent(context, AddCarActivity.class));
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
                        myCarListAdapter = new MyCarListAdapter(context, myCarResponce.getData(), new MyCarListAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(Carlist item, View v) {
                                switch (v.getId()) {
                                    case R.id.iv_edit:
                                        context.startActivity(new Intent(context, AddCarActivity.class)
                                                .putExtra("action", "Edit")
                                                .putExtra("mycar", item)
                                        );
                                        break;
                                    case R.id.iv_delete:
                                        deletePopup();
                                        break;
                                }
                            }
                        });
                        rvList.setAdapter(myCarListAdapter);
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

    private void deletePopup() {
        final Dialog mBottomSheetDialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.popup_custom, null);
        mBottomSheetDialog.setContentView(view); // your custom view.
        MaterialButton no_btn = mBottomSheetDialog.findViewById(R.id.no_btn);
        MaterialButton yes_button = mBottomSheetDialog.findViewById(R.id.yes_button);
        TextView tvTitle = mBottomSheetDialog.findViewById(R.id.tv_title);
        TextView tvMsg = mBottomSheetDialog.findViewById(R.id.tv_msg);
        tvTitle.setText(R.string.delete);
        tvMsg.setText(R.string.do_you_want_to_delete);
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
            }
        });

        mBottomSheetDialog.setCancelable(false);
        mBottomSheetDialog.setCanceledOnTouchOutside(false);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.show();
    }
}