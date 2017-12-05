package com.example.we25.easycafe;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.we25.seohu.sensor.Sensor_Main;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

/**
 * Created by we25 on 2017-07-07.
 */

public class MessagingService extends FirebaseMessagingService {

    private  static final String TAG = "Test";
    private String main;
    private String msg;


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Map<String , String> data = remoteMessage.getData();
        SharedPreferences pref = getSharedPreferences("pref" , MODE_PRIVATE);
        if(data != null && pref.getBoolean("PushCheck", true)) {
            main = data.get("main");
            msg = data.get("contents");
            Log.d(TAG, "받기 성공");
            nofimake(main, msg);

        }
    }

    public void nofimake(String main , String msg){
        PendingIntent contentIntent = null;
        int notiID = 0;
        if( main.equals("쓰레기")){
            contentIntent = PendingIntent.getActivity(getApplicationContext(), 1,
                    new Intent(getApplicationContext(), Sensor_Main.class), 0);
            notiID = 1;
        }else if(main.equals("화장실")){
            contentIntent = PendingIntent.getActivity(getApplicationContext(), 2,
                    new Intent(getApplicationContext(), Sensor_Main.class), 0);
            notiID =2;
        }else if(main.equals("티슈")){
            contentIntent = PendingIntent.getActivity(getApplicationContext(), 3,
                    new Intent(getApplicationContext(), Sensor_Main.class), 0);
            notiID = 3;
        }


        //알림창 설정빌더
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext()).setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(main)
                .setContentText(msg)
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setVibrate(new long[]{1, 1000})
                .setPriority(Notification.PRIORITY_MAX);
        mBuilder.setDefaults(Notification.DEFAULT_ALL);


        NotificationManager notificationManager =
                (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder.setContentIntent(contentIntent);

        notificationManager.notify(notiID, mBuilder.build());
    }
}
