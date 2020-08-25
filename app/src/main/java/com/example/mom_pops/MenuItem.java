package com.example.mom_pops;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.File;
import java.io.FileWriter;


public class MenuItem extends ConstraintLayout {
    private boolean starIsSelected;
    private boolean cartIsSelected;

    private TextView item_name_text_view;
    private TextView item_price_text_view;
    private TextView item_description_text_view;
    private TextView item_calories_text_view;
    private ImageView starIcon;
    private ImageView cartIcon;

    private String itemName;
    private String itemPrice;
    private String itemCal;
    private String itemDescription;
    private String itemRestaurant;

    private ConstraintLayout topLayout;
    private View sol;

    private Activity activity;
    private Context context;

    // won't allow menu item popup to show more than once
    private boolean popup_started;

    public MenuItem(Context contex, Activity current, String name, String price, String description, String calories, String restaurant) {
        super(contex);
        activity = current;
        context = contex;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        sol = inflater.inflate(R.layout.menu_item, this);

        item_name_text_view = sol.findViewById(R.id.textView4);
        item_price_text_view = sol.findViewById(R.id.textView16);
        item_description_text_view = sol.findViewById(R.id.textView8);
        item_calories_text_view = sol.findViewById(R.id.textView3);

        itemName = name;
        itemPrice = price;
        itemDescription = description;
        itemCal = calories;
        itemRestaurant = restaurant;

        setMenuItem(itemName, itemPrice, itemDescription, itemCal);

        // setting on click listener for star icon
        // behavior: updates icon appearance and favorites/unfavorites menu item depending on current status
        starIcon = sol.findViewById(R.id.imageView2);
        starIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = activity.getApplicationContext();
                boolean isSelected = getStarBoolean();
                if (isSelected) {
                    starIcon.setImageResource(R.mipmap.unselected_star);
                    Toast.makeText(context, "Removing item from favorites...", Toast.LENGTH_SHORT).show();
                } else {
                    starIcon.setImageResource(R.mipmap.selected_star);
                    Toast.makeText(context, "Adding item to favorites...", Toast.LENGTH_SHORT).show();
                }

                setStarBoolean(!isSelected);
                Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show();
            }
        });

        cartIcon = sol.findViewById(R.id.imageView7);
        cartIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isSelected = getCartBoolean();
                if (isSelected) {
                    cartIcon.setImageResource(R.mipmap.unselected_cart);
                    Toast.makeText(context, "Removing item from cart...", Toast.LENGTH_SHORT).show();
                } else {
                    cartIcon.setImageResource(R.mipmap.selected_cart);
                    addCartItemToInternalStorage(itemName + "~" + itemPrice
                        + "~" + itemDescription + "~" + itemCal + "~" + itemRestaurant + "\n");
                }
            }
        });

        topLayout = sol.findViewById(R.id.topLayout);
        topLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, Popup.class);
                intent.putExtra("popupType", "item");
                intent.putExtra("itemName", itemName);
                intent.putExtra("itemPrice", itemPrice);
                intent.putExtra("itemCal", itemCal);
                intent.putExtra("itemDescription", itemDescription);
                intent.putExtra("starIcon", getStarBoolean());
                intent.putExtra("cartIcon", getCartBoolean());
                intent.putExtra("itemRestaurant", itemRestaurant);
                activity.startActivity(intent);
                popup_started = true;
            }
        });
    }

    public void addCartItemToInternalStorage(String body) {
        Toast.makeText(context, "Adding item to cart...", Toast.LENGTH_SHORT).show();
        File file = new File(context.getFilesDir(), "CartItems.txt");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                Toast.makeText(context, "Error...", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
                return;
            }
        }

        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(body);
            fw.flush();
            fw.close();
            setCartBoolean(!getCartBoolean());
            Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "Error...", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public boolean getStarBoolean() {
        return starIsSelected;
    }

    public boolean getCartBoolean() {
        return cartIsSelected;
    }

    public void setMenuItem(String name, String price, String description, String calories) {
        item_name_text_view.setText(name);
        item_price_text_view.setText(price);
        item_description_text_view.setText(description);
        item_calories_text_view.setText(calories);
    }

    public void setMenuItemName(String name) {
        item_name_text_view.setText(name);
    }

    public void setMenuItemPrice(String price) {
        item_price_text_view.setText(price);
    }

    public void setMenuItemDescription(String description) {
        item_description_text_view.setText(description);
    }

    public void setStarBoolean(boolean update) {
        starIsSelected = update;
    }

    public void setCartBoolean(boolean update) {
        cartIsSelected = update;
    }

    public void setPadding(int height) {
        setPadding(0, height, 0, 0);
    }
}
