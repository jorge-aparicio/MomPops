package com.example.mom_pops;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;


public final class SingletonActivity extends AppCompatActivity {
    private static SingletonActivity INSTANCE = new SingletonActivity();

    private SingletonActivity () {}

    public static File getInstance() {
        return new File(INSTANCE.getFilesDir(), "CartItems.txt");
    }
}
