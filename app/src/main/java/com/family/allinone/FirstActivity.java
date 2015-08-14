package com.family.allinone;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.Serializable;
import java.text.Format;

public class FirstActivity extends ActionBarActivity {

    final static int REQUEST_FOR_RESULT = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        final EditText editName = (EditText)findViewById(R.id.editName);
        final EditText editTel = (EditText)findViewById(R.id.editTel);
        Button sendData = (Button)findViewById(R.id.sendDataBtn);
        Button sendBundle = (Button)findViewById(R.id.sendAsBundleBtn);
        Button sendObject = (Button)findViewById(R.id.sendObjBtn);
        Button requestBtn = (Button)findViewById(R.id.requestBtn);

        sendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getName = editName.getText().toString();
                String getTel = editTel.getText().toString();
                String phone = "";
                try {
                    phone = "" + getTel.substring(0, 3) + "-"
                            + getTel.substring(3, 6) + "-"
                            + getTel.substring(6, 10);
                }catch (Exception e){
                    phone = "unknown";
                }


                Intent secondActivity = new Intent(FirstActivity.this,SecondActivity.class);
                secondActivity.putExtra("postName",getName);
                secondActivity.putExtra("postTel",phone);
                startActivity(secondActivity);
            }
        });

        sendBundle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getName = editName.getText().toString()+"  byBundle";
                String getTel = editTel.getText().toString();
                String phone = "";
                try {
                    phone = "" + getTel.substring(0, 3) + "-"
                            + getTel.substring(3, 6) + "-"
                            + getTel.substring(6, 10)+"  byBundle";
                }catch (Exception e){
                    phone = "unknown";
                }
                Bundle bundle = new Bundle();
                bundle.putString("postName",getName);
                bundle.putString("postTel", phone);

                Intent secondActivity = new Intent(FirstActivity.this,SecondActivity.class);
                secondActivity.putExtras(bundle);
                startActivity(secondActivity);
            }
        });
        sendObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getName = editName.getText().toString()+" byObject";
                String getTel = editTel.getText().toString();
                String phone = "";
                try {
                    phone = "" + getTel.substring(0, 3) + "-"
                            + getTel.substring(3, 6) + "-"
                            + getTel.substring(6, 10)+" byObject";
                }catch (Exception e){
                    phone = "unknown";
                }
                MyUser mUser = new MyUser();
                mUser.setName(getName);
                mUser.setTel(phone);

                Intent secondActivity = new Intent(FirstActivity.this,SecondActivity.class);
                secondActivity.putExtra("MyUser", mUser);
                startActivity(secondActivity);
            }
        });
        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondActivity = new Intent(FirstActivity.this,SecondActivity.class);
                startActivityForResult(secondActivity,REQUEST_FOR_RESULT);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_FOR_RESULT && resultCode == RESULT_OK){
            Toast.makeText(this,data.getStringExtra("str"),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_, menu);
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

class MyUser implements Serializable{
    String mName;
    String mTel;

    public String getTel() {
        return mTel;
    }

    public void setTel(String mTel) {
        this.mTel = mTel;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }
}
