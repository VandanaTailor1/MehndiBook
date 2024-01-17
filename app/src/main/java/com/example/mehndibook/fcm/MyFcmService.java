package com.example.mehndibook.fcm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.mehndibook.R;
import com.example.mehndibook.view.NotificationActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFcmService extends FirebaseMessagingService {
    Notification notification;
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        String titile=message.getNotification().getTitle();
          String body=message.getNotification().getBody();
        Intent intent = new Intent(this, NotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification=new NotificationCompat.Builder(this,"FCM_CHANNEL_ID")
                 .setSmallIcon(R.drawable.notification)
                 .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.notification))
                 .setContentTitle(titile)
                 .setContentText(body)
                 .setContentIntent(pendingIntent)
                 .setAutoCancel(true)
                 .build();
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(1002, notification);
        super.onMessageReceived(message);
    }
}
