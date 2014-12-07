package com.careerguidance.activity.helperActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.careerguidance.R;
import com.careerguidance.activity.TestDBActivity;
import com.careerguidance.adapter.CareerGuidance;
import com.careerguidance.dblayout.AndroidDatabaseManager;

import java.util.ArrayList;

public class InterestsActivity extends Activity {
    private CareerGuidance careerGuidance;

    ListView listView = null;
    ArrayAdapter<String> adapter = null;
    ArrayList<String> interestsList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);

        careerGuidance = new CareerGuidance(getApplicationContext());

        TextView pageTitleView = (TextView) findViewById(R.id.page_title);
        pageTitleView.setText(getString(R.string.profile_func4));
        listView = (ListView) findViewById(R.id.listview);

        interestsList = (ArrayList<String>) careerGuidance.getAllInterestNames();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, interestsList);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);
    }

    public void submitButton(View v) {
        SparseBooleanArray checked = listView.getCheckedItemPositions();
        ArrayList<String> selectedItems = new ArrayList<String>();
        for (int i = 0; i < checked.size(); i++) {
            int position = checked.keyAt(i);
            if (checked.valueAt(i))
                selectedItems.add(adapter.getItem(position));
        }
        finish();
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
