package com.gmt.myschool.fragments;

import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmt.myschool.R;
import com.gmt.myschool.temp.SandwichWizardModel;
import com.gmt.myschool.ui.wizardpager.model.AbstractWizardModel;
import com.gmt.myschool.ui.wizardpager.model.ModelCallbacks;
import com.gmt.myschool.ui.wizardpager.model.Page;
import com.gmt.myschool.ui.wizardpager.ui.PageFragmentCallbacks;
import com.gmt.myschool.ui.wizardpager.ui.StepPagerStrip;

import java.util.List;

/**
 * Created by user on 6/10/2016.
 */
public class PaymentFragment extends SuperFragment implements
        PageFragmentCallbacks, ModelCallbacks {

    private double mAmount = -1;

    private ViewPager mPager;
    private MyPagerAdapter mPagerAdapter;
    private StepPagerStrip mStepPagerStrip;
    private AbstractWizardModel mWizardModel = new SandwichWizardModel(getActivity());
    private List<Page> mCurrentPageSequence;

    @Override
    public String getTitle() {
        return "Payment";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment, container, false);
        mAmount = getArguments().getDouble(FeeDetailsFragment.PAYMENT_AMOUNT);

        mWizardModel.registerListener(this);

        mPagerAdapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());
        mPager = (ViewPager) view.findViewById(R.id.pager);
        mPager.setAdapter(mPagerAdapter);
        mStepPagerStrip = (StepPagerStrip) view.findViewById(R.id.strip);
        mStepPagerStrip
                .setOnPageSelectedListener(new StepPagerStrip.OnPageSelectedListener() {
                    @Override
                    public void onPageStripSelected(int position) {
                        position = Math.min(mPagerAdapter.getCount() - 1,
                                position);
                        if (mPager.getCurrentItem() != position) {
                            mPager.setCurrentItem(position);
                        }
                    }
                });

        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mStepPagerStrip.setCurrentPage(position);
            }
        });

        onPageTreeChanged();
        return view;
    }

    @Override
    public void onPageTreeChanged() {
        mCurrentPageSequence = mWizardModel.getCurrentPageSequence();
        recalculateCutOffPage();
        mStepPagerStrip.setPageCount(mCurrentPageSequence.size());
        mPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPageDataChanged(Page page) {
        if (page.isRequired()) {
            if (recalculateCutOffPage()) {
                mPagerAdapter.notifyDataSetChanged();
            }
        }
    }

    private boolean recalculateCutOffPage() {
        // Cut off the pager adapter at first required page that isn't completed
        int cutOffPage = mCurrentPageSequence.size() + 1;
        for (int i = 0; i < mCurrentPageSequence.size(); i++) {
            Page page = mCurrentPageSequence.get(i);
            if (page.isRequired() && !page.isCompleted()) {
                cutOffPage = i;
                break;
            }
        }

        if (mPagerAdapter.getCutOffPage() != cutOffPage) {
            mPagerAdapter.setCutOffPage(cutOffPage);
            return true;
        }

        return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    @Override
    public Page onGetPage(String key) {
        return mWizardModel.findByKey(key);
    }

    @Override
    public void moveNext() {
        mPager.setCurrentItem(mPager.getCurrentItem() + 1);
    }

    @Override
    public void movePrevious() {
        mPager.setCurrentItem(mPager.getCurrentItem() - 1);
    }

    @Override
    public void finish() {
        getActivity().onBackPressed();
    }

    public class MyPagerAdapter extends FragmentStatePagerAdapter {
        private int mCutOffPage;
        private Fragment mPrimaryItem;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            if (i < mCurrentPageSequence.size())
                return mCurrentPageSequence.get(i).createFragment(PaymentFragment.this);
            return null;
        }

        @Override
        public int getItemPosition(Object object) {
            // TODO: be smarter about this
            if (object == mPrimaryItem) {
                // Re-use the current fragment (its position never changes)
                return POSITION_UNCHANGED;
            }

            return POSITION_NONE;
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position,
                                   Object object) {
            super.setPrimaryItem(container, position, object);
            mPrimaryItem = (Fragment) object;
        }

        @Override
        public int getCount() {
            return mCurrentPageSequence == null ? 0
                    : mCurrentPageSequence.size();
        }

        public void setCutOffPage(int cutOffPage) {
            if (cutOffPage < 0) {
                cutOffPage = Integer.MAX_VALUE;
            }
            mCutOffPage = cutOffPage;
        }

        public int getCutOffPage() {
            return mCutOffPage;
        }
    }

}
