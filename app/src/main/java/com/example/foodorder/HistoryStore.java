package com.example.foodorder;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class HistoryStore {
    private List<List<History>> history;
    private SQLiteDatabase db;
    public void load(Context context)
    {
        this.db = new FoodStoreDbHelper( // Open database
                context.getApplicationContext()
        ).getWritableDatabase();
        history = new ArrayList<List<History>>();
    }
    public List<List<History>>  getCustomer(String email,String password)
    {
        String clause = "WHERE email =" +"'" + email +"'" +" AND " +"password = " +  password;
        List<History> orderHistory = new ArrayList<>();
        Cursor cursor  =db.query(customerSchema.CustomerTable.NAME,null,clause,null,null,null,null);
        HistoryCursor foodDBCursor = new HistoryCursor(cursor);
        try {
            foodDBCursor.moveToFirst();
            while(!foodDBCursor.isAfterLast())
            {
                //history = foodDBCursor.getCustomer();
                foodDBCursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return history;
    }
}
