package com.haru.pawprint.adapter;

import android.content.Context;
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
import com.haru.pawprint.dialog.HealthItemDialog;
import com.haru.pawprint.util.RecordCalendar;

import java.util.ArrayList;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CalendarRecyclerViewAdapter extends RecyclerView.Adapter<CalendarRecyclerViewAdapter.CalendarViewHolder> {

    private RecordCalendar recordCalendar = new RecordCalendar();
    private CalendarActivity activity;

    private HealthItemDialog healthItemDialog;

    public CalendarRecyclerViewAdapter(CalendarActivity activity){
        this.activity = activity;
        recordCalendar.initBaseCalendar(activity);

        healthItemDialog = new HealthItemDialog(activity);
        healthItemDialog.setCanceledOnTouchOutside(true);
        healthItemDialog.setCancelable(true);
    }


    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calendar, parent, false);
        return new CalendarViewHolder(view, activity);
    }

    @Override
    public int getItemCount() {
        return RecordCalendar.LOW_OF_CALENDAR * RecordCalendar.DAYS_OF_WEEK;
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        ArrayList<DialogHealthListItemAdapter.HealthListItem> healthList = new ArrayList<>();

        healthList.add(new DialogHealthListItemAdapter.HealthListItem(1, "11:00", "탄천 산책"));
        healthList.add(new DialogHealthListItemAdapter.HealthListItem(2, "주식", "하림 펫사료"));
        healthList.add(new DialogHealthListItemAdapter.HealthListItem(3, "간식", "버거킹 독퍼"));
        healthList.add(new DialogHealthListItemAdapter.HealthListItem(4, "영양제", "눈물싹싹"));
        healthList.add(new DialogHealthListItemAdapter.HealthListItem(5, "약", "심장사상충"));
        healthList.add(new DialogHealthListItemAdapter.HealthListItem(5, "약", "심장사상충"));
        healthList.add(new DialogHealthListItemAdapter.HealthListItem(5, "약", "심장사상충"));
        healthList.add(new DialogHealthListItemAdapter.HealthListItem(5, "약", "심장사상충"));
        healthList.add(new DialogHealthListItemAdapter.HealthListItem(5, "약", "심장사상충"));
        healthList.add(new DialogHealthListItemAdapter.HealthListItem(5, "약", "심장사상충"));
        healthList.add(new DialogHealthListItemAdapter.HealthListItem(5, "약", "심장사상충"));
        healthList.add(new DialogHealthListItemAdapter.HealthListItem(5, "약", "심장사상충"));
        healthList.add(new DialogHealthListItemAdapter.HealthListItem(5, "약", "심장사상충"));
        healthList.add(new DialogHealthListItemAdapter.HealthListItem(5, "약", "심장사상충"));
        healthList.add(new DialogHealthListItemAdapter.HealthListItem(5, "약", "심장사상충"));
        healthList.add(new DialogHealthListItemAdapter.HealthListItem(5, "약", "심장사상충"));
        healthList.add(new DialogHealthListItemAdapter.HealthListItem(5, "약", "심장사상충"));



        if (position % RecordCalendar.DAYS_OF_WEEK == 0) holder.textView.setTextColor(Color.parseColor("#DC0000"));
        else holder.textView.setTextColor(Color.parseColor("#1C1C1C"));

        if (position < recordCalendar.prevMonthTailOffset
                || position >= recordCalendar.prevMonthTailOffset + recordCalendar.currentMonthMaxDate) {
            holder.textView.setAlpha(0.3f);
        } else {
            holder.textView.setAlpha(1f);
            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    healthItemDialog.setItemArrayList(healthList);
                    healthItemDialog.show();
                    healthItemDialog.setDialogTitle(getYear(), getMonth(), recordCalendar.data.get(position), position % RecordCalendar.DAYS_OF_WEEK);
                }
            });

            holder.recyclerView.setAdapter(new DateHealthListItemAdapter(activity, healthList));
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

    public void changeYearMonth(int year, int month){
        recordCalendar.changeMonth(year, month);
        refreshView(recordCalendar.getCalendarInstance());
    }

    public int getYear(){
        return recordCalendar.getCalendarInstance().get(Calendar.YEAR);
    }

    public int getMonth(){
        return recordCalendar.getCalendarInstance().get(Calendar.MONTH);
    }

    private void refreshView(Calendar calendar) {
        notifyDataSetChanged();
        activity.refreshCurrentMonth(calendar);
    }

    class CalendarViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        RecyclerView recyclerView;
        LinearLayout linearLayout;

        public CalendarViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.layout_item_calendar_date);
            textView = itemView.findViewById(R.id.textview_date);
            recyclerView = itemView.findViewById(R.id.recyclerview_date_health_list);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        }
    }
}
