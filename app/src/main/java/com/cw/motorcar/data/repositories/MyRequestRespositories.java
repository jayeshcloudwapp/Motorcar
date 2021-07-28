package com.cw.motorcar.data.repositories;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;

import com.cw.motorcar.data.network.ApiClient;
import com.cw.motorcar.data.network.MyApi;
import com.cw.motorcar.driver.model.CarBrandResponce;
import com.cw.motorcar.driver.model.MyRequestResponce;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MyRequestRespositories {

    public MutableLiveData<MyRequestResponce> getMyRequest(String token, String action) {
        MutableLiveData<MyRequestResponce> myRequestResponce = new MutableLiveData<MyRequestResponce>();
        Retrofit retrofit = ApiClient.getClient("");
        MyApi service = retrofit.create(MyApi.class);
        Call<MyRequestResponce> call = service.getMyRequest(token, action);
        call.enqueue(new Callback<MyRequestResponce>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<MyRequestResponce> call, Response<MyRequestResponce> response) {
                if (response.isSuccessful()) {
                    myRequestResponce.setValue(response.body());
                } else {
                    myRequestResponce.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<MyRequestResponce> call, Throwable t) {
                // loginResponce.setValue(t.getMessage());
            }
        });

        return myRequestResponce;
    }
}
