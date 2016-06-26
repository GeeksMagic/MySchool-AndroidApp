package com.gmt.myschool.ui.wizardpager.ui;

import android.os.Parcelable;

import com.gmt.myschool.ui.wizardpager.model.Page;

public interface PageFragmentCallbacks extends Parcelable{
    Page onGetPage(String key);
    void moveNext();
    void movePrevious();
    void finish();
}
