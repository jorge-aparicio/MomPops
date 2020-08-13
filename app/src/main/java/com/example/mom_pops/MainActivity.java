package com.example.mom_pops;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;
    private Cursor cursor;

    private Button spin_the_wheel_button;

    //variables for the restaurant list display
    private RecyclerView recycle_view_restaurants;
    private RestaurantsAdapter restaurant_adapter;
    private List<Restaurant> restaurant_list = new ArrayList<>();

    private ListView listView;


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

        mDBHelper = new DatabaseHelper(this);

        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }

        cursor = mDb.query(
                "Restaurants",
                null,
                null,
                null,
                null,
                null,
                null
        );

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
        Intent intent = new Intent(this, MyFavorites.class);
        startActivity(intent);
    }

    /**
     * Adds data to restaurant list views
     */
    public void addRestaurantData() {
        cursor.moveToFirst();
        Restaurant restaurant = new Restaurant(cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(7), cursor.getString(0), "7 miles away", R.drawable.ic_launcher_background);
        restaurant_list.add(restaurant);

        cursor.move(1);
        restaurant = new Restaurant(cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(7), cursor.getString(0), "7 miles away", R.drawable.ic_launcher_background);
        restaurant_list.add(restaurant);
    }
}