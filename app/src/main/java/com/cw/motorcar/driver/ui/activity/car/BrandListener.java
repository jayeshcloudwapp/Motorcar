package com.cw.motorcar.driver.ui.activity.car;

import androidx.lifecycle.LiveData;

import com.cw.motorcar.driver.model.CarBrandResponce;
import com.cw.motorcar.driver.model.CarModelResponce;
import com.cw.motorcar.driver.ui.activity.auth.model.CommonResponce;

public interface BrandListener {

    void onStarted();

    void onSuccess(LiveData<CarBrandResponce> carResponce);

    void onFailure(String meessage);

    void onSuccessModel(LiveData<CarModelResponce> carModelResponce);
    void onSuccessCar(LiveData<CommonResponce> carModelResponce);
}
