package com.example.mom_pops;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class SpinTheWheel extends AppCompatActivity {
    //possible options for food types
    String[] food_type_options = new String []{"mcdonalds", "subway", "other"};

    //variables to be input
    float max_distance;
    String food_type;

    //components to accept input
    EditText max_distance_input;
    Spinner food_type_input;

    Button spin_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spin_the_wheel);

        //load the components from xml file
        max_distance_input = (EditText) findViewById(R.id.max_distance_input);
        food_type_input = (Spinner) findViewById(R.id.food_type_input);
        spin_button = (Button) findViewById(R.id.spin_wheel_button);

        //set the options for the dropdown spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, food_type_options);
        food_type_input.setAdapter(adapter);

        //when the spin button is pressed, get the inputs and get random restaurant from database
        spin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //convert max distance to float
                String distance_string = max_distance_input.getText().toString();
                try{
                    max_distance = new Float(distance_string);
                } catch (Exception e) {
                    System.out.println("The max distance input was not a decimal value");
                }

                //load the food type
                food_type = food_type_input.getSelectedItem().toString();

                System.out.println(max_distance);
                System.out.println(food_type);
            }
        });
    }
}