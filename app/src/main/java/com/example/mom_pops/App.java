package com.example.mom_pops;

import android.app.Application;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;

public class App extends Application {
    // global variables across app
    private HashSet<String> set = new HashSet<String>();
    private boolean cartLoaded = false;

    public HashSet<String> getCartSet() {
        return set;
    }

    public boolean getCartLoaded() {
        return cartLoaded;
    }

    public void setCartLoaded(boolean bool) {
        cartLoaded = bool;
    }
}
