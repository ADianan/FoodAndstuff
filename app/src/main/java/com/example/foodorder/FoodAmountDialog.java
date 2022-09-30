package com.example.foodorder;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodAmountDialog  {
    private AlertDialog.Builder builder;
    private AlertDialog dialog;

    public void createNew(Context context,CartViewModel model,Food food)
    {
        builder = new AlertDialog.Builder(context);
        LayoutInflater li = LayoutInflater.from(context);
        View contact = li.inflate(R.layout.amountpopup,null);
        Button add = contact.findViewById(R.id.add);
        Button remove = contact.findViewById(R.id.remove);
        Button Next = contact.findViewById(R.id.next);
        EditText amount = contact.findViewById(R.id.amount);
        TextView name = contact.findViewById(R.id.foodPopName);
        ImageView imageView = contact.findViewById(R.id.imageView);
        imageView.setImageResource(food.getImage());
        name.setText(food.getName());
        builder.setView(contact);
        dialog = builder.create();
        dialog.show();;
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String am = String.valueOf(amount.getText());
                int newAmount = Integer.parseInt(am) +1;
                amount.setText(String.valueOf(newAmount));
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String am = String.valueOf(amount.getText());
                if(Integer.parseInt(am) > 0)
                {

                    int newAmount = Integer.parseInt(am) -1;
                    amount.setText(String.valueOf(newAmount));
                }
            }
        });
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.cart.add(new Cart(food,Integer.parseInt(String.valueOf(amount.getText()))));
                dialog.dismiss();
            }
        });



    }
}
