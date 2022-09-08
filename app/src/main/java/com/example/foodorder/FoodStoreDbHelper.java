package com.example.foodorder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.foodorder.FoodSchema.FoodTable;
import androidx.annotation.Nullable;

public class FoodStoreDbHelper extends SQLiteOpenHelper {

    private static final  int VERSION = 1;
    private static final String DATABASE_NAME = "food.db";
    public FoodStoreDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null,VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + FoodTable.NAME + "(" +
                FoodTable.Cols.NAME + " TEXT, " +
                FoodTable.Cols.DESCR + " TEXT, " +
                FoodTable.Cols.PRICE + " REAL, " +
                FoodTable.Cols.FOODIMAGE + " INTEGER, " +
                FoodTable.Cols.RESTAURANTNAME + " TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
