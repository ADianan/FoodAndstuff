package com.example.foodorder;

import android.database.Cursor;
import android.database.CursorWrapper;
import com.example.foodorder.HistorySchema.HistoryTable;

import java.sql.Time;

public class HistoryCursor extends CursorWrapper {
    public HistoryCursor(Cursor cursor) {
        super(cursor);
    }
    public History getHistory()
    {
        String Name =getString(getColumnIndex(HistoryTable.Cols.NAME));
        int image =getInt(getColumnIndex(HistoryTable.Cols.FOODIMAGE));
        int amount   =getInt(getColumnIndex(HistoryTable.Cols.AMOUNT));
        double price   =getDouble(getColumnIndex(HistoryTable.Cols.PRICE));
        String date =getString(getColumnIndex(HistoryTable.Cols.TIME));
        String orderid =getString(getColumnIndex(HistoryTable.Cols.ORDERID));
        Food food = new Food("",Name,"",price,image);
        History history = new History(orderid,new Cart(food,amount), date);
        return history;
    }
}
