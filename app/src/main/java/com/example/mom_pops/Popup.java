package com.example.mom_pops;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Popup extends Activity {
    private String itemName;
    private String itemCal;
    private String itemPrice;
    private String itemDescription;
    private String itemRestaurant;
    private ImageView itemImgPath;

    private TextView itemName_textView;
    private TextView itemPrice_textView;
    private TextView itemCal_textView;
    private TextView itemDescription_textView;
    private TextView itemRestaurant_textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        // get information on menu item
        Intent intent = getIntent();
        String popupType = intent.getStringExtra("popupType");
        switch (popupType) {
            case "item":
                setContentView(R.layout.activity_menu_item_popup);
                getWindow().setLayout((int) (width * .85), (int) (height * .85));
                itemName = intent.getStringExtra("itemName");
                itemPrice = intent.getStringExtra("itemPrice");
                itemCal = intent.getStringExtra("itemCal");
                itemDescription = intent.getStringExtra("itemDescription");
                itemRestaurant = intent.getStringExtra("itemRestaurant");

                // setting info to menu popup
                itemName_textView = findViewById(R.id.itemName);
                itemCal_textView = findViewById(R.id.itemCalories);
                itemPrice_textView = findViewById(R.id.itemPrice);
                itemDescription_textView = findViewById(R.id.itemDescription);
                itemRestaurant_textView = findViewById(R.id.itemRestaurant);


                itemName_textView.setText(itemName);
                itemPrice_textView.setText(itemPrice);
                itemCal_textView.setText(itemCal);
                itemDescription_textView.setText(itemDescription);
                itemRestaurant_textView.setText(itemRestaurant);

                Button closeButton = findViewById(R.id.closeButton);
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // close popup
                        finish();
                    }
                });

                break;

            case "confirmation":
                getWindow().setLayout((int) (width * .65), (int) (height * .40));
                setContentView(R.layout.activity_confirmation_pop);
                String message = intent.getStringExtra("message");
                TextView message_textView = findViewById(R.id.confirmation_text);
                message_textView.setText(message);

                Button cancelButton = findViewById(R.id.cancel_button);
                Button confirmButton = findViewById(R.id.confirm_button);
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent res = new Intent();
                        res.putExtra("result", false);
                        setResult(1, res);
                        finish();
                    }
                });

                confirmButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent res = new Intent();
                        res.putExtra("result", true);
                        setResult(1, res);
                        finish();
                    }
                });
        }
    }
}
