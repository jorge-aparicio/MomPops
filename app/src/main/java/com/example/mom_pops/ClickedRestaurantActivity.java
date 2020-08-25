package com.example.mom_pops;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.MutableContextWrapper;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class ClickedRestaurantActivity extends AppCompatActivity {
    public boolean active;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        active = true;
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_clicked_restaurant);

        FrameLayout container = findViewById(R.id.itemContainer);
        int counter = 0;
        int height = 0;
        while (counter < 100) {
            MenuItem item = new MenuItem(getApplicationContext(), this, "Cheeseburger " + counter, "$3.99", "This is your basic Cheeseburger.", "1000 Calories", "Mom and Pops Best Restaurant");
            item.setPadding(height);
            container.addView(item);
            height += 600;
            counter++;
        }
    }

    public boolean getActive() {
        return active;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1)
            System.out.println("here");
    }

    @Override
    protected void onResume() {
        super.onResume();
        active = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        active = false;
    }
}