package com.family.allinone;

import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;


public class RadioActivity extends ActionBarActivity {

    TextView sampleText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);

        sampleText = (TextView)findViewById(R.id.sampleText);
        RadioGroup gravityGroup = (RadioGroup)findViewById(R.id.group1);
        gravityGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.gravityLeft:
                        sampleText.setGravity(Gravity.LEFT);
                        break;
                    case R.id.gravityCenter:
                        sampleText.setGravity(Gravity.CENTER);
                        break;
                    case R.id.gravityRight:
                        sampleText.setGravity(Gravity.RIGHT);
                        break;
                }
            }
        });
        RadioGroup sizeGroup = (RadioGroup)findViewById(R.id.group2);
        sizeGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.sizeSmall:
                        sampleText.setTextScaleX(0.33f);
                        break;
                    case R.id.sizeMedium:
                        sampleText.setTextScaleX(0.66f);
                        break;
                    case R.id.sizeBig:
                        sampleText.setTextScaleX(1.0f);
                        break;
                }
            }
        });
        RadioGroup styleGroup = (RadioGroup)findViewById(R.id.group3);
        styleGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.styleNormal:
                        sampleText.setTypeface(null, Typeface.NORMAL);
                        break;
                    case R.id.styleBold:
                        sampleText.setTypeface(null, Typeface.BOLD);
                        break;
                    case R.id.styleItalic:
                        sampleText.setTypeface(null, Typeface.ITALIC);
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_radio, menu);
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
