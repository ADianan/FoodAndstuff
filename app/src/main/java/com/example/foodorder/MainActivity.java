package com.example.foodorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Food> list = new ArrayList<Food>();
        for(int i = 0;i<20;i++)
        {
            list.add(new Food("food"+i,"foodDescription"+i,Double.valueOf(i+1),0));
        }
        FragmentManager fm = getSupportFragmentManager();
        home homefrag = (home) fm.findFragmentById(R.id.mainFragment);
        FragmentTransaction ft = fm.beginTransaction();
        if(homefrag == null )
        {
            homefrag = new home(list);
            ft.add(R.id.mainFragment,homefrag);
        }
        ft.commit();
    }
}