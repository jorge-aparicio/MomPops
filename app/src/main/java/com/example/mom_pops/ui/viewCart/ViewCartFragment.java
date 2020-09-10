package com.example.mom_pops.ui.viewCart;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.mom_pops.App;
import com.example.mom_pops.MenuItem;
import com.example.mom_pops.Popup;
import com.example.mom_pops.R;

import java.text.DecimalFormat;
import java.util.HashSet;

// TODO: add support and create activity for view carts, save cart functionality
public class ViewCartFragment extends Fragment {
    // contains total price of cart items
    double total_price;

    // refers to the whole cart page's xml
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // getting the view cart page's xml
        root = inflater.inflate(R.layout.activity_view_cart, container, false);

        // layout in the view cart page where the cart items will be placed
        LinearLayout layout = root.findViewById(R.id.cartFrame);

        // placing cart items onto layout
        HashSet<String> items = App.getCartSet();
        int index = 0;
        for (String item : items) {
            String[] fields = item.split("~");
            MenuItem menuItem = new MenuItem(getActivity(), fields[0], fields[1], fields[2], fields[3], fields[4], true);
            layout.addView(menuItem, index++);
            total_price += Double.parseDouble(fields[1]);
        }

        // add whitespace at the bottom of the linear layout
        Space space = new Space(getActivity().getApplicationContext());
        space.setMinimumHeight(30);
        layout.addView(space);

        // updates cart total textview
        if (total_price != 0)
            ((TextView) root.findViewById(R.id.viewCartTotal)).setText("Cart Total: $" + new DecimalFormat("#.##").format(total_price) + " + tax");

        /*
            assigning on click listener to clear cart button
            behavior: displays confirmation popup to user only if the cart isn't empty
         */
        Button clearCart = root.findViewById(R.id.clearCart);
        clearCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // do nothing if a popup is already displaying
                if (((App) getActivity().getApplication()).getPopupDisplaying())
                    return;

                LinearLayout cartContainer = root.findViewById(R.id.cartFrame);
                if (cartContainer.getChildCount() == 0) {
                    Toast.makeText(getActivity().getApplicationContext(), "Cart is already empty.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // setting popupDisplaying to true so the user can't spam multiple popups
                ((App) getActivity().getApplication()).setPopupDisplaying(true);

                // creates popup asking for confirmation
                Intent intent = new Intent(getActivity(), Popup.class);
                intent.putExtra("popupType", "confirmation");
                intent.putExtra("message", "Clear Cart?");
                startActivityForResult(intent, 1);
            }
        });

        // return cart page xml
        return root;
    }

    /*
        Function gets called after coming back from an activity that was started on this fragment.
        As of right now, it's only used to determine whether the user wants to delete their cart based off of
        what they selected in the confirmation popup.
        behavior: nothing if the user cancels, deletes cart items entirely if user confirms
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // reverts popupDisplaying status back to false
        ((App) getActivity().getApplication()).setPopupDisplaying(false);

        // user left the popup by clicking outside of it instead of pressing cancel
        if (data == null)
            return;

        super.onActivityResult(requestCode, resultCode, data);

        // clear cart request code
        if (requestCode == 1) {
            boolean result = data.getBooleanExtra("result", false);

            // if user wants to delete cart
            if (result) {
                Toast.makeText(getActivity().getApplicationContext(), "Clearing Cart...", Toast.LENGTH_SHORT).show();
                try {
                    LinearLayout cartContainer = root.findViewById(R.id.cartFrame);
                    cartContainer.removeAllViews();
                    App.getCartSet().clear();
                    ((TextView) root.findViewById(R.id.viewCartTotal)).setText("Cart Total: $0.00");
                    Toast.makeText(getActivity().getApplicationContext(), "Success!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getActivity().getApplicationContext(), "Error...", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }
    }
}