package com.example.mom_pops;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.File;

public class RemoveCartItemService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        File file = new File(getFilesDir(), "CartItems.txt");
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
