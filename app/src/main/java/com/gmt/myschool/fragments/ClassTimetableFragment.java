package com.gmt.myschool.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.gmt.myschool.R;
import com.gmt.myschool.adapters.MarksCardListAdapter;
import com.gmt.myschool.adapters.TimeTableListAdapter;
import com.gmt.myschool.database.MarksCard;
import com.gmt.myschool.database.TimeTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 6/1/2016.
 */
public class ClassTimetableFragment extends SuperFragment {

    com.rey.material.widget.Spinner mDaySpinner;
    ListView mTimeTableListView;
    TimeTableListAdapter mAdapter;
    ArrayAdapter<String> mDayAdapter;

    @Override
    public String getTitle() {
        return "Class Time Table";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_table, container, false);
        mDaySpinner = (com.rey.material.widget.Spinner) view.findViewById(R.id.day_selector);
        mTimeTableListView = (ListView) view.findViewById(R.id.time_table_list);
        mAdapter = new TimeTableListAdapter(getActivity(), getDummyTimeTable());
        mTimeTableListView.setAdapter(mAdapter);

        mDayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.days_array)) {

            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                ((TextView) v).setTextSize(16);
                ((TextView) v).setTextColor(Color.parseColor("#ffffff"));
                return v;
            }

            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                v.setBackgroundResource(R.drawable.spinner_item_selector);
                ((TextView) v).setTextColor(Color.parseColor("#000000"));
                ((TextView) v).setGravity(Gravity.CENTER);
                v.setPadding(10, 10, 10, 10);
                return v;
            }
        };
        mDaySpinner.setAdapter(mDayAdapter);
        return view;
    }

    public List<TimeTable> getDummyTimeTable() {
        List<TimeTable> list = new ArrayList<TimeTable>();
        list.add(new TimeTable(1, "Kannada", "Naren", "09:30\nto\n10:30"));
        list.add(new TimeTable(2, "Social", "Lakshmi", "10:30\nto\n11:30"));
        list.add(new TimeTable(3, "Maths", "Sushil", "11:30\nto\n12:30"));
        list.add(new TimeTable(4, "English", "Sanjay", "12:30\nto\n01:30"));
        list.add(new TimeTable(5, "Hindi", "Thejas", "03:30\nto\n04:30"));
        list.add(new TimeTable(6, "Science", "Girija", "04:30\nto\n05:30"));
        list.add(new TimeTable(7, "Drawing", "Praveen", "05:30\nto\n06:30"));
        return list;
    }
}
