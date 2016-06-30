package com.gmt.myschool.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.gmt.myschool.BR;
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
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        TimeTable marksCard = mTimeTableList.get(position);
        holder.getBinding().setVariable(BR.timetable, marksCard);
        holder.getBinding().executePendingBindings();
        return convertView;
    }

    class ViewHolder {
        private ViewDataBinding binding;

        public ViewHolder(View rowView) {
            binding = DataBindingUtil.bind(rowView);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }
}
