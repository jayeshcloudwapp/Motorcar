package com.cw.motorcar.data.repositories;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.cw.motorcar.data.network.ApiClient;
import com.cw.motorcar.data.network.MyApi;
import com.cw.motorcar.driver.model.CarBrandResponce;
import com.cw.motorcar.driver.model.CarModelResponce;
import com.cw.motorcar.driver.model.MyCarResponce;
import com.cw.motorcar.driver.ui.activity.auth.model.CommonResponce;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CarRepositorie {

    public MutableLiveData<MyCarResponce> myCarList(String token) {
        MutableLiveData<MyCarResponce> myCarResponce = new MutableLiveData<MyCarResponce>();
        Retrofit retrofit = ApiClient.getClient("");
        MyApi service = retrofit.create(MyApi.class);
        Call<MyCarResponce> call = service.getMyCar(token);
        call.enqueue(new Callback<MyCarResponce>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<MyCarResponce> call, Response<MyCarResponce> response) {
                if (response.isSuccessful()) {
                    myCarResponce.setValue(response.body());
                } else {
                    myCarResponce.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<MyCarResponce> call, Throwable t) {
                // loginResponce.setValue(t.getMessage());
            }
        });

        return myCarResponce;
    }

    public MutableLiveData<CarBrandResponce> getBrand(String token) {
        MutableLiveData<CarBrandResponce> myCarBrandResponce = new MutableLiveData<CarBrandResponce>();
        Retrofit retrofit = ApiClient.getClient("");
        MyApi service = retrofit.create(MyApi.class);
        Call<CarBrandResponce> call = service.getBrand(token);
        call.enqueue(new Callback<CarBrandResponce>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<CarBrandResponce> call, Response<CarBrandResponce> response) {
                if (response.isSuccessful()) {
                    myCarBrandResponce.setValue(response.body());
                } else {
                    myCarBrandResponce.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<CarBrandResponce> call, Throwable t) {
                // loginResponce.setValue(t.getMessage());
            }
        });

        return myCarBrandResponce;
    }

    public MutableLiveData<CarModelResponce> getModel(String token,String brandId) {
        MutableLiveData<CarModelResponce> myCarModelResponce = new MutableLiveData<CarModelResponce>();
        Retrofit retrofit = ApiClient.getClient("");
        MyApi service = retrofit.create(MyApi.class);
        Call<CarModelResponce> call = service.getModel(token,brandId);
        call.enqueue(new Callback<CarModelResponce>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<CarModelResponce> call, Response<CarModelResponce> response) {
                if (response.isSuccessful()) {
                    myCarModelResponce.setValue(response.body());
                } else {
                    myCarModelResponce.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<CarModelResponce> call, Throwable t) {
                // loginResponce.setValue(t.getMessage());
            }
        });

        return myCarModelResponce;
    }


}
