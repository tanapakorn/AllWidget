package com.family.allinone;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;


public class HorizontalActivity extends ActionBarActivity {

    HorizontalScrollView scroll;
    LinearLayout linearLayout;
    int currentImage = 0;
    private static final int IMAGE_1 = 0;
    private static final int IMAGE_2 = 1;
    private static final int IMAGE_3 = 2;
    private static final int IMAGE_4 = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal);

        scroll = (HorizontalScrollView)findViewById(R.id.horizontalScrollView);
        linearLayout = (LinearLayout)findViewById(R.id.linearLayout);
        scroll.setOnTouchListener(new View.OnTouchListener() {
            float sX,eX;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scroll.onInterceptTouchEvent(event);
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    sX = event.getX();
                }else if (event.getAction() == MotionEvent.ACTION_UP) {
                    eX = event.getX();
                    if(eX < sX ){
                        if(currentImage == IMAGE_1 ) {
                            scroll.smoothScrollTo(600 , 0);
                            currentImage = IMAGE_2;
                        }else if(currentImage == IMAGE_2) {
                            scroll.smoothScrollTo(1200 , 0);
                            currentImage = IMAGE_3;
                        }else if(currentImage == IMAGE_3) {
                            scroll.smoothScrollTo(1800 , 0);
                            currentImage = IMAGE_4;
                        }
                    }else if(eX > sX){
                        if(currentImage == IMAGE_4 ) {
                            scroll.smoothScrollTo(1200 , 0);
                            currentImage = IMAGE_3;
                        }else if(currentImage == IMAGE_3) {
                            scroll.smoothScrollTo(600 , 0);
                            currentImage = IMAGE_2;
                        }else if(currentImage == IMAGE_2) {
                            scroll.smoothScrollTo(0 , 0);
                            currentImage = IMAGE_1;
                        }
                    }
                }
                return false;
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_horizontal, menu);
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

