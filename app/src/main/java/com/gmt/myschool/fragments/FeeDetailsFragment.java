package com.gmt.myschool.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.gmt.myschool.R;
import com.gmt.myschool.activity.MainActivity;
import com.gmt.myschool.adapters.PayHistoryListAdapter;
import com.gmt.myschool.database.PaymentFee;
import com.gmt.myschool.ui.expandable.layout.ExpandableLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 6/1/2016.
 */
public class FeeDetailsFragment extends SuperFragment implements View.OnClickListener {

    public static final String PAYMENT_AMOUNT = "PAYMENT_AMOUNT";
    ExpandableLayout mAdmissionFeeDetails, mBalanceFee, mPaymentHistory;
    Button mPayOnline, mPayNow;
    ListView mPayHistoryListView;
    PayHistoryListAdapter mAdapter;
    TextView mFDBalanceFee;


    @Override
    public String getTitle() {
        return "Fee Details";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fee_details, container, false);
        mAdmissionFeeDetails = (ExpandableLayout) view.findViewById(R.id.admission_fee_details);
        mBalanceFee = (ExpandableLayout) view.findViewById(R.id.balance_fee);
        mPaymentHistory = (ExpandableLayout) view.findViewById(R.id.payment_history);
        mPayOnline = (Button) view.findViewById(R.id.pay_online);
        mPayNow = (Button) view.findViewById(R.id.fd__balance_pay_now);
        mPayHistoryListView = (ListView) view.findViewById(R.id.pay_history_list);
        mFDBalanceFee = (TextView) view.findViewById(R.id.fd__balance_fee);

        mAdapter = new PayHistoryListAdapter(getActivity(), getDummyPayHistory());
        mPayHistoryListView.setAdapter(mAdapter);

        mAdmissionFeeDetails.setOnClickListener(this);
        mBalanceFee.setOnClickListener(this);
        mPaymentHistory.setOnClickListener(this);
        mPayOnline.setOnClickListener(this);
        mPayNow.setOnClickListener(this);

        mPayHistoryListView.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.admission_fee_details:
                if (mAdmissionFeeDetails.isOpened()) {
                    mAdmissionFeeDetails.hide();
                } else {
                    mAdmissionFeeDetails.show();
                }
                mBalanceFee.hide();
                mPaymentHistory.hide();
                break;
            case R.id.balance_fee:
                if (mBalanceFee.isOpened()) {
                    mBalanceFee.hide();
                } else {
                    mBalanceFee.show();
                }
                mAdmissionFeeDetails.hide();
                mPaymentHistory.hide();
                break;
            case R.id.payment_history:
                if (mPaymentHistory.isOpened()) {
                    mPaymentHistory.hide();
                } else {
                    mPaymentHistory.show();
                }
                mAdmissionFeeDetails.hide();
                mBalanceFee.hide();
                break;
            case R.id.pay_online:
                mAdmissionFeeDetails.hide();
                mBalanceFee.hide();
                mPaymentHistory.hide();
                payOnline(-1);
                break;
            case R.id.fd__balance_pay_now:
                double amount = Double.parseDouble(mFDBalanceFee.getText().toString());
                payOnline(amount);
                break;
        }
    }

    public List<PaymentFee> getDummyPayHistory() {
        List<PaymentFee> list = new ArrayList<PaymentFee>();
        list.add(new PaymentFee("02-JUN-2014", "1000.00", "R98347"));
        list.add(new PaymentFee("15-AUG-2014", "9000.00", "P92374"));
        list.add(new PaymentFee("06-JUN-2015", "4000.00", "CX5837"));
        list.add(new PaymentFee("21-AUG-2015", "500.00", "S31945"));
        list.add(new PaymentFee("01-JUN-2016", "650.00", "G94875"));
        list.add(new PaymentFee("18-AUG-2016", "700.00", "TY2376"));
        return list;
    }

    private void payOnline(double amount) {
        SuperFragment payment = new PaymentFragment();
        Bundle b = new Bundle();
        b.putDouble(PAYMENT_AMOUNT, amount);
        payment.setArguments(b);
        ((MainActivity) getActivity()).replaceFragment(payment, null);
    }
}
