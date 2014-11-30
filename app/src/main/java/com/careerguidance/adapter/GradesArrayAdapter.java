package com.careerguidance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.careerguidance.R;

import java.util.ArrayList;

/**
 * Created by yxt on 11/29/14.
 */
public class GradesArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private ArrayList<String> valuesLeft_;
    private ArrayList<String> valuesRight_;

    public GradesArrayAdapter(Context context, ArrayList<String> valuesLeft, ArrayList<String> valuesRight) {
        super(context, R.layout.listview_item_grade, valuesLeft);
        this.context = context;
        valuesLeft_ = valuesLeft;
        valuesRight_ = valuesRight;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.listview_item_grade, parent, false);
        TextView courseName = (TextView) rowView.findViewById(R.id.course_name);
        TextView score = (TextView) rowView.findViewById(R.id.score);

        courseName.setText(valuesLeft_.get(position));
        score.setText(valuesRight_.get(position));

        return rowView;
    }
}