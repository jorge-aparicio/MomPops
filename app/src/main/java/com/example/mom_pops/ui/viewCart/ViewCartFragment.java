package com.example.mom_pops.ui.viewCart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.mom_pops.App;
import com.example.mom_pops.Cart;
import com.example.mom_pops.CartItem;
import com.example.mom_pops.Popup;
import com.example.mom_pops.R;

import java.text.DecimalFormat;
import java.util.HashSet;

public class ViewCartFragment extends Fragment {
    double total_price;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.activity_view_cart, container, false);
        LinearLayout layout = root.findViewById(R.id.cartFrame);

        // placing cart items onto cart
        HashSet<String> items = App.getCartSet();
        int index = 0;
        for (String item : items) {
            String[] fields = item.split("~");
            CartItem cartItem = new CartItem(getActivity(), inflater, root, fields[0], fields[1], fields[2], fields[3], fields[4]);
            layout.addView(cartItem.cartItem_xml, index++);
            total_price += Double.parseDouble(fields[1]);
        }

        // updates cart total textview
        if (total_price != 0)
            ((TextView) root.findViewById(R.id.viewCartTotal)).setText("Cart Total: $" + new DecimalFormat("#.##").format(total_price) + " + tax");

        // assigning on click listener to clear cart button
        Button clearCart = root.findViewById(R.id.clearCart);
        clearCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout cartContainer = root.findViewById(R.id.cartFrame);
                if (cartContainer.getChildCount() == 0) {
                    Toast.makeText(getActivity().getApplicationContext(), "Cart is already empty.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // creates popup asking for confirmation
                Intent intent = new Intent(getActivity(), Popup.class);
                intent.putExtra("popupType", "confirmation");
                intent.putExtra("message", "Clear Cart?");
                startActivityForResult(intent, 1);
            }
        });


        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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