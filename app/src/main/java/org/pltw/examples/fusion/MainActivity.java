package org.pltw.examples.fusion;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();

    private TextView textView;
    private CheckBox batteryLevelCheckBox;
    private CheckBox batterySaverCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        batterySaverCheckBox = (CheckBox)findViewById(R.id.checkBox2);
        batteryLevelCheckBox = (CheckBox)findViewById(R.id.checkBox3);

        batterySaverCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (batterySaverCheckBox.isChecked())
                    NotificationService.notifyBatterySaver = true;
                else
                    NotificationService.notifyBatterySaver = false;
            }
        });

        batteryLevelCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (batteryLevelCheckBox.isChecked())
                    NotificationService.notifyBatteryLevel = true;
                else
                    NotificationService.notifyBatteryLevel = false;
            }
        });


        textView.setText("Select the notifications you would like to receive.");
        PendingIntent servicePendingIntent = PendingIntent.getService(this, 0, new Intent(this, NotificationService.class), PendingIntent.FLAG_CANCEL_CURRENT );

        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() +
                        10 * 1000, servicePendingIntent);
    }

}
