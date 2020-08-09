package com.example.mom_pops;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


public class MenuItem extends ConstraintLayout {
    private boolean starIsSelected;
    private boolean cartIsSelected;
    private boolean description_visibility;

    private TextView item_name_text_view;
    private TextView item_price_text_view;
    private TextView item_description_text_view;
    private TextView item_calories_text_view;
    private ImageView starIcon;
    private ImageView cartIcon;

    private ConstraintLayout topLayout;

    private Activity activity;

    public MenuItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        // setting the activity
        activity = (Activity) getContext();

        // get attributes assigned to menu item in xml
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.menuItem, 0, 0);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View sol = inflater.inflate(R.layout.menu_item, this, true);

        // TODO add a method to get current value of star and cart, maybe do it in activity?
        // TODO set up getters and setters

        item_name_text_view = sol.findViewById(R.id.textView4);
        item_price_text_view = sol.findViewById(R.id.textView16);
        item_description_text_view = sol.findViewById(R.id.textView8);
        item_calories_text_view = sol.findViewById(R.id.textView3);

        setMenuItem(attributes.getString(R.styleable.menuItem_item_name), attributes.getString(R.styleable.menuItem_item_price),
                attributes.getString(R.styleable.menuItem_item_description), attributes.getString(R.styleable.menuItem_item_calories));
        attributes.recycle();

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
                Context context = activity.getApplicationContext();
                boolean isSelected = getCartBoolean();
                if (isSelected) {
                    cartIcon.setImageResource(R.mipmap.unselected_cart);
                    Toast.makeText(context, "Removing item from cart...", Toast.LENGTH_SHORT).show();
                } else {
                    cartIcon.setImageResource(R.mipmap.selected_cart);
                    Toast.makeText(context, "Adding item to cart...", Toast.LENGTH_SHORT).show();
                }

                setCartBoolean(!isSelected);
                Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show();
            }
        });

        topLayout = sol.findViewById(R.id.topLayout);
        topLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.startActivity(new Intent(activity, MenuItemPop.class));
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

}
