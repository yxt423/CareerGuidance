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

import com.careerguidance.R;
import com.careerguidance.dblayout.AndroidDatabaseManager;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Show activities what only contains a list of options and a title.
 * This activity is reused for location selection, gender selection, etc.
 */
public class SelectionActivity extends Activity {
//    private CareerGuidance careerGuidance;

    ArrayAdapter<String> adapter = null;
    String pageTitle = null;
    String[] listValues = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

//        careerGuidance = new CareerGuidance(getApplicationContext());
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
                intent.putExtra("returnValue", list.get(position));
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    public void dataInit() {
        pageTitle = getString(R.string.profile_func1);
        listValues = new String[] {"Male", "Female"};
    }

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
//        if (id == R.id.action_db_testing) {
//            Intent intent = new Intent(this, TestDBActivity.class);
//            startActivity(intent);
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
