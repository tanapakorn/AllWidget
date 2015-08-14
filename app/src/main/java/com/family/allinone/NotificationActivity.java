package com.family.allinone;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class NotificationActivity extends Activity {

    NotificationCompat.Builder notifyBuilder;
    NotificationManager notifyManager;
    int progressStatus = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        Button notifyBtn = (Button)findViewById(R.id.notifyBtn);
        Button notifyProgressBtn = (Button)findViewById(R.id.notifyProgressBtn);

        notifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NotificationActivity.this,"you've sent an email",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(NotificationActivity.this,MainActivity.class);
                PendingIntent p = PendingIntent.getActivity(NotificationActivity.this,
                        0, i, 0);

                notifyBuilder = new NotificationCompat.Builder(NotificationActivity.this)
                        .setTicker("New Mail !!")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("New Mail !!")
                        .setContentText("You've received mail from someone")
                        .setContentIntent(p)
                        .setAutoCancel(true);
                Notification notification = notifyBuilder.build();
                notifyManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                notifyManager.notify(0,notification);
            }
        });

        notifyProgressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NotificationActivity.this,"Downloading Files",Toast.LENGTH_SHORT).show();
                notifyBuilder = new NotificationCompat.Builder(NotificationActivity.this)
                        .setTicker("Downloading Files")
                        .setContentText("Downloading in progress . . .")
                        .setSmallIcon(R.mipmap.ic_launcher);
                notifyManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(progressStatus < 100){
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            progressStatus += 1;
                            notifyBuilder.setProgress(30,progressStatus,false);
                            Notification notification = notifyBuilder.build();
                            notifyManager.notify(0,notification);
                        }
                        progressStatus = 0;
                        notifyBuilder.setContentText("Download Complete");
                        notifyBuilder.setProgress(0, progressStatus, false);
                        Notification notification = notifyBuilder.build();
                        notifyManager.notify(0,notification);
                    }
                });
                thread.start();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_notification, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.reverse_in_left, R.anim.reverse_out_right);
    }
}
