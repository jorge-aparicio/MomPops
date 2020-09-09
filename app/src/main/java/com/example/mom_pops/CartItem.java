package com.example.mom_pops;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.text.DecimalFormat;

//TODO: update cart item to have a favorite icon, share to friend functionality
public class CartItem extends ConstraintLayout {
    private LayoutInflater inflater;
    private Context context;
    public View cartItem_xml;
    private View parent;

    // holds values of textview fields in cart item
    private String itemName, itemPrice, itemDescription, itemCal, itemRestaurant, itemString;

    public CartItem(Context itemContext, LayoutInflater inflate, View itemParent, String name, String price, String description, String calories, String restaurant) {
        super(itemContext);
        inflater = inflate;
        context = itemContext;
        parent = itemParent;

        // getting xml of cart item
        cartItem_xml = inflater.inflate(R.layout.cart_item, null);

        // sets all cart item fields
        setCartItem(name, price, description, calories, restaurant);

        /*
         onClick listener for x icon deletes cart item
         behavior: removes the cart item entirely from the users cart
        */
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

        /*
            onClick listener for cart item, displays popup with enlarged/expanded information
         */
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

    /*

     */
    public void setCartItem(String name, String price, String description, String calories, String restaurant) {
        itemName = name;
        itemPrice = price;
        itemDescription = description;
        itemCal = calories;
        itemRestaurant = restaurant;

        itemString = itemName + "~" + itemPrice + "~" + itemDescription + "~" + itemCal + "~" + itemRestaurant;

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
