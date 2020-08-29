package com.example.mom_pops;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashSet;

import static java.security.AccessController.getContext;

public class ViewCartActivity extends AppCompatActivity {
    private Activity activity;
    private double total_price;

    private HashSet<String> cartSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        activity = this;
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_view_cart);

        cartSet = ((App) getApplication()).getCartSet();

        total_price = 0;
        File file = new File(getFilesDir(), "CartItems.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            FrameLayout layout = findViewById(R.id.cartFrame);
            int height = 0;
            while ( (line = br.readLine()) != null) {
                String[] arr = line.split("~");
                CartItem cartItem = new CartItem(getApplicationContext(), this, arr[0], arr[1],
                        arr[2], arr[3], arr[4]);
                cartItem.setPadding(0, height, 0, 0);
                total_price += Double.parseDouble(arr[1]);
                layout.addView(cartItem);
                height += 450;
            }

            HashSet<String> cartSet = ((App) getApplication()).getCartSet();
            for (String s : cartSet) {
                System.out.println(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (total_price != 0)
            ((TextView) findViewById(R.id.viewCartTotal)).setText("Cart Total: $" + new DecimalFormat("#.##").format(total_price) + " + tax");

        Button clearCart = findViewById(R.id.clearCart);
        clearCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FrameLayout cartContainer = findViewById(R.id.cartFrame);
                if (cartContainer.getChildCount() == 0) {
                    Toast.makeText(getApplicationContext(), "Cart is already empty.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // creates popup asking for confirmation
                Intent intent = new Intent(activity, Popup.class);
                intent.putExtra("popupType", "confirmation");
                intent.putExtra("message", "Clear Cart?");
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // clear cart request code
        if (requestCode == 1) {
            boolean result = data.getBooleanExtra("result", false);

            // if user wants to delete cart
            if (result) {
                Toast.makeText(getApplicationContext(), "Clearing Cart...", Toast.LENGTH_SHORT).show();
                try {
                    deleteFile("CartItems.txt");
                    FrameLayout cartContainer = findViewById(R.id.cartFrame);
                    cartContainer.removeAllViews();
                    cartSet.clear();
                    ((TextView) findViewById(R.id.viewCartTotal)).setText("Cart Total: $0.00");
                    Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error...", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }
    }


}