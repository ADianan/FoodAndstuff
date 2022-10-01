package com.example.foodorder;

import android.content.ContentValues;
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
    public List<List<History>>  getCustomer(String userId)
    {
        String clause = "WHERE user_id =" +"'" + userId +"'";
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
    public void addOrder(History history,Customer customer)
    {
        int amount =history.purchasedFood.getAmount();
        ContentValues cv = new ContentValues();
        cv.put(HistorySchema.HistoryTable.Cols.ORDERID, history.orderId);
        cv.put(HistorySchema.HistoryTable.Cols.TIME, String.valueOf(history.dateOfPurchase));
        cv.put(HistorySchema.HistoryTable.Cols.PRICE, history.purchasedFood.getFood().getPrice() *amount);
        cv.put(HistorySchema.HistoryTable.Cols.FOODIMAGE, history.purchasedFood.getFood().getImage());
        cv.put(HistorySchema.HistoryTable.Cols.NAME, history.purchasedFood.getFood().getName() );
        cv.put(HistorySchema.HistoryTable.Cols.AMOUNT, amount);
        cv.put(HistorySchema.HistoryTable.Cols.USERID, customer.getUserid() );
        db.insert(HistorySchema.HistoryTable.NAME,null,cv);
    }
}
