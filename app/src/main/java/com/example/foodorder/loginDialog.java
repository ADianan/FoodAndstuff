package com.example.foodorder;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

public class loginDialog {
    private AlertDialog.Builder builder;
    private AlertDialog dialog;

    public void customer(Context context)
    {
        MutabaleCart mcart = new ViewModelProvider((ViewModelStoreOwner) context, (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory()).get(MutabaleCart.class);
        builder = new AlertDialog.Builder(context);
        LayoutInflater li = LayoutInflater.from(context);
        View contact = li.inflate(R.layout.login_layout,null);
        Button sign = contact.findViewById(R.id.sign);
        TextView text = contact.findViewById(R.id.textView5);
        EditText password = contact.findViewById(R.id.password_toggle);
        EditText email = contact.findViewById(R.id.email);

        builder.setView(contact);
        dialog = builder.create();
        dialog.show();;
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = String.valueOf(password.getText());
                String userMail = String.valueOf(email.getText());
                if(userMail.contains("@"))
                {
                    CustomerStore store = new CustomerStore();
                    HistoryStore store1 = new HistoryStore();
                    store1.load(context);
                    store.load(context);
                    Customer customer = store.getCustomer(userMail,userName);
                    if(customer == null)
                    {
                        store.addCustomer(new Customer(userMail,userName,RandomIDGen.UserId()));
                        addCustomertoNutable(customer,store,userMail,userName,mcart);
                        addHistory(mcart.getMutableLiveData(),store1);
                        dialog.dismiss();
                    }
                    else
                    {
                        addCustomertoNutable(customer,store,userMail,userName,mcart);
                        dialog.dismiss();


                    }
                }
                else
                {
                    text.setText("Invalid Email");
                }
            }
        });
    }
    private void addCustomertoNutable(Customer customer,CustomerStore store,String userMail,String userName, MutabaleCart mcart)
    {
        customer = store.getCustomer(userMail,userName);
        CartViewModel model = mcart.getMutableLiveData();
        model.customer = customer;
        mcart.mutableLiveData.setValue(model);
    }
    private void addHistory(CartViewModel cart,HistoryStore store)
    {
        String order = RandomIDGen.orderID();
        String time = RandomIDGen.getTime();
        for(Cart i: cart.cart)
        {
            History history = new History(order,i,time);
            store.addOrder(history,cart.customer);
        }

    }
}
