package com.example.mom_pops;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MyFavorites extends AppCompatActivity {
    private RestaurantsAdapter restaurant_adapter_favs;
    private RecyclerView recycle_view_restaurants_favs;
    private List<Restaurant> restaurant_list_favs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favorites);

        recycle_view_restaurants_favs = (RecyclerView) findViewById(R.id.recycle_restaurant_list_favs);
        restaurant_adapter_favs = new RestaurantsAdapter(restaurant_list_favs);
        RecyclerView.LayoutManager restaurant_layout_manager_favs = new LinearLayoutManager(getApplicationContext());
        recycle_view_restaurants_favs.setLayoutManager(restaurant_layout_manager_favs);
        recycle_view_restaurants_favs.setItemAnimator(new DefaultItemAnimator());
        recycle_view_restaurants_favs.setAdapter(restaurant_adapter_favs);

        addRestaurantDataFavs();
    }

    /**
     * Adds data to restaurant list views for favorites list
     */
    public void addRestaurantDataFavs() {
        Restaurant restaurant = new Restaurant("McDonalds", "Fast Food", "$", "92837163", "213B Baker Street, London", "7 miles away", R.drawable.ic_launcher_background);
        restaurant_list_favs.add(restaurant);

        restaurant = new Restaurant("King", "Fast", "$$$", "12353434", "213B Baker Forward, London", "10 miles away", R.drawable.ic_launcher_background);
        restaurant_list_favs.add(restaurant);

        restaurant = new Restaurant("Queen", "Fast", "$$$", "12353434", "213B Baker Forward, London", "10 miles away", R.drawable.ic_launcher_background);
        restaurant_list_favs.add(restaurant);
    }
}