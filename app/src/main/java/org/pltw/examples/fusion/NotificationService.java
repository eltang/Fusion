package org.pltw.examples.fusion;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by erictang on 5/21/17.
 */

public class NotificationService extends IntentService {

    public static boolean notifyBatterySaver = false;
    public static boolean notifyBatteryLevel = false;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public NotificationService() {
        super("NotificationService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

//        if (notifyBatteryLevel) {
//            BatteryManager bm = (BatteryManager) getSystemService(BATTERY_SERVICE);
//            int batLevel = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
//
//            NotificationCompat.Builder mBuilder =
//                    new NotificationCompat.Builder(this)
//                            .setSmallIcon(R.mipmap.ic_launcher)
//                            .setContentTitle("Fusion Status Update")
//                            .setContentText("The current battery level is " + batLevel + "%.")
//                            .setAutoCancel(true);
//
//            Intent resultIntent = new Intent(this, MainActivity.class);
//
//            NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//            mNotifyMgr.notify(1, mBuilder.build());
//        }

        if (notifyBatterySaver) {
            PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
            boolean savingPower = pm.isPowerSaveMode();

            String s = "Battery Saver is currently ";
            if (savingPower)
                s += "on!\n";
            else
                s += "off. Turn it on to extend your battery life.\n\n";

            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("Fusion Status Update")
                            .setContentText(s)
                            .setAutoCancel(true);

            Intent resultIntent = new Intent(this, MainActivity.class);

            NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            mNotifyMgr.notify(1, mBuilder.build());
        }
    }
}
