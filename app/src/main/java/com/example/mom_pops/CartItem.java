package com.example.mom_pops;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class CartItem extends ConstraintLayout {
    private Activity activity;
    private View cartItem_xml;

    private TextView item_name_text_view;
    private TextView item_price_text_view;
    private TextView item_calories_text_view;
    private TextView item_description_text_view;
    private TextView item_restaurant_text_view;

    private String item_name;
    private String item_price;
    private String item_description;
    private String item_calories;
    private String item_restaurant;

    public CartItem(Context context, AttributeSet attrs) {
        super(context, attrs);

        // getting the activity
        activity = (Activity) getContext();

        // get attributes assigned to menu item in xml
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.menuItem, 0, 0);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        cartItem_xml = inflater.inflate(R.layout.cart_item, this, true);

        // find text views in cart item xml
        item_name_text_view = cartItem_xml.findViewById(R.id.itemName);
        item_price_text_view = cartItem_xml.findViewById(R.id.itemPrice);
        item_description_text_view = cartItem_xml.findViewById(R.id.itemDescription);
        item_calories_text_view = cartItem_xml.findViewById(R.id.itemCalories);
        item_restaurant_text_view = cartItem_xml.findViewById(R.id.itemRestaurant);

        // string values of cart item attributes
        item_name = attributes.getString(R.styleable.menuItem_item_name);
        item_price = attributes.getString(R.styleable.menuItem_item_price);
        item_description = attributes.getString(R.styleable.menuItem_item_description);
        item_calories = attributes.getString(R.styleable.menuItem_item_calories);
        item_restaurant = attributes.getString(R.styleable.menuItem_item_restaurant);

        // recycle attributes, apply text to xml
        attributes.recycle();
        setCartItem(item_name, item_price, item_description, item_calories, item_restaurant);

        // onClick listener for x icon deletes cart item
        ImageView x_icon = cartItem_xml.findViewById(R.id.x_icon);
        x_icon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("here");
                ((ViewManager)cartItem_xml.getParent()).removeView(cartItem_xml);
            }
        });

        // onClick listener for cart item, displays popup with enlarged/expanded information
        ConstraintLayout topLayout = cartItem_xml.findViewById(R.id.topLayout);
        topLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, Popup.class);
                intent.putExtra("popupType", "item");
                intent.putExtra("itemName", item_name);
                intent.putExtra("itemDescription", item_description);
                intent.putExtra("itemCal", item_calories);
                intent.putExtra("itemPrice", item_price);
                intent.putExtra("itemRestaurant", item_restaurant);
                activity.startActivity(intent);
            }
        });

    }

    public void setCartItem(String name, String price, String description, String calories, String restaurant) {
        item_name_text_view.setText(name);
        item_price_text_view.setText(price);
        item_description_text_view.setText(description);
        item_calories_text_view.setText(calories);
        item_restaurant_text_view.setText(restaurant);
    }
}
