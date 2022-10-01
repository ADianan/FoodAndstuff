package com.example.foodorder;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class CartViewModel extends ViewModel {
    //This class allows cart data to be access by fragments

    public List<Cart> cart;
    public Customer customer;
    public  CartViewModel() {
        cart = new ArrayList<>();
    }
    }


