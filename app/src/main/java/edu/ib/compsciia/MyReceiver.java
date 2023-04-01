package edu.ib.compsciia;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;


import android.content.Context;
import android.content.Intent;

import edu.ib.compsciia.businesslogic.LifeFormManager;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = intent.getParcelableExtra("notification");
        int id = intent.getIntExtra("notification_id", 0);
        notificationManager.notify(id, notification);
        LifeFormManager.getManager().ScheduleNotificationAgain(id);
    }
}