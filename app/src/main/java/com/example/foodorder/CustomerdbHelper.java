package com.example.foodorder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.foodorder.customerSchema.CustomerTable;
import androidx.annotation.Nullable;

public class CustomerdbHelper extends SQLiteOpenHelper {

    private static final  int VERSION = 1;
    private static final String DATABASE_NAME = "customer.db";
    public CustomerdbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null,VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + CustomerTable.NAME + "(" +
                CustomerTable.Cols.EMAIL + " TEXT, " +
                CustomerTable.Cols.USERID + " TEXT, " +
                CustomerTable.Cols.PASSWORD + " TEXT )" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}