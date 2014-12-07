package com.careerguidance.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.careerguidance.R;
import com.careerguidance.activity.helperActivity.GalleryActivity;
import com.careerguidance.activity.helperActivity.VideoActivity;
import com.careerguidance.adapter.CareerGuidance;
import com.careerguidance.adapter.StableArrayAdapter;
import com.careerguidance.dblayout.AndroidDatabaseManager;
import com.careerguidance.model.University;
import com.careerguidance.utility.Utility;

import java.util.ArrayList;

public class UniversityInfoActivity extends Activity {
    private CareerGuidance careerGuidance;


    ImageView universityPhoto;
    TextView universityNameView;
    TextView universityDescription;
    TextView universityLocation;

    String universityName = null;
    int universityId;
    University university;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_info);

        careerGuidance = new CareerGuidance(getApplicationContext());

        createTextContentView();
        createListView();
    }

    // set university text content
    public void createTextContentView() {
        universityNameView = (TextView) findViewById(R.id.university_name);
        universityDescription = (TextView) findViewById(R.id.university_description_txt);
        universityLocation = (TextView) findViewById(R.id.university_location_txt);
        universityPhoto = (ImageView) findViewById(R.id.university_image);

        Intent intent = getIntent();
        universityId = intent.getIntExtra("university id", 0);
        universityName = intent.getStringExtra("university name");
        university = careerGuidance.getUniversityById(universityId);

        // set university photo
        String imageIdentifierPrefix = "u_";
        if (universityName.contains("Carnegie Mellon University")) {
            imageIdentifierPrefix += "cmu_";
        } else if (universityName.contains("Massachusetts Institute of Technology")) {
            imageIdentifierPrefix += "mit_";
        }
        int id = getResources().getIdentifier(imageIdentifierPrefix + "1", "drawable", "com.careerguidance");
        if (id != 0) {
            universityPhoto.setImageResource(id);
        }

        universityNameView.setText(universityName);
        universityDescription.setText(university.getDescription());
    }

    // create the listview
    public void createListView() {
        String[] values = new String[] {"More Pictures", "Watch Videos"};
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
                        intent.putExtra("type", "university");
                        intent.putExtra("name", universityName);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(getBaseContext(), VideoActivity.class);
                        intent.putExtra("videoNo", R.raw.video_cmu);
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
