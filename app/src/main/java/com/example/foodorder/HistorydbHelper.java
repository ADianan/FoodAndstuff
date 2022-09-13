package com.example.foodorder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class HistorydbHelper extends SQLiteOpenHelper {
    private static final  int VERSION = 1;
    private static final String DATABASE_NAME = "history.db";
    public HistorydbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + HistorySchema.HistoryTable.NAME + "(" +
                HistorySchema.HistoryTable.Cols.NAME + " TEXT, " +
                HistorySchema.HistoryTable.Cols.USERID + " TEXT, " +
                HistorySchema.HistoryTable.Cols.ORDERID + " TEXT, " +
                HistorySchema.HistoryTable.Cols.TIME + " TIME, " +
                HistorySchema.HistoryTable.Cols.PRICE + " REAL, " +
                HistorySchema.HistoryTable.Cols.FOODIMAGE+ " INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
