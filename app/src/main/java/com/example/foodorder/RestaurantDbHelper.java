package com.example.foodorder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.foodorder.RestaurnatSchema.RestaurantTable;
import androidx.annotation.Nullable;

public class RestaurantDbHelper extends SQLiteOpenHelper {

    private static final  int VERSION = 1;
    private static final String DATABASE_NAME = "restaurant.db";
    public RestaurantDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null,VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + RestaurantTable.NAME + "(" +
                RestaurantTable.Cols.IMAGE + " INTEGER, " +
                RestaurantTable.Cols.RESTAURANTNAME + " TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}