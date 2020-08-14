package com.haru.pawprint.adapter;

import android.graphics.Color;
import android.graphics.Point;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haru.pawprint.CalendarActivity;
import com.haru.pawprint.R;
import com.haru.pawprint.util.RecordCalendar;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CalendarRecyclerViewAdapter extends RecyclerView.Adapter<CalendarRecyclerViewAdapter.CalendarViewHolder> {

    private RecordCalendar recordCalendar = new RecordCalendar();
    private CalendarActivity activity;

    public CalendarRecyclerViewAdapter(CalendarActivity activity){
        this.activity = activity;
        recordCalendar.initBaseCalendar(activity);
    }


    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calendar, parent, false);
        return new CalendarViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return RecordCalendar.LOW_OF_CALENDAR * RecordCalendar.DAYS_OF_WEEK;
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        if (position % RecordCalendar.DAYS_OF_WEEK == 0) holder.textView.setTextColor(Color.parseColor("#DC0000"));
        else holder.textView.setTextColor(Color.parseColor("#1C1C1C"));

        if (position < recordCalendar.prevMonthTailOffset
                || position >= recordCalendar.prevMonthTailOffset + recordCalendar.currentMonthMaxDate) {
            holder.textView.setAlpha(0.3f);
        } else {
            holder.textView.setAlpha(1f);
        }
        holder.textView.setText(recordCalendar.data.get(position).toString());
    }

    public void changeToPrevMonth() {
        recordCalendar.changeToPrevMonth();
        refreshView(recordCalendar.getCalendarInstance());
    }

    public void changeToNextMonth() {
        recordCalendar.changeToNextMonth();
        refreshView(recordCalendar.getCalendarInstance());
    }

    public void changeMonth(int year, int month){
        recordCalendar.changeMonth(year, month);
        refreshView(recordCalendar.getCalendarInstance());
    }

    private void refreshView(Calendar calendar) {
        notifyDataSetChanged();
        activity.refreshCurrentMonth(calendar);
    }


    class CalendarViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public CalendarViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textview_date);
        }
    }
}
