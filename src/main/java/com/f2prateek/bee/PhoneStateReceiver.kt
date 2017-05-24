package com.f2prateek.bee

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.telephony.TelephonyManager
import android.telephony.TelephonyManager.*
import timber.log.Timber

class PhoneStateReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)

        if (EXTRA_STATE_IDLE == state) {
            notificationManager.cancel(NOTIFICATION_ID)
            return
        }

        if (EXTRA_STATE_RINGING != state && EXTRA_STATE_OFFHOOK != state) {
            Timber.e("Unknown state: %s", state)
            return
        }

        val resources = context.resources
        val pendingIntent = launchMainActivity(context)

        val builder = NotificationCompat.Builder(context).setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(resources.getString(R.string.app_name))
                .setContentText(resources.getString(R.string.notification_description))
                .setContentIntent(pendingIntent)

        notificationManager.notify(null, NOTIFICATION_ID, builder.build())
    }

    companion object {
        private val NOTIFICATION_ID = 0

        internal fun launchMainActivity(context: Context): PendingIntent {
            val intent = Intent(context, MainActivity::class.java)
            return PendingIntent.getActivity(context, 0, intent, 0)
        }
    }
}
