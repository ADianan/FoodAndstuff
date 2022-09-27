package com.example.foodorder;

import androidx.lifecycle.ViewModel;

public class CartViewModel extends ViewModel {
    //This class allows cart data to be access by fragments
    public  Cart cart;
    public  CartViewModel()
    {
        cart = new Cart();
    }

}
