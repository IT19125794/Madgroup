package com.example.broadcastproj;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class BackgroundService extends IntentService {


    /**
     * @param name
     * @deprecated
     */
    public BackgroundService(String name) {
        super("BackgroundService");
    }

    public static void startAction(Context context) {

        Intent intent = new Intent(context, BackgroundService.class);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        if (intent != null) {

            for (int i = 0; i < 5; i++) {

                Intent localBroadcastIntent = new Intent(MainActivity.BROADCAST_ACTION);
                localBroadcastIntent.putExtra("value", "Broadcast " + (i + 1));

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sendBroadcast(localBroadcastIntent);
            }

        }
    }

}
