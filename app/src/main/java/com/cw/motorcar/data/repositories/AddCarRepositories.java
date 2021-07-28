package com.cw.motorcar.data.repositories;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.cw.motorcar.data.network.ApiClient;
import com.cw.motorcar.data.network.MyApi;

import com.cw.motorcar.driver.ui.activity.auth.model.CommonResponce;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddCarRepositories {

    public LiveData<CommonResponce> useraddCar(String token, String brandId, String modelId, String year, String fuelType, String regNo, String vinNo, String image) {
        MultipartBody.Part picture = null;

        if (image != null) {
            File file = new File(image);
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            picture = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
        }

        RequestBody BrandId = RequestBody.create(MediaType.parse("multipart/form-data"), brandId);
        RequestBody ModelId = RequestBody.create(MediaType.parse("multipart/form-data"), modelId);
        RequestBody Year = RequestBody.create(MediaType.parse("multipart/form-data"), year);
        RequestBody FuelType = RequestBody.create(MediaType.parse("multipart/form-data"), fuelType);
        RequestBody RegNo = RequestBody.create(MediaType.parse("multipart/form-data"), regNo);
        RequestBody VinNo = RequestBody.create(MediaType.parse("multipart/form-data"), vinNo);
        RequestBody Token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        MutableLiveData<CommonResponce> loginResponce = new MutableLiveData<CommonResponce>();
        Retrofit retrofit = ApiClient.getClient("");
        MyApi service = retrofit.create(MyApi.class);
        Call<CommonResponce> call = service.addCar(Token, BrandId, ModelId, Year, FuelType, RegNo, VinNo, picture);
        call.enqueue(new Callback<CommonResponce>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<CommonResponce> call, Response<CommonResponce> response) {
                if (response.isSuccessful()) {
                    loginResponce.setValue(response.body());
                } else {
                    loginResponce.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<CommonResponce> call, Throwable t) {
                CommonResponce commonResponce = new CommonResponce();
                commonResponce.setStatus(false);
                commonResponce.setMessage(t.getMessage().toLowerCase());
                loginResponce.setValue(commonResponce);
            }
        });
        return loginResponce;
    }

    public LiveData<CommonResponce> userEditCar(String carid,String token, String brandId, String modelId, String year, String fuelType, String regNo, String vinNo, String image) {
        MultipartBody.Part picture = null;

        if (image != null&&! image.isEmpty()) {
            File file = new File(image);
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            picture = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
        }

        RequestBody CarId = RequestBody.create(MediaType.parse("multipart/form-data"), carid);
        RequestBody BrandId = RequestBody.create(MediaType.parse("multipart/form-data"), brandId);
        RequestBody ModelId = RequestBody.create(MediaType.parse("multipart/form-data"), modelId);
        RequestBody Year = RequestBody.create(MediaType.parse("multipart/form-data"), year);
        RequestBody FuelType = RequestBody.create(MediaType.parse("multipart/form-data"), fuelType);
        RequestBody RegNo = RequestBody.create(MediaType.parse("multipart/form-data"), regNo);
        RequestBody VinNo = RequestBody.create(MediaType.parse("multipart/form-data"), vinNo);
        RequestBody Token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        MutableLiveData<CommonResponce> loginResponce = new MutableLiveData<CommonResponce>();
        Retrofit retrofit = ApiClient.getClient("");
        MyApi service = retrofit.create(MyApi.class);
        Call<CommonResponce> call = service.editCar(CarId,Token, BrandId, ModelId, Year, FuelType, RegNo, VinNo, picture);
        call.enqueue(new Callback<CommonResponce>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<CommonResponce> call, Response<CommonResponce> response) {
                if (response.isSuccessful()) {
                    loginResponce.setValue(response.body());
                } else {
                    loginResponce.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<CommonResponce> call, Throwable t) {
                CommonResponce commonResponce = new CommonResponce();
                commonResponce.setStatus(false);
                commonResponce.setMessage(t.getMessage().toLowerCase());
                loginResponce.setValue(commonResponce);
            }
        });
        return loginResponce;
    }
}
