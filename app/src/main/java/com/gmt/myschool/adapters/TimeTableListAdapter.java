package com.gmt.myschool.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gmt.myschool.R;
import com.gmt.myschool.database.TimeTable;

import java.util.List;

/**
 * Created by user on 6/8/2016.
 */
public class TimeTableListAdapter extends BaseAdapter {

    List<TimeTable> mTimeTableList;
    Context mContext;

    public TimeTableListAdapter(Context context, List<TimeTable> marksCardList) {
        this.mContext = context;
        this.mTimeTableList = marksCardList;
    }

    @Override
    public int getCount() {
        return mTimeTableList == null ? 0 : mTimeTableList.size();
    }

    @Override
    public Object getItem(int position) {
        return mTimeTableList == null ? null : mTimeTableList.get(position);
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
            convertView = inflater.inflate(R.layout.time_table_list_item, null);
            holder = new ViewHolder();
            holder.mPeriod = (TextView) convertView.findViewById(R.id.tt_period);
            holder.mSubject = (TextView) convertView.findViewById(R.id.tt_subject);
            holder.mTeacher = (TextView) convertView.findViewById(R.id.tt_teacher);
            holder.mTime = (TextView) convertView.findViewById(R.id.tt_time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        TimeTable marksCard = mTimeTableList.get(position);

        holder.mPeriod.setText(""+marksCard.getPeriod());
        holder.mSubject.setText(marksCard.getSubject());
        holder.mTeacher.setText(marksCard.getTeacher());
        holder.mTime.setText(marksCard.getTime());

        return convertView;
    }

    class ViewHolder {
        TextView mPeriod;
        TextView mSubject;
        TextView mTeacher;
        TextView mTime;
    }
}
