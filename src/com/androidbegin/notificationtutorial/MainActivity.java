package com.androidbegin.notificationtutorial;

import android.os.Bundle;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notificationmain);

		Button bnotify = (Button) findViewById(R.id.notification);

		Button bcustomnotify = (Button) findViewById(R.id.customnotification);

		bnotify.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				Notification();
			}
		});

		bcustomnotify.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this, TestSrvice.class);
				startService(intent);
			}
		});

	}

	public void Notification() {
		// Set Notification Title
		String strtitle = getString(R.string.notificationtitle);
		// Set Notification Text
		String strtext = getString(R.string.notificationtext);
		
		// Open NotificationView Class on Notification Click
		Intent intent = new Intent(this, NotificationView.class);
		// Send data to NotificationView Class
		intent.putExtra("title", strtitle);
		intent.putExtra("text", strtext);
		// Open NotificationView.java Activity
		PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent,
				PendingIntent.FLAG_UPDATE_CURRENT);
		
		//Create Notification using NotificationCompat.Builder 
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
				// Set Icon
				.setSmallIcon(R.drawable.logosmall)
				// Set Ticker Message
				.setTicker(getString(R.string.notificationticker))
				// Set Title
				.setContentTitle(getString(R.string.notificationtitle))
				// Set Text
				.setContentText(getString(R.string.notificationtext))
				// Add an Action Button below Notification
				.addAction(R.drawable.ic_launcher, "Action Button", pIntent)
				// Set PendingIntent into Notification
				.setContentIntent(pIntent)
				// Dismiss Notification
				.setAutoCancel(true);
		
		// Create Notification Manager
		NotificationManager notificationmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		// Build Notification with Notification Manager
		notificationmanager.notify(0, builder.build());

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
		notificationmanager.notify(0, builder.build());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
