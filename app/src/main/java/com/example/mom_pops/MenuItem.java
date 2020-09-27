package com.example.mom_pops;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.text.DecimalFormat;
import java.util.LinkedHashSet;

// TODO LIST: give option to recommend to friends, add photo support for menu items, add database functionality for icons

public class MenuItem extends ConstraintLayout {
    // keeps track of state of icons
    private boolean starIsSelected;
    private boolean cartIsSelected;

    // string that represents menu item inside cart hashset
    private String itemString;

    // bottom left icons
    private ImageView starIcon;
    private ImageView cartIcon;

    // values of textview fields in menu item
    private String itemName, itemPrice, itemCal, itemDescription, itemRestaurant;

    // refers to constraint layout container for menu item
    private ConstraintLayout topLayout;

    // refers to the whole menu item layout
    private View menuItem_xml;

    // activity corresponding to where menu item will be placed
    private Activity activity;

    // context corresponding to where menu item will be placed
    private Context context;
    private LinkedHashSet<String> cartSet;

    // constructor for menu item, takes in all the fields required in order to append to layout in activity
    public MenuItem(Activity itemActivity, String name, String price, String description, String calories, String restaurant, boolean inCartPage) {
        super(itemActivity.getApplicationContext());

        activity = itemActivity;
        context = itemActivity.getApplicationContext();

        // inflates or "instantiates" the menu item's xml
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        menuItem_xml = inflater.inflate(R.layout.menu_item, this);

        // retrieving global hashset that keeps track of cart items
        cartSet = ((App) activity.getApplication()).getCartSet();

        // sets all menu item fields
        setMenuItemFields(name, price, description, calories, restaurant, inCartPage);

        /*
         setting on click listener for star icon
         behavior: updates icon appearance and favorites/unfavorites menu item depending on current status
         TODO: add database functionality in the future
        */
        starIcon = menuItem_xml.findViewById(R.id.starIcon);
        starIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = activity.getApplicationContext();
                boolean isSelected = getStarBoolean();

                // enters if star icon was in selected state
                if (isSelected) {
                    starIcon.setImageResource(R.mipmap.unselected_star);
                    Toast.makeText(context, "Item removed from favorites.", Toast.LENGTH_SHORT).show();
                } else { // item wasn't in selected state
                    starIcon.setImageResource(R.mipmap.selected_star);
                    Toast.makeText(context, "Item added to favorites.", Toast.LENGTH_SHORT).show();
                }

                // update status of star icon
                setStarBoolean(!isSelected);
            }
        });


        // updates the appearance of the icon to selected if it's in the cart hashset
        // Should be mentioned that the default status of the icon is to be unselected
        cartIcon = menuItem_xml.findViewById(R.id.cartIcon);
        if (cartSet.contains(itemString)) {
            cartIcon.setImageResource(R.mipmap.selected_cart);
            setCartBoolean(true);
        }

        /*
         setting on click listener for cart icon
         behavior: updates icon appearance and adds/removes menu item from the user's cart depending on current status
         TODO: add database functionality in the future
        */
        cartIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isSelected = getCartBoolean();
                if (isSelected) {
                    cartSet.remove(itemString);
                    cartIcon.setImageResource(R.mipmap.unselected_cart);

                    // remove cart item's xml and update total price if the user removed the item from the view cart page
                    LinearLayout container = activity.findViewById(R.id.cartFrame);
                    if (container != null) {
                        // gets the textview that displays the total price
                        TextView total_textview = activity.findViewById(R.id.viewCartTotal);

                        // reduces total price by the cost of the menu item
                        String[] fields = total_textview.getText().toString().split(" ");
                        double total = Double.parseDouble(fields[2].substring(1));
                        total = total - Double.parseDouble(itemPrice);

                        // update total price textview accordingly
                        if (total == 0.00) {
                            total_textview.setText("Cart Total: $0.00");
                        } else {
                            total_textview.setText("Cart Total: $" + new DecimalFormat("#.##").format(total) + " + tax");
                        }

                        // remove cart item from gui
                        container.removeView(menuItem_xml);
                    }

                    Toast.makeText(context, "Item removed from cart.", Toast.LENGTH_SHORT).show();
                } else {
                    cartIcon.setImageResource(R.mipmap.selected_cart);
                    ((App) activity.getApplication()).getCartSet().add(itemString);
                    Toast.makeText(context, "Item added to cart.", Toast.LENGTH_SHORT).show();
                }

                setCartBoolean(!isSelected);
            }
        });

        /*
         setting on click listener for menu item
         behavior: displays popup of menu item
         TODO: add better styling to the popup
        */
        topLayout = menuItem_xml.findViewById(R.id.topLayout);
        topLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                // do nothing if popup is already displaying
                if (((App) activity.getApplication()).getPopupDisplaying())
                    return;

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
            }
        });
    }

    /*
        sets the values of text related fields of menu item
     */
    public void setMenuItemFields(String name, String price, String description, String calories, String restaurant, boolean inCartPage) {
        itemName = name;
        itemPrice = price;
        itemDescription = description;
        itemCal = calories;
        itemRestaurant = restaurant;

        // id string that gets pushed to cart hash set if added to cart
        itemString = itemName + "~" + itemPrice + "~" + itemDescription + "~" + itemCal + "~" + itemRestaurant;

        // sets values of textviews
        ((TextView) menuItem_xml.findViewById(R.id.itemName)).setText(name);
        ((TextView) menuItem_xml.findViewById(R.id.itemPrice)).setText("$" + price);
        ((TextView) menuItem_xml.findViewById(R.id.itemDescription)).setText(description);
        ((TextView) menuItem_xml.findViewById(R.id.itemCalories)).setText(calories);
    }

    /*
        getters and setters down below
     */
    public boolean getStarBoolean() {
        return starIsSelected;
    }

    public boolean getCartBoolean() {
        return cartIsSelected;
    }

    public void setStarBoolean(boolean update) {
        starIsSelected = update;
    }

    public void setCartBoolean(boolean update) {
        cartIsSelected = update;
    }
}
