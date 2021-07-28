package com.cw.motorcar.driver.ui.activity.car;

import androidx.lifecycle.LiveData;

import com.cw.motorcar.driver.model.MyCarResponce;

public interface MyCarListener {

    void onStarted();

    void onSuccess(LiveData<MyCarResponce> carResponce);

    void onFailure(String meessage);
}
