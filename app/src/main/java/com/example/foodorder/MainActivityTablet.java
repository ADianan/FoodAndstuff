
package com.example.foodorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
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

        FrameLayout rvRestaurants = findViewById(R.id.container_restaurant2);
        FrameLayout rvFoods = findViewById(R.id.container_food2);
        FrameLayout rvCart = findViewById(R.id.container_cart2);

        FragmentManager fm = getSupportFragmentManager();
        FrameLayoutViewModel flModel = new FrameLayoutViewModel(rvRestaurants,rvFoods,rvCart,fm);

        Button btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity();
            }
        });

        flModel.ReplaceRestaurantFrag(new RestaurantMenu());
        flModel.ReplaceCartFrag(new fragmen_cart());
    }

    private void startNewActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}