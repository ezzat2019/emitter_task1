package com.talabto.emitterbyezzat.services;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import androidx.annotation.Nullable;

import com.talabto.emitterbyezzat.MainActivity;
import com.talabto.emitterbyezzat.R;


public class MyForeService extends IntentService {
    private BroadcastReceiver myReciver;
    private IntentFilter intentFilter;


    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("ch1", "noti", NotificationManager.IMPORTANCE_MIN);
            notification = new Notification.Builder(getApplicationContext(), "ch1")
                    .setSmallIcon(R.mipmap.ic_launcher).setContentTitle("services of emiter app")
                    .build();

            notificationManager.createNotificationChannel(notificationChannel);
            startForeground(1, notification);

        } else {
            notification = new Notification.Builder(getApplicationContext())
                    .setSmallIcon(R.mipmap.ic_launcher).setContentTitle("services of middle app")
                    .build();


            notificationManager.notify(1, notification);
        }

        myReciver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                if (intent != null) {
                    if (intent.getAction().equals("com.talabto.middleappbyezzat.recive_from")) {

                        Intent intent1 = new Intent(getApplicationContext(), MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent1.setAction("show_dialog");
                        String user = intent.getStringExtra("user");
                        intent1.putExtra("user", user);

                        startActivity(intent1);


                    } else if (intent.getAction().equals("com.talabto.emitterbyezzat.secd_mid")) {


                        String res = intent.getStringExtra("mid_rec");
                        Intent intent1 = new Intent(getApplicationContext(), MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent1.setAction("show_res");
                        intent1.putExtra("response", res);
                        startActivity(intent1);



                    }
                }
            }
        };

        intentFilter = new IntentFilter();
        intentFilter.addAction("com.talabto.emitterbyezzat.recive");
        intentFilter.addAction("com.talabto.emitterbyezzat.secd_mid");


        registerReceiver(myReciver, intentFilter);
        return START_STICKY;
    }

    public MyForeService() {
        super("MyForeService");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReciver);

    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }

}