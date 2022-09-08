package com.example.foodorder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.foodorder.CustomerSchema.CustomerTable;
import androidx.annotation.Nullable;

public class CustomerdbHelper extends SQLiteOpenHelper {

    private static final  int VERSION = 1;
    private static final String DATABASE_NAME = "food.db";
    public CustomerdbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null,VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + CustomerTable.NAME + "(" +
                CustomerTable.Cols.NAME + " TEXT, " +
                CustomerTable.Cols.CUSTOMERID + " INTEGER, " +
                CustomerTable.Cols.TIME + " TIME, " +
                CustomerTable.Cols.PRICE + " REAL, " +
                CustomerTable.Cols.FOODIMAGE+ " INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}