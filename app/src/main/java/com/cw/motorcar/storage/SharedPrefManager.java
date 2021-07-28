package com.cw.motorcar.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.cw.motorcar.driver.ui.activity.auth.model.UserData;


public class SharedPrefManager {

    private static SharedPrefManager mInstance;
    private static Context mCtx;
    private static final String SHARED_PREF_NAME = "MotorCar";
    private static final String KEY_USER_ID = "keyuserid";
    private static final String KEY_USER_NAME = "keyusername";
    private static final String KEY_USER_EMAIL = "keyuseremail";
    private static final String KEY_USER_PHONE = "keyuserphone";
    private static final String KEY_USER_ADDRESS = "keyuseraddress";
    private static final String KEY_USER_IMAGE = "keyuserimage";
    private static final String KEY_USER_DEVICE_TOKEN = "keyuserdevicetoken";
    private static final String KEY_EMAIL_VERIFIED = "keyuseremailverified";
    private static final String KEY_PASSWORD = "keyuserpassword";


    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public boolean userLogin(UserData userData) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_USER_ID, userData.getId());
        editor.putString(KEY_USER_NAME, userData.getName());
        editor.putString(KEY_USER_EMAIL, userData.getEmail());
        editor.putString(KEY_USER_PHONE, userData.getPhone());
        editor.putString(KEY_USER_ADDRESS, userData.getAddress());
        editor.putString(KEY_USER_IMAGE, userData.getImage());
        editor.putString(KEY_EMAIL_VERIFIED, userData.getEmailVerifiedAt());
        editor.putString(KEY_USER_DEVICE_TOKEN, userData.getToken());
        editor.apply();
        return true;
    }


    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if (sharedPreferences.getString(KEY_USER_EMAIL, null) != null)
            return true;
        return false;
    }


    public UserData getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new UserData(
                sharedPreferences.getInt(KEY_USER_ID, 0),
                sharedPreferences.getString(KEY_USER_NAME, null),
                sharedPreferences.getString(KEY_USER_EMAIL, null),
                sharedPreferences.getString(KEY_USER_PHONE, null),
                sharedPreferences.getString(KEY_USER_ADDRESS, null),
                sharedPreferences.getString(KEY_USER_IMAGE, null),
                sharedPreferences.getString(KEY_EMAIL_VERIFIED, null),
                sharedPreferences.getString(KEY_USER_DEVICE_TOKEN, null)
        );
    }


    public void putString(String key, String value) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();

    }

    public String getString(String key) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, null);
    }

    public void putInt(String flag, int value) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(flag, value);
        editor.apply();

    }

    public int getInt(String key) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, 0);
    }

    //

    public boolean logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }
}
