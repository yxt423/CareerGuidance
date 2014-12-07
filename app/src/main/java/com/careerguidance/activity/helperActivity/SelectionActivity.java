package com.careerguidance.activity.helperActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.careerguidance.adapter.CareerGuidance;
import com.careerguidance.dblayout.AndroidDatabaseManager;
import com.careerguidance.R;
import com.careerguidance.activity.TestDBActivity;
import com.careerguidance.utility.Utility;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Show activities what only contains a list of options and a title.
 * This activity is reused for location selection, gender selection, etc.
 */
public class SelectionActivity extends Activity {
    private CareerGuidance careerGuidance;

    ArrayAdapter<String> adapter = null;
    String pageTitle = null;
    String[] listValues = null;
    int functionNo = 0;  // 1 gender, 2 location

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        careerGuidance = new CareerGuidance(getApplicationContext());

        Bundle extras = getIntent().getExtras();
        functionNo = extras.getInt("function_no");
        dataInit();

        TextView pageTitleView = (TextView) findViewById(R.id.page_title);
        pageTitleView.setText(pageTitle);

        final ListView listview = (ListView) findViewById(R.id.listview);
        final ArrayList<String> list = new ArrayList<String>();
        Collections.addAll(list, listValues);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                Intent intent = new Intent();
                if (functionNo == 2 && position == 0) {
                    intent.putExtra("returnValue", pageTitle + ":   " + Utility.getLocationCountryName(getApplicationContext()));
                } else {
                    intent.putExtra("returnValue", pageTitle + ":   " + list.get(position));
                }
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    public void dataInit() {
        switch (functionNo) {
            case 1:
                pageTitle = "Gender";
                listValues = new String[] {"Male", "Female"};
                break;
            case 2:
                pageTitle = "Location";
                listValues = new String[] {"Use current location", "United States", "Kenya", "China"};
                break;
            default:
                break;
        }
    }

//    public String[] getSelectionValues() {
//        if (pageTitle.equals("Gender")) {
//            return new String[] {"Male", "Female"};
//        } else if (pageTitle.equals("Location")) {
//            return new String[] {"Use current location", "United States", "Kenya", "China"};
//        } else if (pageTitle.equals("Grades")) {
//            return new String[] {"Math", "Science", "Language Arts", "Social Studies", "History", "Add new one"};
//        } else if (pageTitle.equals("Interests")) {
//            return new String[] {"Reading", "Sports", "Add new one"};
//        } else if (pageTitle.equals("More Resources")) {
//            return new String[] {"Official Website", "Wiki page", "Search in Google"};
//        } else {
//            return new String[]{};
//        }
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
