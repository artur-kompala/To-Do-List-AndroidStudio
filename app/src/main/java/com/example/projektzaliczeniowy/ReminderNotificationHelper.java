package com.example.projektzaliczeniowy;

import static com.example.projektzaliczeniowy.MainActivity.items;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReminderNotificationHelper extends ContextWrapper {
    private static final String CHANNEL_ID = "reminder_channel_id";
    private static final String CHANNEL_NAME = "Reminder Channel";
    private NotificationManager notificationManager;

    public ReminderNotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel();
        }
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(true);
            channel.setLockscreenVisibility(NotificationCompat.VISIBILITY_PUBLIC);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void showReminderNotification(String reminderTitle, String reminderDescription) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.download)
                .setContentTitle(reminderTitle)
                .setContentText(reminderDescription)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        getNotificationManager().notify(0, builder.build());
    }

    private NotificationManager getNotificationManager() {
        if (notificationManager == null) {
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return notificationManager;
    }

    public void generateReminderNotificationForToday() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String today = dateFormat.format(new Date());


        List<Item> itemsWithTodayDate = new ArrayList<>();
        for (Item item : items) {
            if (item.getDateEnd().equals(today)) {
                itemsWithTodayDate.add(item);

            }
        }


        showReminderNotification("To Do List - Artur Kompała ISI 1","Masz "+itemsWithTodayDate.size()+" zadania na dzisiaj");



    }
}

