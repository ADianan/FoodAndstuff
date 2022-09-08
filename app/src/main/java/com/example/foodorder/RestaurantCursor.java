package com.example.foodorder;

import android.database.Cursor;
import android.database.CursorWrapper;
import com.example.foodorder.RestaurnatSchema.RestaurantTable;
public class RestaurantCursor extends CursorWrapper {
    public RestaurantCursor(Cursor cursor) {
        super(cursor);
    }
    public Restaurant getRestaurant()
    {
        String Name =getString(getColumnIndex(RestaurantTable.Cols.RESTAURANTNAME));
        int image =getInt(getColumnIndex(RestaurantTable.Cols.IMAGE));
        return  new Restaurant(Name,image);
    }
}
