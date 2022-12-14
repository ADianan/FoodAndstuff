package com.example.foodorder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.foodorder.FoodSchema.FoodTable;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class FoodStore {
    private SQLiteDatabase db;
    public void load(Context context)
    {
        this.db = new FoodStoreDbHelper( // Open database
                context.getApplicationContext()
        ).getWritableDatabase();
    }
    public List<Food>getFoodList(String restaurantName)
    {
        List<Food> foodList =  new ArrayList<>();
        String clause = "restaurant_id = ?" ;
        String[] args ={restaurantName} ;
        try {
            Cursor cursor = db.query(FoodTable.NAME, null, clause, args, null, null, null);
            foodList = foodCursor(cursor);
        } catch(Exception e){
            Exception caught = e;
        }
        return foodList;
    }
    private List<Food> foodCursor(Cursor cursor)
    {
        List<Food> foodList =  new ArrayList<>();
        FoodDBCursor foodDBCursor = new FoodDBCursor(cursor);
        try {
            foodDBCursor.moveToFirst();
            while(!foodDBCursor.isAfterLast())
            {
                foodList.add(foodDBCursor.getFood());
                foodDBCursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return foodList;
    }

    public List<Food>getHomeFood()
    {
        List<Food> foodList =  new ArrayList<>();
        List<Food> randomFood= new ArrayList<>();
        Cursor cursor  =db.query(FoodTable.NAME,null,null,null,null,null,null);
        foodList = foodCursor(cursor);
        Random rand = new Random();
        if(foodList.size()> 0)
        {
            for(int i = 0; i<10; i++)
            {
                int num = rand.nextInt(foodList.size());
                randomFood.add(foodList.get(num));
            }
        }
        return randomFood;

    }

    public void addFood(List<Food> allFood)
    {
        for(Food food: allFood)
        {

            ContentValues cv = new ContentValues();
            cv.put(FoodTable.Cols.NAME, food.getName());
            cv.put(FoodTable.Cols.DESCR, food.getDescription());
            cv.put(FoodTable.Cols.PRICE, food.getPrice());
            cv.put(FoodTable.Cols.FOODIMAGE, food.getImage());
            cv.put(FoodTable.Cols.RESTAURANTNAME, food.getRestaurantid());
            db.insert(FoodTable.NAME,null,cv);
        }

    }

}
