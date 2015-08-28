package com.family.allinone;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


public class ActionbarActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actionbar);

        Button button = (Button)findViewById(R.id.button);
        ImageView imageView = (ImageView)findViewById(R.id.pic3);
        registerForContextMenu(imageView);
        registerForContextMenu(button);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = getMenuInflater();
        switch (v.getId()){
            case R.id.pic3:
                menuInflater.inflate(R.menu.context_menu_image,menu);
                break;
            case R.id.button:
                menuInflater.inflate(R.menu.context_menu_button,menu);
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        TextView textView = (TextView)findViewById(R.id.dynamic_text);
        switch (item.getItemId()){
            case R.id.item1:
                textView.setText("Item 1 Selected");
                return true;
            case R.id.item2:
                textView.setText("Item 2 Selected");
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
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
        }else if (id == R.id.action_search) {
            return true;
        }else if (id == R.id.action_copy) {
            return true;
        }else if (id == R.id.action_past) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
