package com.example.foodorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Food> list = new ArrayList<Food>();//get food from database
        List<Food> rand = new ArrayList<Food>();
        for(int i = 0;i<20;i++)
        {
            list.add(new Food("food"+i,"foodDescription"+i,Double.valueOf(i+1),0));
        }
        CartViewModel cart = new CartViewModel();
        Button but1 = findViewById(R.id.button);
        Button but2 = findViewById(R.id.button2);
        Button but3 = findViewById(R.id.button3);
        FragmentManager fm = getSupportFragmentManager();
        ButtonViewModel model = new ButtonViewModel(but1,but2,but3, fm);
        model.ReplaceFrag(new home(list, model, cart));

    }
}