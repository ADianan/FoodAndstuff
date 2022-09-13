package com.example.foodorder;

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
        this.db = new FoodStoreDbHelper( // Open database
                context.getApplicationContext()
        ).getWritableDatabase();
    }
    public Customer getCustomer(String email,String password)
    {
        String clause = "WHERE email =" +"'" + email +"'" +" AND " +"password = " +  password;
        Cursor cursor  =db.query(CustomerTable.NAME,null,clause,null,null,null,null);
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
}
