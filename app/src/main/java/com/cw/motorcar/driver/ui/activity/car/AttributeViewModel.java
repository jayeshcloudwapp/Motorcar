package com.cw.motorcar.driver.ui.activity.car;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cw.motorcar.driver.model.AttributeResponce;

public class AttributeViewModel extends ViewModel {

    public String token = "";
    public String subCatId = "";
    public Attributelistener atributelistenre;

    public void getAttribute() {
        atributelistenre.onStarted();
        MutableLiveData<AttributeResponce> attributeResponce = new AttributeRepositories().getAttributes(token, subCatId);
        atributelistenre.onSuccess(attributeResponce);
    }

}
