package com.haru.pawprint.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.haru.pawprint.R;
import com.haru.pawprint.adapter.CalendarRecyclerViewAdapter;

import java.util.Calendar;

import androidx.annotation.NonNull;

public class MonthPickerDialog extends Dialog {

    private Context context;
    private CalendarRecyclerViewAdapter calendarRecyclerViewAdapter;
    private RadioButton radioButtonJan;

    public MonthPickerDialog(@NonNull Context context, @NonNull CalendarRecyclerViewAdapter calendarRecyclerViewAdapter) {
        super(context);
        this.context = context;
        this.calendarRecyclerViewAdapter = calendarRecyclerViewAdapter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);

        setContentView(R.layout.dialog_month_picker);

        ImageView imageViewYearUp = findViewById(R.id.imageview_year_up);
        ImageView imageViewYearDown = findViewById(R.id.imageview_year_down);
        TextView textViewYear = findViewById(R.id.textview_year);
        RadioGroup radioGroupMonth = findViewById(R.id.radiogroup_month);
        radioButtonJan = findViewById(R.id.radiobutton_jan);

        imageViewYearUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(textViewYear.getText().toString()) > 0)
                    textViewYear.setText(""+(Integer.parseInt(textViewYear.getText().toString())-1));
            }
        });

        imageViewYearDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewYear.setText(""+(Integer.parseInt(textViewYear.getText().toString())+1));
            }
        });

        radioGroupMonth.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                
            }
        });
    }
}
