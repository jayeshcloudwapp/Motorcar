package com.cw.motorcar.driver.ui.activity.car;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;

import com.cw.motorcar.data.network.ApiClient;
import com.cw.motorcar.data.network.MyApi;
import com.cw.motorcar.driver.model.AttributeResponce;
import com.cw.motorcar.driver.model.CarBrandResponce;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AttributeRepositories {

    public MutableLiveData<AttributeResponce> getAttributes(String token, String subCatID) {
        MutableLiveData<AttributeResponce> myCarBrandResponce = new MutableLiveData<AttributeResponce>();
        Retrofit retrofit = ApiClient.getClient("");
        MyApi service = retrofit.create(MyApi.class);
        Call<AttributeResponce> call = service.getAttributes(token, subCatID);
        call.enqueue(new Callback<AttributeResponce>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<AttributeResponce> call, Response<AttributeResponce> response) {
                if (response.isSuccessful()) {
                    myCarBrandResponce.setValue(response.body());
                } else {
                    myCarBrandResponce.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<AttributeResponce> call, Throwable t) {
                // loginResponce.setValue(t.getMessage());
            }
        });

        return myCarBrandResponce;
    }
}
