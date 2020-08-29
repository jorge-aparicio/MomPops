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

import org.w3c.dom.Text;

public class CartItem extends ConstraintLayout {
    private Activity activity;
    private Context context;
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

    public CartItem(Context con, Activity act, String name, String price, String description, String calories, String restaurant) {
        super(con);
        activity = act;
        context = con;

        item_name = name;
        item_price = price;
        item_description = description;
        item_calories = calories;
        item_restaurant = restaurant;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        cartItem_xml = inflater.inflate(R.layout.cart_item, this);

        setCartItem(name, price, description, calories, restaurant);

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
                intent.putExtra("itemPrice", "$" + item_price);
                intent.putExtra("itemRestaurant", item_restaurant);
                activity.startActivity(intent);
            }
        });
    }

    public void setCartItem(String name, String price, String description, String calories, String restaurant) {
        ((TextView) cartItem_xml.findViewById(R.id.itemName)).setText(name);
        ((TextView) cartItem_xml.findViewById(R.id.itemPrice)).setText("$" + price);
        ((TextView) cartItem_xml.findViewById(R.id.itemDescription)).setText(description);
        ((TextView) cartItem_xml.findViewById(R.id.itemCalories)).setText(calories);
        ((TextView) cartItem_xml.findViewById(R.id.itemRestaurant)).setText(restaurant);
    }
}
