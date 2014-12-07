package com.careerguidance.activity.helperActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.careerguidance.activity.TestDBActivity;
import com.careerguidance.adapter.CareerGuidance;
import com.careerguidance.adapter.GradesArrayAdapter;
import com.careerguidance.dblayout.AndroidDatabaseManager;

import java.util.ArrayList;
import java.util.Collections;

public class GradesActivity extends Activity {
    private CareerGuidance careerGuidance;

    GradesArrayAdapter adapter = null;
    String[] courseNameStr = new String[] {"Math", "Science", "Language Arts", "Social Studies", "History"};
    String[] courseCourses = new String[] {"0", "0", "0", "0", "0", "0", "0", "0", "0"};
    ArrayList<String> courseNameList = new ArrayList<String>();
    ArrayList<String> scoreList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);

        careerGuidance = new CareerGuidance(getApplicationContext());

        final TextView pageTitleView = (TextView) findViewById(R.id.page_title);
        pageTitleView.setText("Grades");

        final ListView listview = (ListView) findViewById(R.id.listview);
        Collections.addAll(courseNameList, courseNameStr);
        Collections.addAll(scoreList, courseCourses);

        courseNameList = (ArrayList<String>) careerGuidance.getAllSubjectNames();

        adapter = new GradesArrayAdapter(this, courseNameList, scoreList);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                editCourse(position);
            }
        });

        // long click on item to delete.
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, int position, long id) {
                courseNameList.remove(position);
                scoreList.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    // on click method of list item (course)
    public void editCourse(final int position) {
        showEditCourseDialog("Edit course", null, courseNameList.get(position), scoreList.get(position), position, 2);
    }

    // on click method of add new course button
    public void addNewCourse(View v) {
        showEditCourseDialog("New course", null, null, null, 0, 1);
    }

    public void showEditCourseDialog(final String title, String message,
                                     final String courseNameStr, final String scoreStr, final int position, final int functionType) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(title);
        if (message != null && message.length() != 0) {
            alert.setMessage(message);
        }

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_edit_course, null);
        alert.setView(dialogView);

        final EditText courseName = (EditText) dialogView.findViewById(R.id.course_name_edit);
        final EditText score = (EditText) dialogView.findViewById(R.id.score_edit);
        courseName.setText(courseNameStr == null ? "" : courseNameStr);
        score.setText(scoreStr == null ? "" : scoreStr);

        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String newCourseName = courseName.getText().toString();
                String newScore = score.getText().toString();
                if (newCourseName == null || newCourseName.length() == 0) {
                    showEditCourseDialog(title, "Course Name can not be empty.", courseNameStr, scoreStr, position, functionType);
                } else if (newScore == null || newScore.length() == 0) {
                    showEditCourseDialog(title, "Score can not be empty.", courseNameStr, scoreStr, position, functionType);
                } else {
                    if (functionType == 1) { // add new course
                        courseNameList.add(courseName.getText().toString());
                        scoreList.add(score.getText().toString());
                        adapter.notifyDataSetChanged();
                    } else if (functionType == 2) { // edit course
                        courseNameList.set(position, courseName.getText().toString());
                        scoreList.set(position, score.getText().toString());
                        adapter.notifyDataSetChanged();
                    }
                }
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
