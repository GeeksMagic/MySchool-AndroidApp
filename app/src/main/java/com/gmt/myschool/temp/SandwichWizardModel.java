package com.gmt.myschool.temp;

import android.content.Context;

import com.gmt.myschool.pages.Payment1Page;
import com.gmt.myschool.pages.Payment2Page;
import com.gmt.myschool.ui.wizardpager.model.AbstractWizardModel;
import com.gmt.myschool.ui.wizardpager.model.PageList;

public class SandwichWizardModel extends AbstractWizardModel {
    public SandwichWizardModel(Context context) {
        super(context);
    }

    @Override
    protected PageList onNewRootPageList() {
        return new PageList(
                new Payment1Page(this, "Enter Payment Details"),
                new Payment2Page(this, "Enter Your Details")
        );
    }
}
