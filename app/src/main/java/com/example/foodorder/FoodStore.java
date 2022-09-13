package com.example.foodorder;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.foodorder.FoodSchema.FoodTable;

import java.util.ArrayList;
import java.util.List;

public class FoodStore {
    private List<Food> foodList;
    private SQLiteDatabase db;
    public void load(Context context)
    {
        this.db = new FoodStoreDbHelper( // Open database
                context.getApplicationContext()
        ).getWritableDatabase();
        foodList = new ArrayList<>();
    }
    public List<Food>getFoodList(String restaurantName)
    {
        String clause = "WHERE restaurant_id =" +"'" + restaurantName +"'";
        Cursor cursor  =db.query(FoodTable.NAME,null,clause,null,null,null,null);
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

}
