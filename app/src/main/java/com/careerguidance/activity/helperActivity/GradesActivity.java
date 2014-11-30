package com.careerguidance.activity.helperActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.careerguidance.R;
import com.careerguidance.adapter.GradesArrayAdapter;

import java.util.ArrayList;
import java.util.Collections;

public class GradesActivity extends Activity {

    GradesArrayAdapter adapter = null;
    String[] courseNameStr = new String[] {"Math", "Science", "Language Arts", "Social Studies", "History"};
    String[] courseCourses = new String[] {"90", "85", "73", "98", "62"};
    ArrayList<String> courseNameList = new ArrayList<String>();
    ArrayList<String> scoreList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        TextView pageTitleView = (TextView) findViewById(R.id.page_title);
        pageTitleView.setText("Grades");

        final ListView listview = (ListView) findViewById(R.id.listview);
        Collections.addAll(courseNameList, courseNameStr);
        Collections.addAll(scoreList, courseCourses);
        adapter = new GradesArrayAdapter(this, courseNameList, scoreList);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                if (position == courseNameList.size() - 1) {
                    addNewCourse();
                } else {
                    editCourse(position);
                }
            }
        });
    }

    public void addNewCourse() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Edit course");

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_edit_course, null);
        alert.setView(dialogView);

        final EditText courseName = (EditText) dialogView.findViewById(R.id.course_name_edit);
        final EditText score = (EditText) dialogView.findViewById(R.id.score_edit);

        alert.setPositiveButton("Add Course", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                courseNameList.set(courseNameList.size() - 1, courseName.getText().toString());
                scoreList.set(scoreList.size() - 1, score.getText().toString());
                courseNameList.add("Add new course");
                scoreList.add("");
                adapter.notifyDataSetChanged();
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) { }
        });

        alert.show();
    }

    public void editCourse(final int position) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Edit course");

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_edit_course, null);
        alert.setView(dialogView);

        final EditText courseName = (EditText) dialogView.findViewById(R.id.course_name_edit);
        final EditText score = (EditText) dialogView.findViewById(R.id.score_edit);
        courseName.setText(courseNameList.get(position));
        score.setText(scoreList.get(position));

        alert.setPositiveButton("Update Course", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                courseNameList.set(position, courseName.getText().toString());
                scoreList.set(position, score.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) { }
        });

        alert.show();
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
