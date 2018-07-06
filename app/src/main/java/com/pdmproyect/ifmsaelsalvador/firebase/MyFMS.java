package com.pdmproyect.ifmsaelsalvador.firebase;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.media.MediaBrowserCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.pdmproyect.ifmsaelsalvador.R;
import com.pdmproyect.ifmsaelsalvador.activities.ProfileActivity;
import com.pdmproyect.ifmsaelsalvador.activities.SignUpActivity;

import java.util.Random;

public class MyFMS extends FirebaseMessagingService {

    NotificationManager notificationManager = null;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        int notificationId = new Random().nextInt(60000);
        Intent notificationIntent=new Intent(this, ProfileActivity.class);
        notificationIntent.setAction(".activities.ProfileActivity");
        notificationIntent.addCategory(Intent.CATEGORY_DEFAULT);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent= PendingIntent.getActivity(
                this, 1,notificationIntent, PendingIntent.FLAG_ONE_SHOT
        );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setupChannels();
        }

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "1")
                .setSmallIcon(R.drawable.ic_launcher_foreground)  //a resource for your custom small icon
                .setContentTitle(remoteMessage.getData().get("title")) //the "title" value you sent in your notification
                .setContentText(remoteMessage.getData().get("message")) //ditto
                .setAutoCancel(true)  //dismisses the notification on click
                .setContentIntent(pendingIntent)
                .setSound(defaultSoundUri);
        notificationManager.notify(notificationId /* ID of notification */, notificationBuilder.build());

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setupChannels() {
        NotificationChannel adminChannel;
        CharSequence adminChannelName = "project";
        String adminChannelDescription = "new project", channelID = "1";
        adminChannel = new NotificationChannel(channelID,   adminChannelName, NotificationManager.IMPORTANCE_HIGH);
        adminChannel.setDescription(adminChannelDescription);
        adminChannel.enableLights(true);
        adminChannel.setLightColor(Color.RED);
        adminChannel.enableVibration(true);
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(adminChannel);
        }
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
//        algo para mandar el token al server
    }


}
