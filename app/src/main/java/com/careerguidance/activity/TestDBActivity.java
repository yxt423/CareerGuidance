package com.careerguidance.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.careerguidance.DBLayout.UserDataSource;
import com.careerguidance.R;
import com.careerguidance.adapter.CareerGuidance;
import com.careerguidance.model.User;

public class TestDBActivity extends Activity {

    private CareerGuidance objCareerGuidance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_db);

        objCareerGuidance = new CareerGuidance(this);

        CareerGuidance objCareerGuidance1 = new CareerGuidance(this);

        TextView test = (TextView) findViewById(R.id.output);

        String userId = "CG values: " + objCareerGuidance.getAllInterestNames().toString() + "\n";

        userId += "CG1 values: " + objCareerGuidance1.getAllUniversityNames().toString() + "\n";

        objCareerGuidance.setUserFirstName("Christopher");

        objCareerGuidance.setUserLastName("Njuguna");

        userId += "CG: FirstName : " + objCareerGuidance.getUserFirstName();

        userId += "CG1: LastName : " + objCareerGuidance1.getUserLastName();

        if (objCareerGuidance.userHasProfile())
            test.setText(userId);
        else
            test.setText("No Profile");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.test_db, menu);
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
