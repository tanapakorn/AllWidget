package com.family.allinone;

import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ToastActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);

        Button shortToast = (Button)findViewById(R.id.shortToastBtn);
        Button longToast = (Button)findViewById(R.id.longToastBtn);
        Button longerToast = (Button)findViewById(R.id.longerToastBtn);
        Button xmlToast = (Button)findViewById(R.id.xmlToastBtn);
        final Toast t = Toast.makeText(this,"This Is Short Toast",Toast.LENGTH_SHORT);
        final Toast toast = new Toast(this);


        shortToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast.cancel();
                t.setText("This Is Short Toast");
                t.setDuration(Toast.LENGTH_SHORT);
                t.show();
            }
        });
        longToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast.cancel();
                t.setText("This Is Long Toast");
                t.setDuration(Toast.LENGTH_LONG);
                t.show();
            }
        });
        longerToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast.cancel();
                t.setText("This Is 5-Sec Toast");
                t.setDuration(Toast.LENGTH_SHORT);
                new CountDownTimer(5000,1000){
                    @Override
                    public void onTick(long millisUntilFinished) {
                        t.show();
                    }
                    @Override
                    public void onFinish() {}
                }.start();
            }
        });
        xmlToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t.cancel();
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toast_layout_root));
                TextView textView = (TextView)layout.findViewById(R.id.textView);
                textView.setText("This Is Custom Toast");

                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(layout);
                toast.show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_toast, menu);
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
