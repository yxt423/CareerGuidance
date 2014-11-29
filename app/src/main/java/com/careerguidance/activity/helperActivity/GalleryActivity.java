package com.careerguidance.activity.helperActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.careerguidance.R;

public class GalleryActivity extends Activity {

    private LinearLayout mGallery;
    private LayoutInflater mInflater;
    private Integer[] images = null;

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
        float density = metrics.density;
        int densityDpi = metrics.densityDpi;

        mInflater = LayoutInflater.from(this);

        images = new Integer[] {R.drawable.p1, R.drawable.p2, R.drawable.p3,
                R.drawable.p4, R.drawable.p5};

        mGallery = (LinearLayout) findViewById(R.id.id_gallery);

        for (int i = 0; i < images.length; i++) {
            View view = mInflater.inflate(R.layout.gallery_item,
                    mGallery, false);
            ImageView img = (ImageView) view
                    .findViewById(R.id.gallery_item_image);
            img.setMaxWidth(width);
            img.setMaxHeight(height);
            img.setImageResource(images[i]);
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
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
