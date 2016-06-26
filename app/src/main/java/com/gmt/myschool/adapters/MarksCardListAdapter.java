package com.gmt.myschool.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gmt.myschool.R;
import com.gmt.myschool.database.MarksCard;

import java.util.List;

/**
 * Created by user on 6/8/2016.
 */
public class MarksCardListAdapter extends BaseAdapter {

    List<MarksCard> mMarksCardList;
    Context mContext;

    public MarksCardListAdapter(Context context, List<MarksCard> marksCardList) {
        this.mContext = context;
        this.mMarksCardList = marksCardList;
    }

    @Override
    public int getCount() {
        return mMarksCardList == null ? 0 : mMarksCardList.size();
    }

    @Override
    public Object getItem(int position) {
        return mMarksCardList == null ? null : mMarksCardList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.marks_card_list_item, null);
            holder = new ViewHolder();
            holder.mSubject = (TextView) convertView.findViewById(R.id.subject);
            holder.mMax = (TextView) convertView.findViewById(R.id.max);
            holder.mMin = (TextView) convertView.findViewById(R.id.min);
            holder.mObtained = (TextView) convertView.findViewById(R.id.obtained);
            holder.mPercentage = (TextView) convertView.findViewById(R.id.percentage);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        MarksCard marksCard = mMarksCardList.get(position);

        holder.mSubject.setText(marksCard.getSubject());
        holder.mMax.setText(marksCard.getMax());
        holder.mMin.setText(marksCard.getMin());
        holder.mObtained.setText(marksCard.getObtained());
        holder.mPercentage.setText(marksCard.getPercentage());

        return convertView;
    }

    class ViewHolder {
        TextView mSubject;
        TextView mMax;
        TextView mMin;
        TextView mObtained;
        TextView mPercentage;
    }
}
