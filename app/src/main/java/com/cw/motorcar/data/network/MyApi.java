package com.cw.motorcar.data.network;


import com.cw.motorcar.driver.model.AttributeResponce;
import com.cw.motorcar.driver.model.CarBrandResponce;
import com.cw.motorcar.driver.model.CarModelResponce;
import com.cw.motorcar.driver.model.CategoryResponce;
import com.cw.motorcar.driver.model.MyCarResponce;
import com.cw.motorcar.driver.model.MyRequestResponce;
import com.cw.motorcar.driver.ui.activity.auth.model.CommonResponce;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface MyApi {

    @FormUrlEncoded
    @POST("/AH/motorcar/api/Driver/login")
    Call<CommonResponce> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("/AH/motorcar/api/Driver/signup")
    Call<CommonResponce> userMobileRegister(
            @Field("phone") String mobile
    );

    @Multipart
    @POST("/AH/motorcar/api/Driver/updateprofile")
    Call<CommonResponce> userUpdateRegister(
            @Part("name") RequestBody name,
            @Part("email") RequestBody email,
            @Part("password") RequestBody password,
            @Part("token") RequestBody token,
            @Part MultipartBody.Part image
    );

    @Multipart
    @POST("/AH/motorcar/api/Driver/updateprofile")
    Call<CommonResponce> userUpdate(
            @Part("name") RequestBody name,
            @Part("email") RequestBody email,
            @Part("token") RequestBody token,
            @Part("address") RequestBody address,
            @Part MultipartBody.Part image
    );

    @FormUrlEncoded
    @POST("/AH/motorcar/api/Driver/categorylist")
    Call<CategoryResponce> getCategory(
            @Field("token") String token
    );

    @FormUrlEncoded
    @POST("/AH/motorcar/api/Driver/subcategorylist")
    Call<CategoryResponce> getSubCategory(
            @Field("token") String token,
            @Field("cat_id") String cat_id
    );

    @FormUrlEncoded
    @POST("/AH/motorcar/api/Driver/mycars")
    Call<MyCarResponce> getMyCar(
            @Field("token") String token
    );

    @FormUrlEncoded
    @POST("/AH/motorcar/api/Driver/brandlist")
    Call<CarBrandResponce> getBrand(
            @Field("token") String token
    );

    @FormUrlEncoded
    @POST("/AH/motorcar/api/Driver/modellist")
    Call<CarModelResponce> getModel(
            @Field("token") String token,
            @Field("brand_id") String brand_id
    );

    @Multipart
    @POST("/AH/motorcar/api/Driver/caradd")
    Call<CommonResponce> addCar(
            @Part("token") RequestBody token,
            @Part("brand_id") RequestBody brand_id,
            @Part("model_id") RequestBody model_id,
            @Part("year") RequestBody year,
            @Part("fuel_type") RequestBody fuel_type,
            @Part("reg_no") RequestBody reg_no,
            @Part("vin_no") RequestBody vin_no,
            @Part MultipartBody.Part image
    );

    @Multipart
    @POST("/AH/motorcar/api/Driver/caredit")
    Call<CommonResponce> editCar(
            @Part("car_id") RequestBody car_id,
            @Part("token") RequestBody token,
            @Part("brand_id") RequestBody brand_id,
            @Part("model_id") RequestBody model_id,
            @Part("year") RequestBody year,
            @Part("fuel_type") RequestBody fuel_type,
            @Part("reg_no") RequestBody reg_no,
            @Part("vin_no") RequestBody vin_no,
            @Part MultipartBody.Part image
    );

    @Multipart
    @POST("/AH/motorcar/api/Driver/addrequest")
    Call<CommonResponce> addNewRequest(
            @Part("token") RequestBody token,
            @Part("cat_id") RequestBody cat_id,
            @Part("sub_cat_id") RequestBody sub_cat_id,
            @Part("attribute_id") RequestBody attribute_id,
            @Part("additional_req") RequestBody additional_req,
            @Part("location") RequestBody location,
            @Part("radius") RequestBody radius,
            @Part("date_time") RequestBody date_time,
            @Part("car_id") RequestBody car_id,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part MultipartBody.Part[] image
    );


    @FormUrlEncoded
    @POST("/AH/motorcar/api/Driver/myrequest")
    Call<MyRequestResponce> getMyRequest(
            @Field("token") String token,
            @Field("action") String action
    );

    @FormUrlEncoded
    @POST("/AH/motorcar/api/Driver/attributelist")
    Call<AttributeResponce> getAttributes(
            @Field("token") String token,
            @Field("subcat_id") String brand_id
    );
}
