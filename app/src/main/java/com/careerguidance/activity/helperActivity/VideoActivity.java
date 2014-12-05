package com.careerguidance.activity.helperActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.VideoView;

import com.careerguidance.DBLayout.AndroidDatabaseManager;
import com.careerguidance.R;
import com.careerguidance.activity.TestDBActivity;

public class VideoActivity extends Activity {

    /**
     * start playing one video. get the video id from the intent, create its
     * uri accordingly, and start playing the video.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        Intent intent = getIntent();
        int videoNo = intent.getIntExtra("videoNo", 0);

        VideoView video1 =(VideoView)findViewById(R.id.video1);
        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(video1);
        video1.setMediaController(mediaController);
        video1.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + videoNo));
        video1.requestFocus();
        video1.start();
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
