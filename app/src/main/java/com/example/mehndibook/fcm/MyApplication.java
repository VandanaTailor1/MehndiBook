package com.example.mehndibook.fcm;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        getNotification();
        super.onCreate();
    }

    public void getNotification(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            NotificationChannel channel = new NotificationChannel("FCM_CHANNEL_ID", "FCM Channel", NotificationManager.IMPORTANCE_HIGH);

            // Set any additional properties for the channel
            // For example, you can set the channel description and LED color
            channel.setDescription("FCM Channel Description");
            channel.setLightColor(Color.GREEN);
//            channel.enableVibration(vibra);
            // Register the channel with the system NotificationManager
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);







        }
    }
}
