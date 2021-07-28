package com.cw.motorcar.driver.ui.activity.car;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cw.motorcar.data.repositories.AddCarRepositories;
import com.cw.motorcar.data.repositories.CarRepositorie;
import com.cw.motorcar.driver.model.CarBrandResponce;
import com.cw.motorcar.driver.model.CarModelResponce;
import com.cw.motorcar.driver.ui.activity.auth.model.CommonResponce;

public class AddCarViewModel extends ViewModel {
    BrandListener brandListener;
    public String token = "";
    public String carId = "";
    public String brandId = "";
    public String ModelId = "";
    public String year = "";
    public String fuel_type = "";
    public String reg_no = "";
    public String vin_no = "";
    public String image = "";
    public String action = "";


    public void getBrand() {
        brandListener.onStarted();
        LiveData<CarBrandResponce> carBrandResponceLiveData = new CarRepositorie().getBrand(token);
        brandListener.onSuccess(carBrandResponceLiveData);
    }

    public void getModel() {
        brandListener.onStarted();
        LiveData<CarModelResponce> carModelResponceLiveData = new CarRepositorie().getModel(token, brandId);
        brandListener.onSuccessModel(carModelResponceLiveData);
    }

    public void onSaveClickButton(View view) {
        brandListener.onStarted();
        if (brandId.isEmpty()) {
            brandListener.onFailure("Select Brand");
            return;
        } else if (ModelId.isEmpty()) {
            brandListener.onFailure("Select Model");
            return;
        } else if (reg_no.isEmpty()) {
            brandListener.onFailure("Enter Reg. No.");
            return;
        } else if (fuel_type.isEmpty()) {
            brandListener.onFailure("Select Fuel Type");
            return;
        } else if (year.isEmpty()) {
            brandListener.onFailure("Select Year");
            return;
        } else if (vin_no.isEmpty()) {
            brandListener.onFailure("Enter Vehicle No.");
            return;
        }

        if (action.equals("Add")) {
            LiveData<CommonResponce> useraddCar = new AddCarRepositories().useraddCar(token, brandId, ModelId, year, fuel_type, reg_no, vin_no, image);
            brandListener.onSuccessCar(useraddCar);
        }else{
            LiveData<CommonResponce> useraddCar = new AddCarRepositories().userEditCar(carId,token, brandId, ModelId, year, fuel_type, reg_no, vin_no, image);
            brandListener.onSuccessCar(useraddCar);
        }
    }
}
