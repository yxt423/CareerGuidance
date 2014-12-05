package com.careerguidance.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.careerguidance.DBLayout.AndroidDatabaseManager;
import com.careerguidance.R;
import com.careerguidance.activity.helperActivity.GalleryActivity;
import com.careerguidance.activity.helperActivity.SelectionActivity;
import com.careerguidance.activity.helperActivity.VideoActivity;
import com.careerguidance.adapter.StableArrayAdapter;
import com.careerguidance.utility.Utility;

import java.util.ArrayList;

public class UniversityInfoActivity extends Activity {

    TextView universityNameView = null;
    String universityName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_info);

        // set university name on page title
        universityNameView = (TextView) findViewById(R.id.university_name);
        Intent intent = getIntent();
        universityName = intent.getStringExtra("university name");
        universityNameView.setText(universityName);

        // create the listview
        String[] values = new String[] {"More Pictures", "Watch Videos", "More Resources"};
        final ListView listview = (ListView) findViewById(R.id.listview);
        final ArrayList<String> list = new ArrayList<String>();

        int totalHeight = 0;
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }

        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intent = new Intent(getBaseContext(), GalleryActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(getBaseContext(), VideoActivity.class);
                        intent.putExtra("videoNo", R.raw.qi_li_xiang);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(getBaseContext(), SelectionActivity.class);
                        intent.putExtra("pageTitle", "More Resources");
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        });
        Utility.setListViewFixedHeight(listview);
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
