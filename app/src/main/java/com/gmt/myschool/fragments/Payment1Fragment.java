package com.gmt.myschool.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gmt.myschool.R;
import com.gmt.myschool.ui.wizardpager.model.Page;
import com.gmt.myschool.ui.wizardpager.ui.PageFragmentCallbacks;
import com.rengwuxian.materialedittext.MaterialEditText;

/**
 * Created by user on 6/12/2016.
 */
public class Payment1Fragment extends SuperFragment {

    private static final String ARG_KEY = "key";
    private static final String CALLBACK_KEY = "callback";

    private String mKey;
    private PageFragmentCallbacks mCallbacks;
    private Page mPage;

    public static String mAmountEntered = "0";

    public static Payment1Fragment create(String key, PageFragmentCallbacks callback) {
        Bundle args = new Bundle();
        args.putString(ARG_KEY, key);
        args.putParcelable(CALLBACK_KEY, callback);

        Payment1Fragment fragment = new Payment1Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        mKey = args.getString(ARG_KEY);
        this.mCallbacks = (PageFragmentCallbacks) args.get(CALLBACK_KEY);
        mPage = mCallbacks.onGetPage(mKey);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_payment_1, container, false);
        ((TextView) rootView.findViewById(android.R.id.title)).setText(mPage.getTitle());
        final MaterialEditText pay = (MaterialEditText) rootView.findViewById(R.id.entered_amount_to_apy);
        final Button next = (Button) rootView.findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pay.getText().toString().trim().length() <= 0) {
                    pay.setError("Please Enter Amount");
                } else {
                    mAmountEntered = pay.getText().toString();
                    mCallbacks.moveNext();
                }
            }
        });
        return rootView;
    }

}
