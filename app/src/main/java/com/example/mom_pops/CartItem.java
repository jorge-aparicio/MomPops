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

import java.text.DecimalFormat;
import java.util.zip.Inflater;

public class CartItem extends ConstraintLayout {
    private LayoutInflater inflater;
    private Context context;
    public View cartItem_xml;
    private View parent;

    private TextView item_name_text_view;
    private TextView item_price_text_view;
    private TextView item_calories_text_view;
    private TextView item_description_text_view;
    private TextView item_restaurant_text_view;

    private String itemName;
    private String itemPrice;
    private String itemDescription;
    private String itemCal;
    private String itemRestaurant;
    private String itemString;

    public CartItem(Context con, LayoutInflater inflate, View par, String name, String price, String description, String calories, String restaurant) {
        super(con);
        inflater = inflate;
        context = con;
        parent = par;

        itemName = name;
        itemPrice = price;
        itemDescription = description;
        itemCal = calories;
        itemRestaurant = restaurant;

        itemString = itemName + "~" + itemPrice + "~" + itemDescription + "~" + itemCal + "~" + itemRestaurant;

        cartItem_xml = inflater.inflate(R.layout.cart_item, null);

        setCartItem(name, price, description, calories, restaurant);

        // onClick listener for x icon deletes cart item
        ImageView x_icon = cartItem_xml.findViewById(R.id.x_icon);
        x_icon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                App.getCartSet().remove(itemString);
                TextView total_textview = parent.findViewById(R.id.viewCartTotal);
                String[] fields = total_textview.getText().toString().split(" ");
                double total = Double.parseDouble(fields[2].substring(1));
                total = total - Double.parseDouble(itemPrice);

                if (total == 0.00) {
                    total_textview.setText("Cart Total: $0.00");
                } else {
                    total_textview.setText("Cart Total: $" + new DecimalFormat("#.##").format(total) + " + tax");
                }

                ((ViewManager)cartItem_xml.getParent()).removeView(cartItem_xml);
            }
        });

        // onClick listener for cart item, displays popup with enlarged/expanded information
        ConstraintLayout topLayout = cartItem_xml.findViewById(R.id.topLayout);
        topLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Popup.class);
                intent.putExtra("popupType", "item");
                intent.putExtra("itemName", itemName);
                intent.putExtra("itemDescription", itemDescription);
                intent.putExtra("itemCal", itemCal);
                intent.putExtra("itemPrice", "$" + itemPrice);
                intent.putExtra("itemRestaurant", itemRestaurant);
                context.startActivity(intent);
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

    public View getItemXML() {
        return cartItem_xml;
    }
}
