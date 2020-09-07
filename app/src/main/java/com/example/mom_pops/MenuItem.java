package com.example.mom_pops;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.LinkedHashSet;


public class MenuItem extends ConstraintLayout {
    private boolean starIsSelected;
    private boolean cartIsSelected;
    private String itemString;

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
    private LinkedHashSet<String> cartSet;

    // won't allow menu item popup to show more than once
    private boolean popup_started;

    public MenuItem(Context contex, Activity current, String name, String price, String description, String calories, String restaurant) {
        super(contex);
        activity = current;
        context = contex;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        sol = inflater.inflate(R.layout.menu_item, this);
        cartSet = ((App) activity.getApplication()).getCartSet();

        itemName = name;
        itemPrice = price;
        itemDescription = description;
        itemCal = calories;
        itemRestaurant = restaurant;


        itemString = itemName + "~" + itemPrice + "~" + itemDescription + "~" + itemCal + "~" + itemRestaurant;
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
                    Toast.makeText(context, "Item added to favorites.", Toast.LENGTH_SHORT).show();
                } else {
                    starIcon.setImageResource(R.mipmap.selected_star);
                    Toast.makeText(context, "Item removed from favorites.", Toast.LENGTH_SHORT).show();
                }

                setStarBoolean(!isSelected);
            }
        });

        cartIcon = sol.findViewById(R.id.imageView7);
        if (cartSet.contains(itemString)) {
            cartIcon.setImageResource(R.mipmap.selected_cart);
            setCartBoolean(true);
        }

        cartIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isSelected = getCartBoolean();
                if (isSelected) {
                    cartSet.remove(itemString);
                    cartIcon.setImageResource(R.mipmap.unselected_cart);
                    Toast.makeText(context, "Item removed from cart.", Toast.LENGTH_SHORT).show();
                } else {
                    cartIcon.setImageResource(R.mipmap.selected_cart);
                    ((App) activity.getApplication()).getCartSet().add(itemString);
                    Toast.makeText(context, "Item added to cart.", Toast.LENGTH_SHORT).show();
                }

                setCartBoolean(!isSelected);
            }
        });

        topLayout = sol.findViewById(R.id.topLayout);
        topLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, Popup.class);
                intent.putExtra("popupType", "item");
                intent.putExtra("itemName", itemName);
                intent.putExtra("itemPrice", "$" + itemPrice);
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

    public boolean getStarBoolean() {
        return starIsSelected;
    }

    public boolean getCartBoolean() {
        return cartIsSelected;
    }

    public void setMenuItem(String name, String price, String description, String calories) {
        ((TextView) sol.findViewById(R.id.textView4)).setText(name);
        ((TextView) sol.findViewById(R.id.textView16)).setText("$" + price);
        ((TextView) sol.findViewById(R.id.textView8)).setText(description);
        ((TextView) sol.findViewById(R.id.textView3)).setText(calories);
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
