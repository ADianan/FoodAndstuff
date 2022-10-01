package com.example.foodorder;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private   Food food;
    private int amount;
    public Cart(Food newFood, int newAmount)
    {
        food = newFood;
        amount = newAmount;
    }


    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
