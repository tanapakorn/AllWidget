package com.family.allinone;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.Random;


public class ProgressActivity extends ActionBarActivity {

    int[] picBox = {R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,R.drawable.pic4};
    ProgressBar mProgressBar,mProgressBar3;
    ProgressDialog progressDialog;
    private int mProgressStatus = 0;
    Handler handler = new Handler();
    Thread loadThread;
    Intent horizontalActivity;
    Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        progressDialog = new ProgressDialog(this);
        mProgressBar = (ProgressBar)findViewById(R.id.progressBar);
        mProgressBar3 = (ProgressBar)findViewById(R.id.progressBar3);

        final ImageView imageView = (ImageView)findViewById(R.id.imageView17);
        horizontalActivity = new Intent(getApplicationContext(),HorizontalActivity.class);
        mProgressBar.setVisibility(View.INVISIBLE);

        Button loadBtn = (Button)findViewById(R.id.loadBtn);
        Button runBtn = (Button)findViewById(R.id.runBtn);
        final Button progressDialogBtn = (Button)findViewById(R.id.progressDialogBtn);

        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);
                startActivity(horizontalActivity);
                finish();
            }
        });

        final Runnable runnable = new Runnable() {
            int i = 0;
            int progressStatus = 0;
            public void run() {

                while ( progressStatus < 100) {
                    progressStatus += doWork();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // Update the progress bar
                    handler.post(new Runnable() {
                        public void run() {
                            mProgressBar3.setProgress(progressStatus);
                            i+=3;
                            if(progressStatus>=100){
                                mProgressBar3.setVisibility(View.INVISIBLE);
                                imageView.setVisibility(View.VISIBLE);
                                Random r = new Random();
                                imageView.setImageResource(picBox[r.nextInt(4)]);
                            }
                        }
                    });
                }
                progressStatus = 0;
                i = 0;
            }
            private int doWork() {
                //Log.i("abc", String.valueOf(horizontalActivity));
                return i * 3;
            }
        };
        runBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setVisibility(View.INVISIBLE);
                mProgressBar3.setVisibility(View.VISIBLE);

                loadThread = new Thread(runnable);
                loadThread.start();
            }
        });
        progressDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setTitle("Progress Dialog.");
                progressDialog.setMessage("please wait...");
                progressDialog.setMax(15);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.show();
                Thread mThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (progressDialog.getProgress() < progressDialog.getMax()) {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            progressDialog.incrementProgressBy(1);
                        }
                        progressDialog.dismiss();
                        progressDialog.setProgress(0);
                    }
                });

                mThread.start();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_progress, menu);
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
