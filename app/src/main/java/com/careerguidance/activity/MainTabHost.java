package com.careerguidance.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.Menu;
import android.view.MenuItem;

import com.careerguidance.dblayout.AndroidDatabaseManager;
import com.careerguidance.R;

public class MainTabHost extends FragmentActivity implements
        CareerListFragment.OnFragmentInteractionListener,
        ProfileFragment.OnFragmentInteractionListener,
        FindCareerMatchFragment.OnFragmentInteractionListener {

    FragmentTabHost mTabHost = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab_host);

        Intent intent = getIntent();
        int currentTab = intent.getIntExtra("currentTab", 0);

        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("Careers").setIndicator("Careers"), CareerListFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("Find Match").setIndicator("Find Match"), FindCareerMatchFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("Profile").setIndicator("Profile"), ProfileFragment.class, null);
        mTabHost.setCurrentTab(currentTab);
    }

    public FragmentTabHost getFragmentTabHost() {
        return mTabHost;
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

    public void onFragmentInteraction(Uri uri) {

    }
}
