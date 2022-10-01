package com.example.foodorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.opencsv.CSVParserWriter;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private  static boolean ADDTODATABASE = false;
    //when adding new data please remove the old data sets
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(ADDTODATABASE == true)
        {
            ReadFood();
            ReadRestaurant();
        }

        CartViewModel cart = new CartViewModel();
        Button but1 = findViewById(R.id.button);
        Button but2 = findViewById(R.id.button2);
        Button but3 = findViewById(R.id.button3);
        FragmentManager fm = getSupportFragmentManager();
        ButtonViewModel model = new ButtonViewModel(but1,but2,but3, fm);
        model.ReplaceFrag(new home(model, cart));
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.ReplaceFrag(new home(model, cart));
            }
        });
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               model.ReplaceFrag(new RestaurantMenu(model));
            }
        });
        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.ReplaceFrag(new fragmen_cart(cart));
            }
        });


    }

    private void ReadFood()
    {
        try{
            List<Food> food = new ArrayList<>();
            FoodStore store = new FoodStore();
            store.load(this);
            InputStream stream = getAssets().open("FoodData.csv");
            CSVReader reader = new CSVReader(new InputStreamReader(stream));
            String [] nextline;
            String view = "";
            while((nextline = reader.readNext())!= null)
            {
                int image = getResources().getIdentifier(nextline[4],"drawable",getPackageName());
                double price = Double.valueOf(nextline[3]);
                food.add(new Food(nextline[0],nextline[1],nextline[2],price,image));
            }
            store.addFood(food);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void ReadRestaurant()
    {
        try{
            List<Restaurant> restaurantList = new ArrayList<>();
            RestaurantStore store = new RestaurantStore();
            store.load(this);

            InputStream stream = getAssets().open("RestaurantData.csv");
            CSVReader reader = new CSVReader(new InputStreamReader(stream));
            String [] nextline;
            while((nextline = reader.readNext())!= null)
            {
                restaurantList.add(new Restaurant(nextline[0],getResources().getIdentifier(nextline[1],"drawable",getPackageName())));
                Log.d("myapp", String.valueOf(getResources().getIdentifier(nextline[1],"drawable",getPackageName())));
            }
            store.addRestaurant(restaurantList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}