package com.cw.motorcar.data.repositories;

import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.cw.motorcar.data.network.ApiClient;
import com.cw.motorcar.data.network.MyApi;
import com.cw.motorcar.driver.ui.activity.auth.model.CommonResponce;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NewRequestRepositories {

    public LiveData<CommonResponce> addRequest(String token, String cat_id, String sub_cat_id, String attribute_id, String additional_req, String location, String radius, String date_time, String car_id, String latitude, String longitude, List<Uri> images) {
        MultipartBody.Part[] picture = new MultipartBody.Part[images.size()];

        for (int i = 0; i < images.size(); i++) {
            File file = new File(images.get(i).getPath());
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part filePart = MultipartBody.Part.createFormData("image[]",
                    file.getName(), requestBody);
            //image.add(filePart);
            picture[i] = filePart;
        }
//        if (image != null) {
//            File file = new File(image);
//            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//            picture = MultipartBody.Part.createFormData("image[]", file.getName(), requestBody);
//        }

        RequestBody catId = RequestBody.create(MediaType.parse("multipart/form-data"), cat_id);
        RequestBody subCatId = RequestBody.create(MediaType.parse("multipart/form-data"), sub_cat_id);
        RequestBody attributeId = RequestBody.create(MediaType.parse("multipart/form-data"), attribute_id);
        RequestBody additionalReq = RequestBody.create(MediaType.parse("multipart/form-data"), additional_req);
        RequestBody loc = RequestBody.create(MediaType.parse("multipart/form-data"), location);
        RequestBody rad = RequestBody.create(MediaType.parse("multipart/form-data"), radius);
        RequestBody dateTime = RequestBody.create(MediaType.parse("multipart/form-data"), date_time);
        RequestBody carId = RequestBody.create(MediaType.parse("multipart/form-data"), car_id);
        RequestBody lat = RequestBody.create(MediaType.parse("multipart/form-data"), latitude);
        RequestBody lon = RequestBody.create(MediaType.parse("multipart/form-data"), longitude);
        RequestBody Token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        MutableLiveData<CommonResponce> loginResponce = new MutableLiveData<CommonResponce>();
        Retrofit retrofit = ApiClient.getClient("");
        MyApi service = retrofit.create(MyApi.class);
        Call<CommonResponce> call = service.addNewRequest(Token, catId, subCatId, attributeId, additionalReq, loc, rad, dateTime, carId, lat, lon, picture);
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
