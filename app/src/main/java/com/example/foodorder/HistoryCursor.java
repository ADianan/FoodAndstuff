package com.example.foodorder;

import android.database.Cursor;
import android.database.CursorWrapper;

public class HistoryCursor extends CursorWrapper {
    public HistoryCursor(Cursor cursor) {
        super(cursor);
    }
}
