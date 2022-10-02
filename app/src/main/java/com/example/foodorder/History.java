package com.example.foodorder;

import java.time.LocalTime;

public class History {

    public String orderId;
    public  Cart purchasedFood;
    public String dateOfPurchase;

    public History(String orderId, Cart newCart, String date)
    {
        this.orderId = orderId;
        purchasedFood = newCart;
        dateOfPurchase = date;
    }
}
