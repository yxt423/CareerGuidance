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
import com.careerguidance.utility.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class LocationActivity extends Activity {
//    private CareerGuidance careerGuidance;

    ArrayAdapter<String> adapter = null;
    String pageTitle = null;
    String[] listValues = null;

    // for location setting.
    Locale myLocale;

    String returnValue = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

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
                if (position == 0) {
                    returnValue = Utility.getLocationCountryName(getApplicationContext());
                } else {
                    returnValue = list.get(position);
                }
                Intent intent = new Intent();
                intent.putExtra("returnValue", returnValue);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    // change language setting.
//    public void setLocale(String lang) {
//
//        myLocale = new Locale(lang);
//        Resources res = getResources();
//        DisplayMetrics dm = res.getDisplayMetrics();
//        Configuration conf = res.getConfiguration();
//        conf.locale = myLocale;
//        res.updateConfiguration(conf, dm);
//        Intent refresh = new Intent(this, MainTabHost.class);
//        finish();
//        startActivity(refresh);
//    }

//    public void okButton(View v) {
//        Intent intent = new Intent();
//        intent.putExtra("returnValue", returnValue);
//        setResult(RESULT_OK, intent);
//        finish();
//    }

    public void dataInit() {
        pageTitle = getString(R.string.profile_func2);
        listValues = new String[] {"Use current location", "United States", "Kenya", "China"};
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.location, menu);
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
