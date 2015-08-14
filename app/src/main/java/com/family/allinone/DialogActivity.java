package com.family.allinone;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.family.allinone.R;

import java.util.ArrayList;

public class DialogActivity extends ActionBarActivity {

    final String[] games = new String[]{"Diablo3", "GuildWar2", "CABAL2"};
    ArrayList<Integer> arrGames = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = DialogActivity.this.getLayoutInflater();
        final AlertDialog loginDialog = new AlertDialog.Builder(this)
                .setView(inflater.inflate(R.layout.dialog_layout, null))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, "Canceled", Toast.LENGTH_SHORT).show();
                    }
                }).create();
        loginDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button button = loginDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText editName = (EditText)loginDialog.findViewById(R.id.editName);
                        EditText editPass = (EditText)loginDialog.findViewById(R.id.editPass);
                        String username = editName.getText().toString();
                        String password = editPass.getText().toString();

                        if(username.equals("admin") && password.equals("1234")){
                            Toast.makeText(DialogActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            loginDialog.dismiss();
                        }else{
                            Toast.makeText(DialogActivity.this, "failed"+username+password, Toast.LENGTH_SHORT).show();
                            loginDialog.cancel();
                        }
                    }
                });
            }
        });

        Button showDialogBtn = (Button)findViewById(R.id.showDialogBtn);
        Button showChoiceBtn = (Button)findViewById(R.id.showChoiceBtn);
        Button showSingleBtn = (Button)findViewById(R.id.showSingleBtn);
        Button showMultiBtn = (Button)findViewById(R.id.showMultiBtn);
        Button showCustomBtn = (Button)findViewById(R.id.showCustomBtn);
        showDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Simple Dialog");
                builder.setMessage("Do you like Android?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, "Yep", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this,"Nope",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
        showChoiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("What's your favorite Games");
                builder.setItems(games, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String s = "You selected \"" + games[which] + " \"";
                        Toast.makeText(DialogActivity.this, s, Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
        showSingleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("What's your favorite Games");
                builder.setSingleChoiceItems(games, 0, null);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ListView listView = ((AlertDialog) dialog).getListView();
                        int selectedItem = listView.getCheckedItemPosition();
                        String s = "You selected \"" + games[selectedItem] + " \"";
                        Toast.makeText(DialogActivity.this, s, Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
        showMultiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("What's your favorite Games");
                builder.setMultiChoiceItems(games, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            arrGames.add(which);
                        } else if (arrGames.contains(which)) {
                            arrGames.remove(Integer.valueOf(which));
                        }
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String s = "Selected Games :\n";
                        for (int i : arrGames) {
                            s += games[i];
                            s += "\n";
                        }
                        Toast.makeText(DialogActivity.this, s, Toast.LENGTH_SHORT).show();
                        arrGames = new ArrayList<Integer>();
                    }
                });
                builder.show();
            }
        });
        showCustomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginDialog.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dialog, menu);
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
