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

public class UserRepositories {

    public LiveData<CommonResponce> userLogin(String email, String passwor) {
        MutableLiveData<CommonResponce> loginResponce = new MutableLiveData<CommonResponce>();
        Retrofit retrofit = ApiClient.getClient("");
        MyApi service = retrofit.create(MyApi.class);
        Call<CommonResponce> call = service.userLogin(email, passwor);
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
                // loginResponce.setValue(t.getMessage());
            }
        });
        return loginResponce;
    }

    public LiveData<CommonResponce> userMobileRegister(String mobile) {
        MutableLiveData<CommonResponce> loginResponce = new MutableLiveData<CommonResponce>();
        Retrofit retrofit = ApiClient.getClient("");
        MyApi service = retrofit.create(MyApi.class);
        Call<CommonResponce> call = service.userMobileRegister(mobile);
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
                // loginResponce.setValue(t.getMessage());
            }
        });
        return loginResponce;
    }

    public LiveData<CommonResponce> userRegister(String name, String email, String password, String token, String image) {
        MultipartBody.Part picture = null;

        if (image != null) {
            File file = new File(image);
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            picture = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
        }

        RequestBody Name = RequestBody.create(MediaType.parse("multipart/form-data"), name);
        RequestBody Email = RequestBody.create(MediaType.parse("multipart/form-data"), email);
        RequestBody Password = RequestBody.create(MediaType.parse("multipart/form-data"), password);
        RequestBody Token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        MutableLiveData<CommonResponce> loginResponce = new MutableLiveData<CommonResponce>();
        Retrofit retrofit = ApiClient.getClient("");
        MyApi service = retrofit.create(MyApi.class);
        Call<CommonResponce> call = service.userUpdateRegister(Name, Email, Password, Token, picture);
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
                // loginResponce.setValue(t.getMessage());
            }
        });
        return loginResponce;
    }

    public LiveData<CommonResponce> userUpdate(String name, String email, String address, String token, String image) {
        MultipartBody.Part picture = null;

        if (image != null) {
            File file = new File(image);
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            picture = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
        }

        RequestBody Name = RequestBody.create(MediaType.parse("multipart/form-data"), name);
        RequestBody Email = RequestBody.create(MediaType.parse("multipart/form-data"), email);
        RequestBody Token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody Address = RequestBody.create(MediaType.parse("multipart/form-data"), address);
        MutableLiveData<CommonResponce> loginResponce = new MutableLiveData<CommonResponce>();
        Retrofit retrofit = ApiClient.getClient("");
        MyApi service = retrofit.create(MyApi.class);
        Call<CommonResponce> call = service.userUpdate(Name, Email, Token,Address, picture);
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
                // loginResponce.setValue(t.getMessage());
            }
        });
        return loginResponce;
    }
}
