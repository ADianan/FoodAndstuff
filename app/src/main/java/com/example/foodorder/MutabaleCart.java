package com.example.foodorder;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MutabaleCart extends ViewModel {
    MutableLiveData<CartViewModel> mutableLiveData;

    public MutabaleCart() {
        this.mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(new CartViewModel());
    }

    public CartViewModel getMutableLiveData() {
        return mutableLiveData.getValue();
    }

    public void setMutableLiveData(CartViewModel data) {
        this.mutableLiveData.setValue(data);
    }
}
