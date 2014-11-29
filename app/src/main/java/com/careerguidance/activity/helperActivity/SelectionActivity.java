package com.careerguidance.activity.helperActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.careerguidance.R;
import com.careerguidance.adapter.StableArrayAdapter;

import java.util.ArrayList;

/**
 * Show activities what only contains a list of options and a title.
 * This activity is reused for location selection, gender selection, etc.
 */
public class SelectionActivity extends Activity {

    String pageTitle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        Intent intent = getIntent();
        pageTitle = intent.getStringExtra("pageTitle");

        TextView textView = (TextView) findViewById(R.id.selection_title);
        textView.setText(pageTitle);


        final ListView listview = (ListView) findViewById(R.id.listview);
        String[] values = getSelectionValues();

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }

        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                finish();
            }
        });
    }

    public String[] getSelectionValues() {
        if (pageTitle.equals("Gender")) {
            return new String[] {"Male", "Female"};
        } else if (pageTitle.equals("Location")) {
            return new String[] {"Use current location", "United States", "Kenya", "China"};
        } else if (pageTitle.equals("Grades")) {
            return new String[] {"Math", "Science", "Language Arts", "Social Studies", "History", "Add new one"};
        } else if (pageTitle.equals("Interests")) {
            return new String[] {"Reading", "Sports", "Add new one"};
        } else if (pageTitle.equals("More Resources")) {
            return new String[] {"Official Website", "Wiki page", "Search in Google"};
        } else {
            return new String[]{};
        }
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
