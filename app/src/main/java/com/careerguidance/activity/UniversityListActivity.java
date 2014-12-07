package com.careerguidance.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.careerguidance.R;
import com.careerguidance.adapter.CareerGuidance;
import com.careerguidance.adapter.StableArrayAdapter;
import com.careerguidance.dblayout.AndroidDatabaseManager;
import com.careerguidance.model.University;

import java.util.ArrayList;

/**
 * show a list of universities. clicking on one item of the list will lead
 * to the university detail info page.
 */
public class UniversityListActivity extends Activity {
    private CareerGuidance careerGuidance = null;

    String careerName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_list);

        careerGuidance = new CareerGuidance(getApplicationContext());

        final ListView listview = (ListView) findViewById(R.id.listview);
//        String[] values = new String[] { "Massachusetts Institute of Technology", "Stanford University", "Carnegie Mellon University",
//                "University of California—​Berkeley", "University of Illinois—​Urbana-​Champaign", "University of Michigan—​Ann Arbor",
//                "Georgia Institute of Technology ", "Cornell University",
//                "Purdue University—​West Lafayette"};

//        final ArrayList<String> list = new ArrayList<String>();
//        for (int i = 0; i < values.length; ++i) {
//            list.add(values[i]);
//        }

        final ArrayList<String> list = (ArrayList<String>) careerGuidance.getAllUniversityNames();
        final ArrayList<University> universityList = (ArrayList<University>) careerGuidance.getAllUniversity();
        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                Intent intent = new Intent(getBaseContext(), UniversityInfoActivity.class);
                intent.putExtra("university id", position);
                intent.putExtra("university name", list.get(position));
                startActivity(intent);
            }
        });
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
