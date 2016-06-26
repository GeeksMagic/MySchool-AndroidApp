package com.gmt.myschool.pages;

import android.support.v4.app.Fragment;

import com.gmt.myschool.fragments.Payment1Fragment;
import com.gmt.myschool.fragments.Payment2Fragment;
import com.gmt.myschool.ui.wizardpager.model.ModelCallbacks;
import com.gmt.myschool.ui.wizardpager.model.Page;
import com.gmt.myschool.ui.wizardpager.ui.PageFragmentCallbacks;

/**
 * Created by user on 6/12/2016.
 */
public class Payment2Page extends Page {

    PageFragmentCallbacks mCallback;

    public Payment2Page(ModelCallbacks callbacks, String title) {
        super(callbacks, title);
    }

    @Override
    public Fragment createFragment(PageFragmentCallbacks callback) {
        this.mCallback = callback;
        return Payment2Fragment.create(getKey(), callback);
    }

    @Override
    public boolean isCompleted() {
        return super.isCompleted();
    }
}
