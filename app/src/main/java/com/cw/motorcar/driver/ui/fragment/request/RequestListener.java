package com.cw.motorcar.driver.ui.fragment.request;

import androidx.lifecycle.LiveData;

import com.cw.motorcar.driver.model.MyRequestResponce;


public interface RequestListener {
    void onStarted();
    void onSuccess(LiveData<MyRequestResponce> requestResponce);
    void onFailure(String meessage);
}
