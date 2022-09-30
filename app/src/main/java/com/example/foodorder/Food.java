package com.example.foodorder;

public class Food {

    private String name;
    private String description;
    private   double price;
    private int image;
    private String Restaurantid;

    public Food(String restaurantid,String name, String description, double price, int image) {
        Restaurantid = restaurantid;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }
}
