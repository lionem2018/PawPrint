package com.haru.pawprint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.haru.pawprint.adapter.TimeStampRecyclerViewAdapter;

import java.util.ArrayList;

public class TimeStampActivity extends AppCompatActivity {

    private int PICTURE_ROW = 3;
    ArrayList<Uri> uriArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_stamp);

        TextView btnBack = findViewById(R.id.textview_back);
        ImageView imageViewMonth = findViewById(R.id.imageview_time_stamp_month);

        imageViewMonth.setImageURI(Uri.parse(((PawPrintApplication)getApplicationContext()).getCurrentPet().getPetProfileUri()));  // just test);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                // Acivity 전환 효과
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        RelativeLayout bottomSheet = findViewById(R.id.bottomsheet_time_stamp);
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        uriArrayList = new ArrayList<Uri>();
        for(int i = 0; i < 31; i++)
            uriArrayList.add(Uri.parse(((PawPrintApplication)getApplicationContext()).getCurrentPet().getPetProfileUri()));  // just test

        RecyclerView recyclerView = bottomSheet.findViewById(R.id.recyclerview_time_stamp_picture_list);
        recyclerView.setLayoutManager(new GridLayoutManager(this, PICTURE_ROW));
        recyclerView.setAdapter(new TimeStampRecyclerViewAdapter(this, uriArrayList));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }
}