package com.example.foodorder;

import android.database.Cursor;
import android.database.CursorWrapper;
import com.example.foodorder.FoodSchema.FoodTable;
public class FoodDBCursor extends CursorWrapper {
    public FoodDBCursor(Cursor cursor)
    {
        super(cursor);
    }
    public Food getFood()
    {
        double price = getDouble(getColumnIndex(FoodTable.Cols.PRICE));
        String name = getString(getColumnIndex(FoodTable.Cols.NAME));
        String description = getString(getColumnIndex(FoodTable.Cols.DESCR));
        int image = getInt(getColumnIndex(FoodTable.Cols.FOODIMAGE));
        return new Food(name, description, price, image);
    }
}
