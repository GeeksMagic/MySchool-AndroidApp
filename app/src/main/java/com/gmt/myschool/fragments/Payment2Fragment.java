package com.gmt.myschool.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gmt.myschool.R;
import com.gmt.myschool.activity.SuperActivity;
import com.gmt.myschool.ui.SweetAlertDialog;
import com.gmt.myschool.ui.wizardpager.model.Page;
import com.gmt.myschool.ui.wizardpager.ui.PageFragmentCallbacks;
import com.rengwuxian.materialedittext.MaterialEditText;

/**
 * Created by user on 6/12/2016.
 */
public class Payment2Fragment extends SuperFragment {

    private static final String ARG_KEY = "key";
    private static final String CALLBACK_KEY = "callback";

    private String mKey;
    private PageFragmentCallbacks mCallbacks;
    private Page mPage;

    MaterialEditText mName, mEmail, mPhone;
    TextView mRemainingBalance;
    Button mMakePayment;

    public static Payment2Fragment create(String key, PageFragmentCallbacks callback) {
        Bundle args = new Bundle();
        args.putString(ARG_KEY, key);
        args.putParcelable(CALLBACK_KEY, callback);

        Payment2Fragment fragment = new Payment2Fragment();
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
        View rootView = inflater.inflate(R.layout.fragment_payment_2, container, false);
        ((TextView) rootView.findViewById(android.R.id.title)).setText(mPage.getTitle());
        mName = (MaterialEditText) rootView.findViewById(R.id.name);
        mEmail = (MaterialEditText) rootView.findViewById(R.id.email_address);
        mPhone = (MaterialEditText) rootView.findViewById(R.id.phone_number);
        mRemainingBalance = (TextView) rootView.findViewById(R.id.remaining_balance);
        mMakePayment = (Button) rootView.findViewById(R.id.make_payment);
        Button back = (Button) rootView.findViewById(R.id.previous);
        mMakePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mName.getText().toString().trim().length() <= 0) {
                    mName.setError("Please Enter Name");
                } else if (mEmail.getText().toString().trim().length() <= 0) {
                    mEmail.setError("Please Enter Email");
                } else if (mPhone.getText().toString().trim().length() <= 0) {
                    mPhone.setError("Please Enter Phone Number");
                } else if (mPhone.getText().toString().trim().length() < 10) {
                    mPhone.setError("Please Enter Complete Phone Number");
                } else {
                    ((SuperActivity) getActivity()).showProgressDialog("Making Payment");
                    ((SuperActivity) getActivity()).dismissProgressDialogSuccess("Payment Made Successfully", new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                            mCallbacks.finish();
                        }
                    });
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallbacks.movePrevious();
            }
        });
        return rootView;
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {
            int balance = 2300 - Integer.parseInt(Payment1Fragment.mAmountEntered);
            mRemainingBalance.setText("" + balance);
        }
    }

}