package com.androidbegin.notificationtutorial;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

public class TestSrvice extends Service {

	public TestSrvice() {
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.e("service", "onCreate()");
		CustomNotification();
	}
	
	public void CustomNotification() {
		// Using RemoteViews to bind custom layouts into Notification
		RemoteViews remoteViews = new RemoteViews(getPackageName(),
				R.layout.customnotification);
		
		// Set Notification Title
		String strtitle = getString(R.string.customnotificationtitle);
		// Set Notification Text
		String strtext = getString(R.string.customnotificationtext);
		
		// Open NotificationView Class on Notification Click
		Intent intent = new Intent(this, NotificationView.class);
		// Send data to NotificationView Class
		intent.putExtra("title", strtitle);
		intent.putExtra("text", strtext);
		// Open NotificationView.java Activity
		PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent,
				PendingIntent.FLAG_UPDATE_CURRENT);

		NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
				// Set Icon
				.setSmallIcon(R.drawable.logosmall)
				// Set Ticker Message
				.setTicker(getString(R.string.customnotificationticker))
				// Dismiss Notification
				.setAutoCancel(true)
				// Set PendingIntent into Notification
				.setContentIntent(pIntent)
				// Set RemoteViews into Notification
				.setContent(remoteViews);

		// Locate and set the Image into customnotificationtext.xml ImageViews
		remoteViews.setImageViewResource(R.id.imagenotileft,R.drawable.ic_launcher);
		remoteViews.setImageViewResource(R.id.imagenotiright,R.drawable.androidhappy);
		
		// Locate and set the Text into customnotificationtext.xml TextViews
		remoteViews.setTextViewText(R.id.title,getString(R.string.customnotificationtitle));
		remoteViews.setTextViewText(R.id.text,getString(R.string.customnotificationtext));
		
		// Create Notification Manager
		NotificationManager notificationmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		// Build Notification with Notification Manager
		startForeground(1, builder.build());
	}

}
