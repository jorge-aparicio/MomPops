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
    public static LinkedHashSet<String> getCartSet() {
        return set;
    }
}
