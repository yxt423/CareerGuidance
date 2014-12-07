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

import com.careerguidance.R;
import com.careerguidance.activity.helperActivity.GalleryActivity;
import com.careerguidance.activity.helperActivity.VideoActivity;
import com.careerguidance.adapter.CareerGuidance;
import com.careerguidance.adapter.StableArrayAdapter;
import com.careerguidance.dblayout.AndroidDatabaseManager;
import com.careerguidance.model.Career;
import com.careerguidance.utility.Utility;

import java.util.ArrayList;

/**
 * Show the detailed information of a career.
 */
public class CareerInfoActivity extends Activity {

    private CareerGuidance careerGuidance;

    TextView careerNameView = null;
    Career career;
    int careerId;

    TextView careerDescription;
    TextView careerSalary;
    TextView careerSkills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_info);

        careerGuidance = new CareerGuidance(getApplicationContext());

        // set career name on page title
        careerNameView = (TextView) findViewById(R.id.career_name);
        Intent intent = getIntent();
        careerId = intent.getIntExtra("career id", 0);
        career = careerGuidance.getCareer(careerId);
        careerNameView.setText(career.getName());

        createTextContentView();
        createListView();
    }

    public void createTextContentView() {
        careerDescription = (TextView) findViewById(R.id.career_description_txt);
        careerSalary = (TextView) findViewById(R.id.career_salary_txt);
        careerSkills = (TextView) findViewById(R.id.skills_required_txt);

        careerDescription.setText(career.getDescription());
        careerSalary.setText(String.valueOf(career.getAvgSalary()));

        String skillsStr = "";
        for (String s : career.getSkillsRequired()) {
            skillsStr += s + "\n";
        }
        careerSkills.setText(skillsStr);
    }

    public void createListView() {
        // create the listview
        String[] values = new String[] {"Famous Universities", "More Pictures", "Watch Videos"};
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
                Intent intent = null;
                switch (position) {
                    case 0:
                        intent = new Intent(getBaseContext(), UniversityListActivity.class);
                        intent.putExtra("career name", list.get(position));
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(getBaseContext(), GalleryActivity.class);
                        intent.putExtra("type", "career");
                        intent.putExtra("name", career.getName());
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(getBaseContext(), VideoActivity.class);
                        intent.putExtra("videoNo", R.raw.qi_li_xiang);
                        startActivity(intent);
                        break;
                    case 3:
//                        intent = new Intent(getBaseContext(), SelectionActivity.class);
//                        intent.putExtra("pageTitle", "More Resources");
//                        startActivity(intent);
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
