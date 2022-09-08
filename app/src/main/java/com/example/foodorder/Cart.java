package com.example.foodorder;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<Food> bucket;
    public Cart()
    {
        bucket = new ArrayList<>();
    }


    public List<Food> getBucket() {
        return bucket;
    }

    public void addFood(Food food) {
        bucket.add(food);

    }
}
