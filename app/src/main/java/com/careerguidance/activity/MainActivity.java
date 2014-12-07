package com.careerguidance.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.careerguidance.adapter.CareerGuidance;
import com.careerguidance.dblayout.AndroidDatabaseManager;
import com.careerguidance.R;

/**
 * The startup activity. show app cover with 2 buttons:
 * find a career match / see all career options.
 */
public class MainActivity extends Activity {
    private CareerGuidance careerGuidance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        careerGuidance = new CareerGuidance(getApplicationContext());
    }

    public void matchCareerButton(View v) {
        if (careerGuidance.userHasProfile()) {
            Intent intent = new Intent(getBaseContext(), MainTabHost.class);
            intent.putExtra("currentTab", 2);
            startActivity(intent);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("You don't have a profile yet.");
            builder.setMessage("We will help you find a career according to your profile information.");
            builder.setPositiveButton("Go Create A Profile!", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Intent intent = new Intent(getBaseContext(), MainTabHost.class);
                    intent.putExtra("currentTab", 2);
                    startActivity(intent);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    public void careerOptionsButton(View v) {
        Intent intent = new Intent(this, MainTabHost.class);
        intent.putExtra("currentTab", 0);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_admin_mode) {
            Intent intent = new Intent(this, AndroidDatabaseManager.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_db_testing) {
            Intent intent = new Intent(this, TestDBActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
