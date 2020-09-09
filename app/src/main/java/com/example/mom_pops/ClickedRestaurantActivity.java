package com.example.mom_pops;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

// TODO: show the clicked restaurant's data instead aka add database functionality
public class ClickedRestaurantActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_clicked_restaurant);

        // adds menu items to clicked restaurant page
        LinearLayout container = findViewById(R.id.itemContainer);
        int counter = 0;
        while (counter < 100) {
            MenuItem item = new MenuItem(getApplicationContext(), this, "Cheeseburger " + counter, "3.99", "This is your basic Cheeseburger.", "1000 Calories", "Mom and Pops Best Restaurant");
            container.addView(item, counter++);
        }
    }
}