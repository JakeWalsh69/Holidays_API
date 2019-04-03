package com.example.holiday_app_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.holiday_app_android.R;
import com.example.holiday_app_android.models.Holiday;
import java.util.ArrayList;

@SuppressWarnings("NullableProblems")
public class HolidayAdapter extends ArrayAdapter<Holiday> {

    private static class ViewHolder {
        TextView name;
        TextView date;
    }

    public HolidayAdapter(Context context, ArrayList<Holiday> holidays)
    {
        super(context, R.layout.activity_main, holidays);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Holiday holiday = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_holiday, parent, false);

//            viewHolder.id = (TextView) convertView.findViewById(R.id.value_title_id);
            viewHolder.name = convertView.findViewById(R.id.value_title_name);
            viewHolder.date = convertView.findViewById(R.id.value_title_date);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

//        viewHolder.id.setText(holiday.getID());
        assert holiday != null;
        viewHolder.name.setText(holiday.getName());
        viewHolder.date.setText(holiday.getDate());

        return convertView;
    }
}