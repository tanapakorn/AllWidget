package com.family.allinone;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.widget.LinearLayout;



public class MenuActivity extends Activity implements View.OnClickListener {

    String[] buttonName = {"RadioButton","ImageView & Button","HorizontalScrollView","ProgressBar"
            ,"TakingPhotos","Toast","Activity","Dialog","Notification","Menu & ActionBar"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final LinearLayout scrollLayout = (LinearLayout)findViewById(R.id.scrollLayout);
        LinearLayout.LayoutParams marginParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT
                , LinearLayout.LayoutParams.WRAP_CONTENT);
        marginParams.setMargins(0,10,0,10);

        for(String i : buttonName){
            final Button button = new Button(this);
            button.setText(i);
            button.setBackgroundColor(Color.parseColor("#ffffffff"));
            button.setTextColor(Color.parseColor("#ffff99b3"));
            button.setWidth(scrollLayout.getWidth());
            button.setTextSize(20);
            button.setAllCaps(false);
            button.setLayoutParams(marginParams);
            button.setTag(i);
            button.setOnClickListener(this);
            scrollLayout.addView(button);
        }
    }

    @Override
    public void onBackPressed(){}

    @Override
    public void onClick(View v) {
        if(v.getTag().equals(buttonName[0])){
            Intent radioActivity = new Intent(getApplicationContext(),RadioActivity.class);
            startActivity(radioActivity);
        }else if(v.getTag().equals(buttonName[1])){
            Intent imageActivity = new Intent(getApplicationContext(),ImageActivity.class);
            startActivity(imageActivity);
        }else if(v.getTag().equals(buttonName[2])){
            Intent horizontalActivity = new Intent(getApplicationContext(),HorizontalActivity.class);
            startActivity(horizontalActivity);
        }else if(v.getTag().equals(buttonName[3])){
            Intent progressActivity = new Intent(getApplicationContext(),ProgressActivity.class);
            startActivity(progressActivity);
        }else if(v.getTag().equals(buttonName[4])){
            Intent photoActivity = new Intent(getApplicationContext(),PhotoActivity.class);
            startActivity(photoActivity);
        }else if(v.getTag().equals(buttonName[5])){
            Intent toastActivity = new Intent(getApplicationContext(),ToastActivity.class);
            startActivity(toastActivity);
        }else if(v.getTag().equals(buttonName[6])){
            Intent activityActivity = new Intent(getApplicationContext(),FirstActivity.class);
            startActivity(activityActivity);
        }else if(v.getTag().equals(buttonName[7])){
            Intent dialogActivity = new Intent(getApplicationContext(),DialogActivity.class);
            startActivity(dialogActivity);
        }else if(v.getTag().equals(buttonName[8])){
            Intent notificationActivity = new Intent(getApplicationContext(),NotificationActivity.class);
            startActivity(notificationActivity);
        }else if(v.getTag().equals(buttonName[9])){
            Intent actionBarActivity = new Intent(getApplicationContext(),ActionbarActivity.class);
            startActivity(actionBarActivity);
        }
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }
}
