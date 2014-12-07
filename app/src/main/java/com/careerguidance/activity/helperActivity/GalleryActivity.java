package com.careerguidance.activity.helperActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.careerguidance.dblayout.AndroidDatabaseManager;
import com.careerguidance.R;
import com.careerguidance.activity.TestDBActivity;

import java.util.ArrayList;

public class GalleryActivity extends Activity {

    private LinearLayout mGallery;
    private LayoutInflater mInflater;
//    private Integer[] images = null;
    private ArrayList<Integer> imageIds = new ArrayList<Integer>();
    private String imageIdentifierPrefix = "";
    private boolean hasPhotos = true;

    /**
     * inflate the gallery layout with a set of local pictures.
     * (set the max width and height of pictures to the screen size.)
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        mInflater = LayoutInflater.from(this);

        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        if (type.contains("career")) {
            imageIdentifierPrefix += "c_";
        } else if (type.contains("university")) {
            imageIdentifierPrefix += "u_";
        }

        String name = intent.getStringExtra("name");
        if (name.contains("Carnegie Mellon University")) {
            imageIdentifierPrefix += "cmu_";
        } else if (name.contains("Massachusetts Institute of Technology")) {
            imageIdentifierPrefix += "mit_";
        } else if (name.contains("Software")) {
            imageIdentifierPrefix += "se_";
        }
        else {
            hasPhotos = false;
        }

        for (int i = 1; i <= 10; i++) {
            int id = getResources().getIdentifier(imageIdentifierPrefix + String.valueOf(i), "drawable", "com.careerguidance");
            if (id != 0) {
                imageIds.add(id);
            }
        }

        mGallery = (LinearLayout) findViewById(R.id.id_gallery);

        if (hasPhotos) {
            for (int i = 0; i < imageIds.size(); i++) {
                View view = mInflater.inflate(R.layout.gallery_item,
                        mGallery, false);
                ImageView img = (ImageView) view.findViewById(R.id.gallery_item_image);
                img.setMinimumWidth(width);
                img.setMaxWidth(width);
                img.setMaxHeight(height);
                img.setImageResource(imageIds.get(i));
                mGallery.addView(view);
            }
        }
        else {
            View view = mInflater.inflate(R.layout.gallery_item,
                    mGallery, false);
            ImageView img = (ImageView) view.findViewById(R.id.gallery_item_image);
            img.setMaxWidth(width);
            img.setMaxHeight(height);
            img.setImageResource(R.drawable.no_image_available);
            mGallery.addView(view);
        }
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
