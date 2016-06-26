package com.gmt.myschool.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gmt.myschool.R;
import com.gmt.myschool.database.PaymentFee;

import java.util.List;

/**
 * Created by user on 6/8/2016.
 */
public class PayHistoryListAdapter extends BaseAdapter {

    List<PaymentFee> mPayList;
    Context mContext;

    public PayHistoryListAdapter(Context context, List<PaymentFee> payList) {
        this.mContext = context;
        this.mPayList = payList;
    }

    @Override
    public int getCount() {
        return mPayList == null ? 0 : mPayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mPayList == null ? null : mPayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.payment_history_list_item, null);
            holder = new ViewHolder();
            holder.mDate = (TextView) convertView.findViewById(R.id.fd_pay_history_date);
            holder.mAmount = (TextView) convertView.findViewById(R.id.fd_pay_history_amount);
            holder.mRecept = (TextView) convertView.findViewById(R.id.fd_pay_history_recept);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        PaymentFee marksCard = mPayList.get(position);

        holder.mDate.setText("" + marksCard.getDate());
        holder.mAmount.setText(marksCard.getAmount());
        holder.mRecept.setText(marksCard.getRecept());

        return convertView;
    }

    class ViewHolder {
        TextView mDate;
        TextView mAmount;
        TextView mRecept;
    }
}
