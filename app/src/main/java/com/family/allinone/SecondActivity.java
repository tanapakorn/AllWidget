package com.family.allinone;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.family.allinone.R;

public class SecondActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final TextView getName = (TextView)findViewById(R.id.getNameData);
        final TextView getTel = (TextView)findViewById(R.id.getPhoneData);
        Button getObjectData = (Button)findViewById(R.id.getObjectData);
        Button sendBackData = (Button)findViewById(R.id.sendBackData);

        final Intent intent = getIntent();
        getName.setText(getName.getText()+intent.getStringExtra("postName"));
        getTel.setText(getTel.getText()+intent.getStringExtra("postTel"));

        getObjectData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    MyUser mUser = (MyUser) intent.getSerializableExtra("MyUser");
                    getName.setText("Name : " + mUser.getName());
                    getTel.setText("Tel.  " + mUser.getTel());
                } catch (Exception e) {
                    getName.setText("unknown");
                    getTel.setText("unknown");
                }
            }
        });
        sendBackData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("str", " It's work!! ");

                setResult(RESULT_OK, i);
                if(!intent.hasExtra("postName")){
                    finish();
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
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
}
