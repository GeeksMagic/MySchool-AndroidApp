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
import com.gmt.myschool.database.MarksCard;
import com.rey.material.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 6/1/2016.
 */
public class TestScoreDetailsFragment extends SuperFragment {

    Spinner mYear, mTestNumber;
    ArrayAdapter<String> mYearAdapter, mTestNumberAdapter;
    String mSelectedYear, mSelectedTestNumber;
    TextView mPercentage, mResult;
    ListView mMarksCardList;
    MarksCardListAdapter mAdapter;
    TextView mTotalMax, mTotalMin, mTotalObtained, mTotalPercentage;

    @Override
    public String getTitle() {
        return "Test Marks Details";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_marks_details, container, false);
        mYear = (Spinner) view.findViewById(R.id.year_selector);
        mTestNumber = (Spinner) view.findViewById(R.id.test_number_selector);
        mPercentage = (TextView) view.findViewById(R.id.percentage);
        mResult = (TextView) view.findViewById(R.id.result);
        mTotalMax = (TextView) view.findViewById(R.id.total_max);
        mTotalMin = (TextView) view.findViewById(R.id.total_min);
        mTotalObtained = (TextView) view.findViewById(R.id.total_obtained);
        mTotalPercentage = (TextView) view.findViewById(R.id.total_percentage);
        mMarksCardList = (ListView) view.findViewById(R.id.marks_card_list);
        mAdapter = new MarksCardListAdapter(getActivity(), getDummyMarks());
        mMarksCardList.setAdapter(mAdapter);
        mYearAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.years_array)) {

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
                v.setPadding(5, 5, 5, 5);
                return v;
            }
        };

        mTestNumberAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.test_number_array)) {

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
                v.setPadding(5, 5, 5, 5);
                return v;
            }
        };

        mYear.setAdapter(mYearAdapter);
        mTestNumber.setAdapter(mTestNumberAdapter);

        mYear.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(Spinner parent, View view, int position, long id) {
                mSelectedYear = getResources().getStringArray(R.array.years_array)[position];
                updateView();
            }
        });
        mTestNumber.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(Spinner parent, View view, int position, long id) {
                mSelectedTestNumber = getResources().getStringArray(R.array.test_number_array)[position];
                updateView();
            }
        });
        updateScore(80);
        return view;
    }

    private List<MarksCard> getDummyMarks() {
        List<MarksCard> list = new ArrayList<MarksCard>();
        list.add(new MarksCard("", "1", "2016", "Kannada", "35", "100", "89", "89%"));
        list.add(new MarksCard("", "1", "2016", "English", "35", "100", "78", "78%"));
        list.add(new MarksCard("", "1", "2016", "Hindi", "35", "100", "69", "69%"));
        list.add(new MarksCard("", "1", "2016", "Science", "35", "100", "83", "83%"));
        list.add(new MarksCard("", "1", "2016", "Social", "35", "100", "64", "64%"));
        list.add(new MarksCard("", "1", "2016", "Maths", "35", "100", "96", "96%"));
        return list;
    }

    private void updateView() {

    }

    private void updateScore(int total) {
        mPercentage.setText(total + "%");
        if (total >= 35) {
            mResult.setText("PASS");
            mResult.setTextColor(Color.parseColor("#4CAF50"));
        } else {
            mResult.setText("FAIL");
            mResult.setTextColor(Color.parseColor("#F44336"));
        }

        mTotalMax.setText("600");
        mTotalMin.setText("210");
        mTotalObtained.setText("479");
        mTotalPercentage.setText(""+total);
    }
}
