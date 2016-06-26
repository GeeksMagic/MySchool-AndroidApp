package com.gmt.myschool.ui.wizardpager.model;

import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.gmt.myschool.ui.wizardpager.ui.PageFragmentCallbacks;
import com.gmt.myschool.ui.wizardpager.ui.SingleChoiceFragment;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A page offering the user a number of mutually exclusive choices.
 */
public class SingleFixedChoicePage extends Page {
    protected ArrayList<String> mChoices = new ArrayList<String>();

    public SingleFixedChoicePage(ModelCallbacks callbacks, String title) {
        super(callbacks, title);
    }

    @Override
    public Fragment createFragment(PageFragmentCallbacks callback) {
        return SingleChoiceFragment.create(getKey(), callback);
    }

    public String getOptionAt(int position) {
        return mChoices.get(position);
    }

    public int getOptionCount() {
        return mChoices.size();
    }

    @Override
    public boolean isCompleted() {
        return !TextUtils.isEmpty(mData.getString(SIMPLE_DATA_KEY));
    }

    public SingleFixedChoicePage setChoices(String... choices) {
        mChoices.addAll(Arrays.asList(choices));
        return this;
    }

    public SingleFixedChoicePage setValue(String value) {
        mData.putString(SIMPLE_DATA_KEY, value);
        return this;
    }
}
