
package com.example.foodorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivityTablet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tablet);

        FrameLayout rvRestaurants = findViewById(R.id.container_restaurant);
        FrameLayout rvFoods = findViewById(R.id.container_food);
        FrameLayout rvCart = findViewById(R.id.container_cart);

        FragmentManager fm = getSupportFragmentManager();
        FrameLayoutViewModel flModel = new FrameLayoutViewModel(rvRestaurants,rvFoods,rvCart,fm);
        CartViewModel cModel = new CartViewModel();

        flModel.ReplaceRestaurantFrag(new RestaurantMenu());
        flModel.ReplaceFoodFrag(new FoodMenu());
        flModel.ReplaceFoodFrag(new fragmen_cart());
    }

    private void startNewActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}