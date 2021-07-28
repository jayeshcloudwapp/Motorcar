package com.cw.motorcar.driver.ui.activity.auth;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cw.motorcar.data.repositories.UserRepositories;
import com.cw.motorcar.driver.ui.activity.auth.model.CommonResponce;


public class VerifyViewModel extends ViewModel {
    public String mobile = "";
    public String countryCode = "";
    public AuthListener authListener;

    public void onVerifyClickButtion() {
        authListener.onStarted();
        if (mobile.isEmpty()) {
            authListener.onFailure("Phone can not be empty.");
            return;
        } else if (mobile.length() != 10) {
            authListener.onFailure("Invalid Phone.");
            return;
        }
        LiveData<CommonResponce> loginResponce = new UserRepositories().userMobileRegister(countryCode + mobile);
        authListener.onSuccess(loginResponce);
    }
}
