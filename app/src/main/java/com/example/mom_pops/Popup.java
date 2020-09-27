package com.example.mom_pops;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

//TODO: better styling for the popups, add image support
public class Popup extends Activity {
    private String itemName, itemCal, itemPrice, itemDescription, itemRestaurant;
    private TextView itemName_textView, itemPrice_textView, itemCal_textView, itemDescription_textView, itemRestaurant_textView;

    private ImageView itemImgPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // won't allow users to spam the popup
        ((App) getApplication()).setPopupDisplaying(true);

        // adjusting size of activity
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        // get the popup type
        Intent intent = getIntent();
        String popupType = intent.getStringExtra("popupType");

        switch (popupType) {
            case "item":    // menu item popup
                setContentView(R.layout.activity_menu_item_popup);
                getWindow().setLayout((int) (width * .85), (int) (height * .85));

                // get information about menu item
                itemName = intent.getStringExtra("itemName");
                itemPrice = intent.getStringExtra("itemPrice");
                itemCal = intent.getStringExtra("itemCal");
                itemDescription = intent.getStringExtra("itemDescription");
                itemRestaurant = intent.getStringExtra("itemRestaurant");

                // get text views that correspond to item's fields
                itemName_textView = findViewById(R.id.itemName);
                itemCal_textView = findViewById(R.id.itemCalories);
                itemPrice_textView = findViewById(R.id.itemPrice);
                itemDescription_textView = findViewById(R.id.itemDescription);
                itemRestaurant_textView = findViewById(R.id.itemRestaurant);

                // set menu item information
                itemName_textView.setText(itemName);
                itemPrice_textView.setText(itemPrice);
                itemCal_textView.setText(itemCal);
                itemDescription_textView.setText(itemDescription);
                itemRestaurant_textView.setText(itemRestaurant);

                /*
                    setting onclick listener for close button
                    behavior: closes the activity, goes back to the clicked restaurant page
                 */
                Button closeButton = findViewById(R.id.closeButton);
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent res = new Intent();
                        setResult(1, res);

                        // reverts popupDisplaying status back to false
                        ((App) getApplication()).setPopupDisplaying(false);

                        // close popup
                        finish();
                    }
                });

                break;

            case "confirmation":    // confirmation popup
                setContentView(R.layout.activity_confirmation_pop);
                getWindow().setLayout((int) (width * .65), (int) (height * .40));

                // gets confirmation text for popup
                String message = intent.getStringExtra("message");

                // applies confirmation text to the only textview in the popup
                TextView message_textView = findViewById(R.id.confirmation_text);
                message_textView.setText(message);

                /*
                    setting onclick listener for cancel button
                    behavior: will close the activity with a return boolean of false, meaning do nothing after closing
                 */
                Button cancelButton = findViewById(R.id.cancel_button);
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent res = new Intent();
                        res.putExtra("result", false);
                        setResult(1, res);

                        finish();
                    }
                });

                /*
                    setting onclick listener for confirm button
                    behavior: will close the activity with a return boolean of true, meaning do whatever was asked for confirmation
                 */
                Button confirmButton = findViewById(R.id.confirm_button);
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

    /*
        will update the app that there's no popup being displayed
     */
    @Override
    public void onDestroy() {
        super.onDestroy();

        // denotes that a popup isn't being displayed anymore
        ((App) getApplication()).setPopupDisplaying(false);
    }
}
