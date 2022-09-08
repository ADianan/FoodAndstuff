package com.example.foodorder;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private Cart cart;
    private String customerID;
    private List<History> history;


    public Customer() {
        this.cart = new Cart();
        history = new ArrayList<>();
    }
}
