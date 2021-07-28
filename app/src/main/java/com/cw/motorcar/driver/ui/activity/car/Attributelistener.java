package com.cw.motorcar.driver.ui.activity.car;

import androidx.lifecycle.LiveData;

import com.cw.motorcar.driver.model.AttributeResponce;
import com.cw.motorcar.driver.model.MyCarResponce;

public interface Attributelistener {
    void onStarted();

    void onSuccess(LiveData<AttributeResponce> attributeResponce);

    void onFailure(String meessage);
}
