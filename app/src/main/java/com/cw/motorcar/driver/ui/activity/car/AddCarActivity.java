package com.cw.motorcar.driver.ui.activity.car;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cw.motorcar.R;
import com.cw.motorcar.custom.ProgressDialog;
import com.cw.motorcar.custom.UiUtils;
import com.cw.motorcar.data.network.ApiClient;
import com.cw.motorcar.databinding.ActivityAddCarBinding;
import com.cw.motorcar.driver.adapter.CarModelAdapter;
import com.cw.motorcar.driver.adapter.CarNameAdapter;
import com.cw.motorcar.driver.model.Brand;
import com.cw.motorcar.driver.model.BrandModel;
import com.cw.motorcar.driver.model.CarBrandResponce;
import com.cw.motorcar.driver.model.CarModelResponce;
import com.cw.motorcar.driver.model.Carlist;
import com.cw.motorcar.driver.ui.activity.auth.model.CommonResponce;
import com.cw.motorcar.storage.SharedPrefManager;
import com.google.android.material.button.MaterialButton;
import com.theartofdev.edmodo.cropper.CropImage;
import com.whiteelephant.monthpicker.MonthPickerDialog;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddCarActivity extends AppCompatActivity implements BrandListener {

    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;
    @BindView(R.id.tv_car_year)
    TextView tvCaryear;
    @BindView(R.id.tv_fual_type)
    TextView tv_fual_type;
    @BindView(R.id.tv_select_car)
    TextView tvSelectCar;
    @BindView(R.id.tv_model)
    TextView tvModel;
    @BindView(R.id.add_car_image)
    AppCompatImageView add_car_image;
    private Context context;
    private String SelectedCar = "";
    private String SelectedModel = "";
    AddCarViewModel addCarViewModel;


    ArrayList<Brand> brands;
    ArrayList<BrandModel> brandModels;

    private ProgressDialog progressDialog;
    private String action = "Add";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_add_car);
        ActivityAddCarBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_add_car);
        addCarViewModel = ViewModelProviders.of(this).get(AddCarViewModel.class);
        binding.setAddcarviewmodel(addCarViewModel);
        addCarViewModel.brandListener = this;
        initview();
    }

    private void initview() {
        context = AddCarActivity.this;
        ButterKnife.bind(this);
        progressDialog = new ProgressDialog((Activity) context);
        tvTitle.setText(getString(R.string.add_car));

        Intent intent = getIntent();
        if (intent.hasExtra("action")) {
            tvTitle.setText(R.string.edit_car);
            action = "Edit";
            setData((Carlist) intent.getSerializableExtra("mycar"));
        }
        addCarViewModel.action = action;
        addCarViewModel.token = SharedPrefManager.getInstance(context).getUser().getToken();
        addCarViewModel.getBrand();
    }

    private void setData(Carlist mycar) {
        tvSelectCar.setText(mycar.getBrand());
        tvModel.setText(mycar.getModel());
        tv_fual_type.setText(mycar.getFuelType());
        tvCaryear.setText(mycar.getYear());
        addCarViewModel.reg_no = mycar.getRegNo();
        addCarViewModel.vin_no = mycar.getVinNo();
        addCarViewModel.fuel_type = mycar.getFuelType();
        addCarViewModel.brandId = mycar.getBrandId();
        addCarViewModel.ModelId = mycar.getModelId();
        addCarViewModel.year=mycar.getYear();
        addCarViewModel.carId = String.valueOf(mycar.getId());
        if (!mycar.getImage().isEmpty()) {
            Glide.with(context).load(ApiClient.SITE_URL + mycar.getImage()).into(add_car_image);
        }
    }


    @OnClick(R.id.iv_back)
    void back() {
        onBackPressed();
    }

    @OnClick(R.id.tv_select_car)
    void popupSelectCar() {
        popUpselectCar();
    }

    @OnClick(R.id.tv_model)
    void popupModel() {
        popUPselectModel();
    }

    @OnClick(R.id.tv_fual_type)
    void popupFuelType() {
        popUpFuelType();
    }

    @OnClick(R.id.btn_save)
    void goToCategory() {
        //startActivity(new Intent(context, CategoryActivity.class));
        finish();
    }

    @OnClick(R.id.frameLayout)
    void openImage() {
        openImagePicker();
    }


    private void openImagePicker() {
        CropImage.activity()
                .start(this);
    }


    @OnClick(R.id.tv_car_year)
    void openYear() {
        showYearPicker();
    }


    private void showYearPicker() {
        new MonthPickerDialog.Builder(context, new
                MonthPickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(int selectedMonth, int selectedYear) {
                        tvCaryear.setText(selectedYear + "");
                        addCarViewModel.year = String.valueOf(selectedYear);
                    }
                }, 2021, 0)
                .setMinYear(1900)
                .setMaxYear(2021)
                .showYearOnly()
                .build()
                .show();
    }


    private void popUpselectCar() {
        final Dialog mBottomSheetDialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.popup_your_car, null);
        mBottomSheetDialog.setContentView(view); // your custom view.
        AppCompatImageView ivCross = mBottomSheetDialog.findViewById(R.id.iv_cross);
        RecyclerView rvCarList = mBottomSheetDialog.findViewById(R.id.rv_car_list);
        EditText etSearchBrand = mBottomSheetDialog.findViewById(R.id.et_search_brand);
        rvCarList.setLayoutManager(new LinearLayoutManager(context));
        CarNameAdapter carNameAdapter = new CarNameAdapter(context, brands, new CarNameAdapter.SelectItemListener() {
            @Override
            public void onSelect(String name, int id) {
                SelectedCar = name;
                mBottomSheetDialog.dismiss();
                tvSelectCar.setText(name);
                addCarViewModel.brandId = String.valueOf(id);
                addCarViewModel.ModelId = "";
                tvModel.setText("");
                addCarViewModel.getModel();
            }
        });

        etSearchBrand.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                carNameAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

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
        final Dialog mBottomSheetDialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.popup_your_model, null);
        mBottomSheetDialog.setContentView(view); // your custom view.
        AppCompatImageView ivCross = mBottomSheetDialog.findViewById(R.id.iv_cross);
        RecyclerView rvCarList = mBottomSheetDialog.findViewById(R.id.rv_car_list);
        EditText et_search_name = mBottomSheetDialog.findViewById(R.id.et_search_name);
        rvCarList.setLayoutManager(new LinearLayoutManager(context));
        CarModelAdapter carNameAdapter = new CarModelAdapter(context, brandModels, new CarModelAdapter.SelectItemListener() {
            @Override
            public void onSelect(String name, int id) {
                SelectedModel = name;
                mBottomSheetDialog.dismiss();
                tvModel.setText(name);
                addCarViewModel.ModelId = String.valueOf(id);
            }
        });
        rvCarList.setAdapter(carNameAdapter);

        et_search_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                carNameAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
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

    private void popUpFuelType() {
        final Dialog mBottomSheetDialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.popup_fuel_type, null);
        mBottomSheetDialog.setContentView(view); // your custom view.
        AppCompatImageView ivCross = mBottomSheetDialog.findViewById(R.id.iv_cross);
        RadioGroup radioGroup = mBottomSheetDialog.findViewById(R.id.radioGroup);
        MaterialButton login_button = mBottomSheetDialog.findViewById(R.id.login_button);
        MaterialButton btnCancel = mBottomSheetDialog.findViewById(R.id.materialButton);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomSheetDialog.dismiss();
            }
        });
        ivCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomSheetDialog.dismiss();
            }
        });


        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton rbPetrol = mBottomSheetDialog.findViewById(R.id.rb_petrol);
                RadioButton rbDiesel = mBottomSheetDialog.findViewById(R.id.rb_diesel);
                RadioButton rbGas = mBottomSheetDialog.findViewById(R.id.rb_gas);
                RadioButton rbElectric = mBottomSheetDialog.findViewById(R.id.rb_electric);
                if (rbPetrol.isChecked()) {
                    tv_fual_type.setText(rbPetrol.getText().toString());
                    addCarViewModel.fuel_type = rbPetrol.getText().toString();
                } else if (rbDiesel.isChecked()) {
                    tv_fual_type.setText(rbDiesel.getText().toString());
                    addCarViewModel.fuel_type = rbDiesel.getText().toString();
                } else if (rbGas.isChecked()) {
                    tv_fual_type.setText(rbGas.getText().toString());
                    addCarViewModel.fuel_type = rbGas.getText().toString();
                } else if (rbElectric.isChecked()) {
                    tv_fual_type.setText(rbElectric.getText().toString());
                    addCarViewModel.fuel_type = rbElectric.getText().toString();
                }
                mBottomSheetDialog.dismiss();
            }
        });
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.setCanceledOnTouchOutside(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onStarted() {
        progressDialog.show();
    }

    @Override
    public void onSuccess(LiveData<CarBrandResponce> carResponce) {

        carResponce.observe(this, new Observer<CarBrandResponce>() {
            @Override
            public void onChanged(CarBrandResponce carBrandResponce) {
                progressDialog.dismiss();
                if (carBrandResponce.getStatus()) {
                    brands = carBrandResponce.getData();
                }
            }
        });
    }

    @Override
    public void onFailure(String meessage) {
        progressDialog.dismiss();
        UiUtils.showMessage(context, tv_fual_type, meessage);
    }

    @Override
    public void onSuccessModel(LiveData<CarModelResponce> carModelResponce) {
        carModelResponce.observe(this, new Observer<CarModelResponce>() {
            @Override
            public void onChanged(CarModelResponce carModelResponce) {
                progressDialog.dismiss();
                try {
                    if (carModelResponce.getStatus()) {
                        brandModels = carModelResponce.getData();
                    }
                } catch (Exception e) {

                }
            }
        });
    }

    @Override
    public void onSuccessCar(LiveData<CommonResponce> carModelResponce) {
        carModelResponce.observe(this, new Observer<CommonResponce>() {
            @Override
            public void onChanged(CommonResponce commonResponce) {
                progressDialog.dismiss();
                if (commonResponce.getStatus()) {
                    finish();
                } else {
                    UiUtils.showMessage(context, tv_fual_type, commonResponce.getMessage());
                }
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK) {
                    Uri resultUri = result.getUri();
                    addCarViewModel.image = resultUri.getPath();
                    Bitmap thumbnail = (BitmapFactory.decodeFile(resultUri.getPath()));
                    Bitmap bitmap = Bitmap.createScaledBitmap(thumbnail, 720, 1280, true);
                    add_car_image.setImageBitmap(bitmap);
                }
            }
        }
    }
}