package com.example.mom_pops;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import static java.security.AccessController.getContext;

public class ViewCartActivity extends AppCompatActivity {
    private Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        activity = this;
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_view_cart);

        Button clearCart = findViewById(R.id.clearCart);
        clearCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // creates popup asking for confirmation
                Intent intent = new Intent(activity, Popup.class);
                intent.putExtra("popupType", "confirmation");
                intent.putExtra("message", "Clear Cart?");
                startActivity(intent);

                FrameLayout cartContainer = findViewById(R.id.cartFrame);
                if (cartContainer.getChildCount() == 0) {
                    Toast.makeText(getApplicationContext(), "Cart is already empty.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Clearing Cart...", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT).show();
                    cartContainer.removeAllViews();
                }
            }
        });
    }
}