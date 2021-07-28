package com.cw.motorcar.driver.ui.activity.car;

import android.net.Uri;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cw.motorcar.data.repositories.AddCarRepositories;
import com.cw.motorcar.data.repositories.NewRequestRepositories;
import com.cw.motorcar.driver.ui.activity.auth.AuthListener;
import com.cw.motorcar.driver.ui.activity.auth.model.CommonResponce;

import java.util.ArrayList;
import java.util.List;

public class NewRequstViewModel extends ViewModel {

    public String token = "";
    public String catId = "";
    public String subCatId = "";
    public String attributeId = "";
    public String additionalReq = "";
    public String location = "";
    public String radius = "";
    public String dateTime = "";
    public List<Uri> images = new ArrayList<>();
    public AuthListener authListener;


    public void onSaveClickButton(View view) {
        authListener.onStarted();
        if (location.isEmpty()) {
            authListener.onFailure("Enter Location");
            return;
        } else if (radius.isEmpty()) {
            authListener.onFailure("Select Radius");
            return;
        } else if (dateTime.isEmpty()) {
            authListener.onFailure("Select Data Time");
            return;
        }

        LiveData<CommonResponce> useraddReq = new NewRequestRepositories().addRequest(token, catId, subCatId, attributeId, additionalReq, location, radius, dateTime, catId, "0.00", "0.00", images);
        authListener.onSuccess(useraddReq);
    }
}
