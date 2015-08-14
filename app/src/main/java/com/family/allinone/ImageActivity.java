package com.family.allinone;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import android.os.Handler;
import android.widget.ProgressBar;


public class ImageActivity extends Activity implements View.OnClickListener{

    ImageView sampleImage,frameImage;
    FrameLayout frameLayout;
    Thread loadThread;
    Handler handler;
    ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        sampleImage = (ImageView)findViewById(R.id.imageView);
        frameImage = (ImageView)findViewById(R.id.frame);
        frameLayout = (FrameLayout)findViewById(R.id.frameLayout);
        ImageButton imageBtn1 = (ImageButton)findViewById(R.id.imageButton);
        ImageButton imageBtn2 = (ImageButton)findViewById(R.id.imageButton2);
        ImageButton imageBtn3 = (ImageButton)findViewById(R.id.imageButton3);
        ImageButton imageBtn4 = (ImageButton)findViewById(R.id.imageButton4);

        imageBtn1.setOnClickListener(this);
        imageBtn2.setOnClickListener(this);
        imageBtn3.setOnClickListener(this);
        imageBtn4.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image, menu);
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

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.imageButton:
                sampleImage.setImageResource(R.drawable.pic1);
                frameImage.bringToFront();
                break;
            case R.id.imageButton2:
                sampleImage.setImageResource(R.drawable.pic2);
                frameImage.bringToFront();
                break;
            case R.id.imageButton3:
                sampleImage.setImageResource(R.drawable.pic3);
                frameImage.bringToFront();
                break;
            case R.id.imageButton4:
                sampleImage.setImageResource(R.drawable.pic4);
                frameImage.bringToFront();
                break;
        }
    }
}
