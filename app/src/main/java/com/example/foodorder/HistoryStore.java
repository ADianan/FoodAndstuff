package com.example.foodorder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.foodorder.HistorySchema.HistoryTable;
import java.util.ArrayList;
import java.util.List;

public class HistoryStore {
    private SQLiteDatabase db;
    public void load(Context context)
    {
        this.db = new HistorydbHelper( // Open database
                context.getApplicationContext()
        ).getWritableDatabase();
    }
    public List<History>  getCustomerHistory(String userId)
    {
        String clause = "WHERE user_id =" +"'" + userId +"'";
        List<History> orderHistory = new ArrayList<>();
        Cursor cursor  =db.query(HistoryTable.NAME,null,null,null,null,null,null);
        HistoryCursor foodDBCursor = new HistoryCursor(cursor);
        try {
            foodDBCursor.moveToFirst();
            while(!foodDBCursor.isAfterLast())
            {
                History history = foodDBCursor.getHistory();
                orderHistory.add(history);
                foodDBCursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return orderHistory;
    }
    public void addOrder(History history,Customer customer)
    {
        ;
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
