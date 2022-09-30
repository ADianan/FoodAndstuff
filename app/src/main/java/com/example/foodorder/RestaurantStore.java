package com.example.foodorder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.foodorder.RestaurnatSchema.RestaurantTable;

import java.util.ArrayList;
import java.util.List;
import  com.example.foodorder.RestaurnatSchema.RestaurantTable;
public class RestaurantStore {
    private List<Restaurant> restaurantList;
    private SQLiteDatabase db;
    public void load(Context context)
    {
        this.db = new RestaurantDbHelper( // Open database
                context.getApplicationContext()
        ).getWritableDatabase();
        restaurantList = new ArrayList<>();
    }
    public List<Restaurant>getFoodList(String restaurantName)
    {

        Cursor cursor  =db.query(RestaurantTable.NAME,null,null,null,null,null,null);
        RestaurantCursor restaurantCursor = new RestaurantCursor(cursor);
        try {
            restaurantCursor.moveToFirst();
            while(!restaurantCursor.isAfterLast())
            {
                restaurantList.add(restaurantCursor.getRestaurant());
                restaurantCursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return restaurantList;
    }
    public void addRestaurant(List<Restaurant> restaurantList)
    {
        for(Restaurant restaurant : restaurantList)
        {

            ContentValues cv = new ContentValues();
            cv.put(RestaurantTable.Cols.RESTAURANTNAME, restaurant.getName());
            cv.put(RestaurantTable.Cols.IMAGE, restaurant.getImageid());
            db.insert(FoodSchema.FoodTable.NAME,null,cv);
        }
    }
}
