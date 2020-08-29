package com.haru.pawprint.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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
    private RadioGroup radioGroupMonth1;
    private RadioGroup radioGroupMonth2;
    private RadioGroup radioGroupMonth3;
    private TextView textViewYear;

    private RadioGroup.OnCheckedChangeListener onCheckedChangeListenerRadioGroupForMonth1;
    private RadioGroup.OnCheckedChangeListener onCheckedChangeListenerRadioGroupForMonth2;
    private RadioGroup.OnCheckedChangeListener onCheckedChangeListenerRadioGroupForMonth3;

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
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().setAttributes(layoutParams);

        setContentView(R.layout.dialog_month_picker);

        ImageView imageViewYearUp = findViewById(R.id.imageview_year_up);
        ImageView imageViewYearDown = findViewById(R.id.imageview_year_down);
        textViewYear = findViewById(R.id.textview_year);
        radioGroupMonth1 = findViewById(R.id.radiogroup_month1);
        radioGroupMonth2 = findViewById(R.id.radiogroup_month2);
        radioGroupMonth3 = findViewById(R.id.radiogroup_month3);

        imageViewYearUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearCheckAllRadioGroup();

                if(Integer.parseInt(textViewYear.getText().toString()) > 0)
                    textViewYear.setText(""+(Integer.parseInt(textViewYear.getText().toString())-1));
                checkYearMonth();
            }
        });

        imageViewYearDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearCheckAllRadioGroup();

                textViewYear.setText(""+(Integer.parseInt(textViewYear.getText().toString())+1));
                checkYearMonth();
            }
        });

        onCheckedChangeListenerRadioGroupForMonth1 = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(radioGroupMonth2.getCheckedRadioButtonId() != -1) radioGroupMonth2.clearCheck();
                if(radioGroupMonth3.getCheckedRadioButtonId() != -1) radioGroupMonth3.clearCheck();
                dismiss();

                if(i == R.id.radiobutton_jan)
                    calendarRecyclerViewAdapter.changeYearMonth(Integer.parseInt(textViewYear.getText().toString()), Calendar.JANUARY);
                else if(i == R.id.radiobutton_feb)
                    calendarRecyclerViewAdapter.changeYearMonth(Integer.parseInt(textViewYear.getText().toString()), Calendar.FEBRUARY);
                else if(i == R.id.radiobutton_mar)
                    calendarRecyclerViewAdapter.changeYearMonth(Integer.parseInt(textViewYear.getText().toString()), Calendar.MARCH);
                else if(i == R.id.radiobutton_apr)
                    calendarRecyclerViewAdapter.changeYearMonth(Integer.parseInt(textViewYear.getText().toString()), Calendar.APRIL);
            }
        };

        onCheckedChangeListenerRadioGroupForMonth2  = new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(radioGroupMonth1.getCheckedRadioButtonId() != -1) radioGroupMonth1.clearCheck();
                if(radioGroupMonth3.getCheckedRadioButtonId() != -1) radioGroupMonth3.clearCheck();
                dismiss();

                if(i == R.id.radiobutton_may)
                    calendarRecyclerViewAdapter.changeYearMonth(Integer.parseInt(textViewYear.getText().toString()), Calendar.MAY);
                else if(i == R.id.radiobutton_jun)
                    calendarRecyclerViewAdapter.changeYearMonth(Integer.parseInt(textViewYear.getText().toString()), Calendar.JUNE);
                else if(i == R.id.radiobutton_jul)
                    calendarRecyclerViewAdapter.changeYearMonth(Integer.parseInt(textViewYear.getText().toString()), Calendar.JULY);
                else if(i == R.id.radiobutton_aug)
                    calendarRecyclerViewAdapter.changeYearMonth(Integer.parseInt(textViewYear.getText().toString()), Calendar.AUGUST);
            }
        };

        onCheckedChangeListenerRadioGroupForMonth3  = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(radioGroupMonth1.getCheckedRadioButtonId() != -1) radioGroupMonth1.clearCheck();
                if(radioGroupMonth2.getCheckedRadioButtonId() != -1) radioGroupMonth2.clearCheck();
                dismiss();

                if(i == R.id.radiobutton_sep)
                    calendarRecyclerViewAdapter.changeYearMonth(Integer.parseInt(textViewYear.getText().toString()), Calendar.SEPTEMBER);
                else if(i == R.id.radiobutton_oct)
                    calendarRecyclerViewAdapter.changeYearMonth(Integer.parseInt(textViewYear.getText().toString()), Calendar.OCTOBER);
                else if(i == R.id.radiobutton_nov)
                    calendarRecyclerViewAdapter.changeYearMonth(Integer.parseInt(textViewYear.getText().toString()), Calendar.NOVEMBER);
                else if(i == R.id.radiobutton_dec)
                    calendarRecyclerViewAdapter.changeYearMonth(Integer.parseInt(textViewYear.getText().toString()), Calendar.DECEMBER);
            }
        };

        radioGroupMonth1.setOnCheckedChangeListener(onCheckedChangeListenerRadioGroupForMonth1);
        radioGroupMonth2.setOnCheckedChangeListener(onCheckedChangeListenerRadioGroupForMonth2);
        radioGroupMonth3.setOnCheckedChangeListener(onCheckedChangeListenerRadioGroupForMonth3);

        clearCheckAllRadioGroup();
        checkYearMonth();
    }

    public void clearCheckAllRadioGroup(){
        radioGroupMonth1.setOnCheckedChangeListener(null);
        radioGroupMonth2.setOnCheckedChangeListener(null);
        radioGroupMonth3.setOnCheckedChangeListener(null);

        if(radioGroupMonth1.getCheckedRadioButtonId() != -1) radioGroupMonth1.clearCheck();
        if(radioGroupMonth2.getCheckedRadioButtonId() != -1) radioGroupMonth2.clearCheck();
        if(radioGroupMonth3.getCheckedRadioButtonId() != -1) radioGroupMonth3.clearCheck();

        radioGroupMonth1.setOnCheckedChangeListener(onCheckedChangeListenerRadioGroupForMonth1);
        radioGroupMonth2.setOnCheckedChangeListener(onCheckedChangeListenerRadioGroupForMonth2);
        radioGroupMonth3.setOnCheckedChangeListener(onCheckedChangeListenerRadioGroupForMonth3);
    }

    public void checkYearMonth(){
        if(calendarRecyclerViewAdapter.getYear() == Integer.parseInt(textViewYear.getText().toString())) {
            radioGroupMonth1.setOnCheckedChangeListener(null);
            radioGroupMonth2.setOnCheckedChangeListener(null);
            radioGroupMonth3.setOnCheckedChangeListener(null);

            switch(calendarRecyclerViewAdapter.getMonth()){
                case 0:
                    radioGroupMonth1.check(R.id.radiobutton_jan);
                    break;
                case 1:
                    radioGroupMonth1.check(R.id.radiobutton_feb);
                    break;
                case 2:
                    radioGroupMonth1.check(R.id.radiobutton_mar);
                    break;
                case 3:
                    radioGroupMonth1.check(R.id.radiobutton_apr);
                    break;
                case 4:
                    radioGroupMonth2.check(R.id.radiobutton_may);
                    break;
                case 5:
                    radioGroupMonth2.check(R.id.radiobutton_jun);
                    break;
                case 6:
                    radioGroupMonth2.check(R.id.radiobutton_jul);
                    break;
                case 7:
                    radioGroupMonth2.check(R.id.radiobutton_aug);
                    break;
                case 8:
                    radioGroupMonth3.check(R.id.radiobutton_sep);
                    break;
                case 9:
                    radioGroupMonth3.check(R.id.radiobutton_oct);
                    break;
                case 10:
                    radioGroupMonth3.check(R.id.radiobutton_nov);
                    break;
                case 11:
                    radioGroupMonth3.check(R.id.radiobutton_dec);
                    break;
            }
            radioGroupMonth1.setOnCheckedChangeListener(onCheckedChangeListenerRadioGroupForMonth1);
            radioGroupMonth2.setOnCheckedChangeListener(onCheckedChangeListenerRadioGroupForMonth2);
            radioGroupMonth3.setOnCheckedChangeListener(onCheckedChangeListenerRadioGroupForMonth3);

        }
        else{
            if(radioGroupMonth1.getCheckedRadioButtonId() != -1) radioGroupMonth1.clearCheck();
            if(radioGroupMonth2.getCheckedRadioButtonId() != -1) radioGroupMonth2.clearCheck();
            if(radioGroupMonth3.getCheckedRadioButtonId() != -1) radioGroupMonth3.clearCheck();
        }
    }
}
