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
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MenuItemPop extends Activity {
    private String itemName;
    private String itemCal;
    private String itemPrice;
    private String itemDescription;
    private ImageView itemImgPath;

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
                // close popup
                finish();
            }
        });
    }
}
