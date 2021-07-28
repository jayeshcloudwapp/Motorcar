package com.cw.motorcar.driver.ui.activity.auth;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cw.motorcar.data.repositories.UserRepositories;
import com.cw.motorcar.driver.ui.activity.auth.model.CommonResponce;

public class LoginViewModel extends ViewModel {
    public String email = "";
    public String password = "";
    public AuthListener authListener;


    public void onLoginClickButtion(View view) {
        authListener.onStarted();
        if (email.isEmpty()) {
            authListener.onFailure("Phone can not be empty.");
            return;
        } else if (email.length() != 13) {
            authListener.onFailure("Invalid Phone,Please enter phone with country code and +");
            return;
        } else if (password.isEmpty()) {
            authListener.onFailure("Password can not be empty.");
            return;
        }
        LiveData<CommonResponce> loginResponce = new UserRepositories().userLogin(email, password);
        authListener.onSuccess(loginResponce);
    }
}
