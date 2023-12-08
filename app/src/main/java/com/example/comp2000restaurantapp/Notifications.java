package com.example.comp2000restaurantapp;

import static android.content.Context.NOTIFICATION_SERVICE;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;

import androidx.core.app.NotificationCompat;

public class Notifications {

    public static void notification(Context context, String title, String body) {
        String chanelID = "CHANNEL_ID_NOTIFICATION";
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context.getApplicationContext(), chanelID);
        builder.setSmallIcon(R.drawable.ic_alert)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        NotificationChannel notificationChannel =
                notificationManager.getNotificationChannel(chanelID);
        if (notificationChannel == null) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            notificationChannel = new NotificationChannel(chanelID,
                    "some description", importance);
            notificationChannel.setLightColor(Color.GREEN);
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        notificationManager.notify(0, builder.build());
    }
}
