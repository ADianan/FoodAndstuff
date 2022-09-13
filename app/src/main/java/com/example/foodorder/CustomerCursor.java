package com.example.foodorder;

import android.database.Cursor;
import android.database.CursorWrapper;
import com.example.foodorder.customerSchema.CustomerTable;
public class CustomerCursor extends CursorWrapper {
    public CustomerCursor(Cursor cursor) {
        super(cursor);
    }
    public Customer getCustomer()
    {
        String email = getString(getColumnIndex(CustomerTable.Cols.EMAIL));
        String password = getString(getColumnIndex(CustomerTable.Cols.PASSWORD));
        String userId = getString(getColumnIndex(CustomerTable.Cols.USERID));
        return new Customer(email, password, userId);
    }
}
