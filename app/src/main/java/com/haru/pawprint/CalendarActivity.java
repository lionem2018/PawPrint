package com.haru.pawprint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haru.pawprint.adapter.CalendarRecyclerViewAdapter;
import com.haru.pawprint.dialog.MonthPickerDialog;
import com.haru.pawprint.util.RecordCalendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CalendarActivity extends AppCompatActivity {

    private CalendarRecyclerViewAdapter calendarRecyclerViewAdapter;

    private TextView textViewYear;
    private TextView textViewMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        textViewYear = findViewById(R.id.textview_calendar_year);
        textViewMonth = findViewById(R.id.textview_calendar_month);
        calendarRecyclerViewAdapter = new CalendarRecyclerViewAdapter(this);

        TextView btnBack = findViewById(R.id.textview_back);
        LinearLayout layoutShowMonthPicker = findViewById(R.id.layout_show_month_picker);
        MonthPickerDialog monthPickerDialog = new MonthPickerDialog(this, calendarRecyclerViewAdapter);
        monthPickerDialog.setCanceledOnTouchOutside(true);
        monthPickerDialog.setCancelable(true);
//        monthPickerDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
                // Acivity 전환 효과
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        initView();

        layoutShowMonthPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monthPickerDialog.show();
                monthPickerDialog.checkYearMonth();
            }
        });
    }

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerview_calendar_date);
        TextView textViewPrevMonth = findViewById(R.id.textview_prev_month);
        TextView textViewNextMonth = findViewById(R.id.textview_next_month);

        recyclerView.setLayoutManager(new GridLayoutManager(this, RecordCalendar.DAYS_OF_WEEK));
        recyclerView.setAdapter(calendarRecyclerViewAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        textViewPrevMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarRecyclerViewAdapter.changeToPrevMonth();
            }
        });

        textViewNextMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarRecyclerViewAdapter.changeToNextMonth();
            }
        });
    }


    public void refreshCurrentMonth(Calendar calendar) {
        SimpleDateFormat year = new SimpleDateFormat("yyyy", Locale.KOREAN);
        SimpleDateFormat month = new SimpleDateFormat("MM", Locale.KOREAN);
        textViewYear.setText(year.format(calendar.getTime()));
        textViewMonth.setText(month.format(calendar.getTime()));
    }
}