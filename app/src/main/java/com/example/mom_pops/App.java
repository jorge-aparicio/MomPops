package com.example.mom_pops;

import android.app.Application;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class App extends Application {
    // global variables across app
    private static LinkedHashSet<String> set = new LinkedHashSet<String>();
    private static boolean popupDisplaying = false;

    public static LinkedHashSet<String> getCartSet() {
        return set;
    }

    public static boolean getPopupDisplaying() {
        return popupDisplaying;
    }

    public static void setPopupDisplaying(boolean status) {
        popupDisplaying = status;
    }
}
