package com.cw.motorcar.driver.ui.fragment.profile;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cw.motorcar.data.repositories.UserRepositories;
import com.cw.motorcar.driver.ui.activity.auth.AuthListener;
import com.cw.motorcar.driver.ui.activity.auth.model.CommonResponce;

public class ProfileViewModel extends ViewModel {
    public String name = "";
    public String email = "";
    public String mobile = "";
    public String address = "";
    public String token = "";
    public String image = "";
    public AuthListener authListener;


    public void onClickUpdate(View view) {
        authListener.onStarted();
        if (name.isEmpty()) {
            authListener.onFailure("Name can not be empty.");
            return;
        } else if (email.isEmpty()) {
            authListener.onFailure("Email can not be empty.");
            return;
        } else if (address==null||address.isEmpty()) {
            authListener.onFailure("Address can not be empty.");
            return;
        }

        LiveData<CommonResponce> loginResponce = new UserRepositories().userUpdate(name, email,address, token, image);
        authListener.onSuccess(loginResponce);
    }

}
