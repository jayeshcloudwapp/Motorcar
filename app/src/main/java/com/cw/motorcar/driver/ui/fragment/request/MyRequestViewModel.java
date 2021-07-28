package com.cw.motorcar.driver.ui.fragment.request;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cw.motorcar.data.repositories.MyRequestRespositories;
import com.cw.motorcar.driver.model.MyRequestResponce;

public class MyRequestViewModel extends ViewModel {

    public RequestListener requestListener;
    public String token = "";
    public String action = "";

    public void getMyRequest() {
        requestListener.onStarted();
        MutableLiveData<MyRequestResponce> requestResponceLiveData = new MyRequestRespositories().getMyRequest(token, action);
        requestListener.onSuccess(requestResponceLiveData);
    }
}
