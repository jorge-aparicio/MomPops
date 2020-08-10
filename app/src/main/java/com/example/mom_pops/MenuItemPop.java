package com.example.mom_pops;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

public class MenuItemPop extends Activity {
    private boolean cartIsSelected;
    private boolean starIsSelected;

    private String itemName;
    private String itemCal;
    private String itemPrice;
    private String itemDescription;
    private ImageView itemImgPath;
    private ImageView starIcon;
    private ImageView cartIcon;

    private TextView itemName_textView;
    private TextView itemPrice_textView;
    private TextView itemCal_textView;
    private TextView itemDescription_textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu_item_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .8));

        // get information on menu item
        Intent intent = getIntent();
        itemName = intent.getStringExtra("itemName");
        itemPrice = intent.getStringExtra("itemPrice");
        itemCal = intent.getStringExtra("itemCal");
        itemDescription = intent.getStringExtra("itemDescription");
        cartIsSelected = intent.getBooleanExtra("cartIcon", false);
        starIsSelected = intent.getBooleanExtra("starIcon", false);

        // setting info to menu popup
        itemName_textView = findViewById(R.id.itemName);
        itemCal_textView = findViewById(R.id.itemCalories);
        itemPrice_textView = findViewById(R.id.itemPrice);
        itemDescription_textView = findViewById(R.id.itemDescription);


        itemName_textView.setText(itemName);
        itemPrice_textView.setText(itemPrice);
        itemCal_textView.setText(itemCal);
        itemDescription_textView.setText(itemDescription);

        Button closeButton = findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("here", "here");
                setResult(RESULT_OK, intent);

                // close popup
                finish();
            }
        });


        starIcon = findViewById(R.id.starIcon);
        starIcon.setImageResource(intent.getBooleanExtra("starIcon", false) ? R.mipmap.selected_star : R.mipmap.unselected_star);
        starIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (starIsSelected) {
                    starIcon.setImageResource(R.mipmap.unselected_star);
                    Toast.makeText(getApplicationContext(), "Removing item from favorites...", Toast.LENGTH_SHORT).show();
                } else {
                    starIcon.setImageResource(R.mipmap.selected_star);
                    Toast.makeText(getApplicationContext(), "Adding item to favorites...", Toast.LENGTH_SHORT).show();
                }

                starIsSelected = !starIsSelected;
                Toast.makeText(getApplication(), "Success!", Toast.LENGTH_SHORT).show();
            }
        });

        cartIcon = findViewById(R.id.cartIcon);
        cartIcon.setImageResource(intent.getBooleanExtra("cartIcon", false) ? R.mipmap.selected_cart : R.mipmap.unselected_cart);
        cartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cartIsSelected) {
                    cartIcon.setImageResource(R.mipmap.unselected_star);
                    Toast.makeText(getApplicationContext(), "Removing item from favorites...", Toast.LENGTH_SHORT).show();
                } else {
                    cartIcon.setImageResource(R.mipmap.selected_star);
                    Toast.makeText(getApplicationContext(), "Adding item to favorites...", Toast.LENGTH_SHORT).show();
                }

                cartIsSelected = !cartIsSelected;
                Toast.makeText(getApplication(), "Success!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
