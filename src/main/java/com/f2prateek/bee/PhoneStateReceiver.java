package com.f2prateek.bee;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.NotificationCompat;
import android.telephony.TelephonyManager;

import static android.content.Context.NOTIFICATION_SERVICE;
import static android.telephony.TelephonyManager.EXTRA_STATE_IDLE;
import static android.telephony.TelephonyManager.EXTRA_STATE_OFFHOOK;
import static android.telephony.TelephonyManager.EXTRA_STATE_RINGING;

public class PhoneStateReceiver extends BroadcastReceiver {
  private static final int NOTIFICATION_ID = 0;

  @Override public void onReceive(Context context, Intent intent) {
    NotificationManager notificationManager =
        (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

    String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

    if (EXTRA_STATE_IDLE.equals(state)) {
      notificationManager.cancel(NOTIFICATION_ID);
      return;
    }

    if (!EXTRA_STATE_RINGING.equals(state) && !EXTRA_STATE_OFFHOOK.equals(state)) {
      return; // Unknown state.
    }

    Resources resources = context.getResources();
    PendingIntent pendingIntent = launchMainActivity(context);

    NotificationCompat.Builder builder =
        new NotificationCompat.Builder(context).setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(resources.getString(R.string.app_name))
            .setContentText(resources.getString(R.string.notification_description))
            .setContentIntent(pendingIntent);

    notificationManager.notify(null, NOTIFICATION_ID, builder.build());
  }

  static PendingIntent launchMainActivity(Context context) {
    Intent intent = new Intent(context, MainActivity.class);
    return PendingIntent.getActivity(context, 0, intent, 0);
  }
}
