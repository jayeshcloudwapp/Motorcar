package com.cw.motorcar.driver.ui.activity.auth;

import androidx.lifecycle.LiveData;

import com.cw.motorcar.driver.ui.activity.auth.model.CommonResponce;

public interface AuthListener {
    void onStarted();
    void onSuccess(LiveData<CommonResponce> loginResponce);
    void onFailure(String meessage);
}
