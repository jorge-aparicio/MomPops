package com.example.mom_pops;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;


public class MenuItem extends ConstraintLayout {
    private TextView item_name_text_view;
    private TextView item_price_text_view;
    private TextView item_category_text_view;
    private TextView item_description_text_view;

    public MenuItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        // get attributes assigned to menu item in xml
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.menuItem, 0, 0);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View sol = inflater.inflate(R.layout.menu_item, this, true);


        item_name_text_view = sol.findViewById(R.id.textView4);
        item_price_text_view = sol.findViewById(R.id.textView7);
        item_category_text_view = sol.findViewById(R.id.textView16);
        item_description_text_view = sol.findViewById(R.id.textView17);

        System.out.println(attributes.getString(R.styleable.menuItem_item_name));
        item_name_text_view.setText(attributes.getString(R.styleable.menuItem_item_name));
        item_price_text_view.setText(attributes.getString(R.styleable.menuItem_item_price));
        item_category_text_view.setText(attributes.getString(R.styleable.menuItem_item_category));
        item_description_text_view.setText(attributes.getString(R.styleable.menuItem_item_description));

        attributes.recycle();
    }
}
