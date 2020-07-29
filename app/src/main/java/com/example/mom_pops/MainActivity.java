package com.example.mom_pops;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button spin_the_wheel_button;

    //variables for the restaurant list display
    private RecyclerView recycle_view_restaurants;
    private RestaurantsAdapter restaurant_adapter;
    private List<Restaurant> restaurant_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        //load restaurant lister
        recycle_view_restaurants = (RecyclerView) findViewById(R.id.recycle_restaurant_list);
        restaurant_adapter = new RestaurantsAdapter(restaurant_list);
        RecyclerView.LayoutManager restaurant_layout_manager = new LinearLayoutManager(getApplicationContext());
        recycle_view_restaurants.setLayoutManager(restaurant_layout_manager);
        recycle_view_restaurants.setItemAnimator(new DefaultItemAnimator());
        recycle_view_restaurants.setAdapter(restaurant_adapter);
        addRestaurantData();

        //navigate from home page to spin the wheel page
        spin_the_wheel_button = (Button) findViewById(R.id.btn_spin_the_wheel);
        spin_the_wheel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_spin_the_wheel();
            }
        });
    }

    /**
     * Opens spin the wheel page
     */
    public void open_spin_the_wheel() {
        Intent intent = new Intent(this, SpinTheWheel.class);
        startActivity(intent);
    }

    /**
     * Adds data to restaurant list views
     */
    public void addRestaurantData() {
        Restaurant restaurant = new Restaurant("McDonalds", "Fast Food", "$", "92837163", "213B Baker Street, London", "7 miles away", R.drawable.ic_dashboard_black_24dp);
        restaurant_list.add(restaurant);

        restaurant = new Restaurant("King", "Fast", "$$$", "12353434", "213B Baker Forward, London", "10 miles away", R.drawable.ic_launcher_background);
        restaurant_list.add(restaurant);

        restaurant = new Restaurant("King", "Fast", "$$$", "12353434", "213B Baker Forward, London", "10 miles away", R.drawable.ic_launcher_background);
        restaurant_list.add(restaurant);

        restaurant = new Restaurant("King", "Fast", "$$$", "12353434", "213B Baker Forward, London", "10 miles away", R.drawable.ic_launcher_background);
        restaurant_list.add(restaurant);

        restaurant = new Restaurant("King", "Fast", "$$$", "12353434", "213B Baker Forward, London", "10 miles away", R.drawable.ic_launcher_background);
        restaurant_list.add(restaurant);

        restaurant = new Restaurant("King", "Fast", "$$$", "12353434", "213B Baker Forward, London", "10 miles away", R.drawable.ic_launcher_background);
        restaurant_list.add(restaurant);
    }
}