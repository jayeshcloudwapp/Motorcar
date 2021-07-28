package com.cw.motorcar.driver.ui.activity.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cw.motorcar.data.repositories.UserRepositories;
import com.cw.motorcar.driver.ui.activity.auth.model.CommonResponce;

public class RegisterViewModel extends ViewModel {

    public String name = "";
    public String password = "";
    public String email = "";
    public String address = "";
    public String cpassword = "";
    public String image = "";
    public String token = "";
    public AuthListener authListener;

    public void onRegisterClickButtion() {
        authListener.onStarted();
        if (name.isEmpty()) {
            authListener.onFailure("Name can not be empty.");
            return;
        } else if (email.isEmpty()) {
            authListener.onFailure("Email can not be empty.");
            return;
        } else if (address.isEmpty()) {
            authListener.onFailure("Address can not be empty.");
            return;
        } else if (password.isEmpty()) {
            authListener.onFailure("Password can not be empty.");
            return;
        } else if (!cpassword.equals(password)) {
            authListener.onFailure("Password and Confirm password should be match.");
            return;
        }

        LiveData<CommonResponce> loginResponce = new UserRepositories().userRegister(name, email, password, token, image);
        authListener.onSuccess(loginResponce);
    }
}
