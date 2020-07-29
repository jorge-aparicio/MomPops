package com.example.mom_pops;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class EditRestaurantMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_edit_restaurant_menu);
    }
}