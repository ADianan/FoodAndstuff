package com.example.foodorder;

public class Restaurant
{
    private String name;
    private int imageid;

    public Restaurant(String name, int imageid) {
        this.name = name;
        this.imageid = imageid;
    }

    public String getName() {
        return name;
    }

    public int getImageid() {
        return imageid;
    }
}
