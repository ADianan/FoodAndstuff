package com.example.foodorder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.foodorder.customerSchema.CustomerTable;
import java.util.List;

public class CustomerStore {
    private Customer customer;
    private SQLiteDatabase db;
    public void load(Context context)
    {
        this.db = new CustomerdbHelper( // Open database
                context.getApplicationContext()
        ).getWritableDatabase();
    }
    public Customer getCustomer(String email,String password)
    {
        String clause = "email = ?" +" AND " +"password = ?";
        String[] args = {email,password};
        Cursor cursor  =db.query(CustomerTable.NAME,null,clause,args,null,null,null);
        CustomerCursor foodDBCursor = new CustomerCursor(cursor);
        try {
            foodDBCursor.moveToFirst();
            while(!foodDBCursor.isAfterLast())
            {
                customer = foodDBCursor.getCustomer();
                foodDBCursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return customer;
    }

    public void addCustomer(Customer customer)
    {
        ContentValues cv = new ContentValues();
        cv.put(CustomerTable.Cols.EMAIL, customer.getEmail());
        cv.put(CustomerTable.Cols.PASSWORD, customer.getPassword());
        cv.put(CustomerTable.Cols.USERID, customer.getUserid());
        db.insert(CustomerTable.NAME,null,cv);
    }
}
