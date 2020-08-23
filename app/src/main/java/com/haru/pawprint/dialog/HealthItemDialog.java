package com.haru.pawprint.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.haru.pawprint.R;
import com.haru.pawprint.RecordActivity;
import com.haru.pawprint.adapter.DialogHealthListItemAdapter;
import com.haru.pawprint.adapter.RecordHealthButtonListAdapter;
import com.haru.pawprint.adapter.RecordHealthSpinnerListAdapter;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * TODO: document your custom view class.
 */
public class HealthItemDialog extends Dialog {

    private Context context;

    private ArrayList<DialogHealthListItemAdapter.HealthListItem> healthList;

    private int year;
    private int month;
    private int date;
    private int day;

    public HealthItemDialog(@NonNull Context context) {
        super(context);

        this.context = context;
    }

    public void setItemArrayList(ArrayList<DialogHealthListItemAdapter.HealthListItem> healthList){
        this.healthList = healthList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);

        setContentView(R.layout.dialog_health_list);

        TextView textViewGoDiary = findViewById(R.id.textview_go_diary);
        RecyclerView recyclerViewHealthList = findViewById(R.id.recyclerview_dialog_healthlist);

        recyclerViewHealthList.setLayoutManager(new LinearLayoutManager(context));
        recyclerViewHealthList.setAdapter(new DialogHealthListItemAdapter(getContext(), healthList));

        textViewGoDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                Intent intent = new Intent(context, RecordActivity.class);
                context.startActivity(intent);
            }
        });
    }

    public void setDialogTitle(int year, int month, int date, int day){

        this.year = year;
        this.month = month;
        this.date = date;
        this.day = day;

        String[] DAY = {"일", "월", "화", "수", "목", "금", "토"};

        String title = (this.month+1) + "월 " + this.date + "일 " + DAY[this.day];
        ((TextView)findViewById(R.id.textview_dialog_title)).setText(title);
    }
}
