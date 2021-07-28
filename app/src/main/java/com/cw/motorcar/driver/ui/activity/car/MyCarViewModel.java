package com.cw.motorcar.driver.ui.activity.car;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cw.motorcar.data.repositories.CarRepositorie;
import com.cw.motorcar.driver.model.MyCarResponce;

public class MyCarViewModel extends ViewModel {

    public MyCarListener authListener;
    public String token;


    public void getUserCarMutableLiveData() {
        authListener.onStarted();
        populateList();
    }

    public void init() {
        populateList();
        //carLiveData.setValue(carArrayList);
    }

    private void populateList() {
        MutableLiveData<MyCarResponce> carResponceLiveData = new CarRepositorie().myCarList(token);
        authListener.onSuccess(carResponceLiveData);
    }
}
