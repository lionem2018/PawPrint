package com.haru.pawprint.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.haru.pawprint.EditPictureActivity;
import com.haru.pawprint.R;
import com.haru.pawprint.RecordActivity;
import com.haru.pawprint.RegisterPetActivity;
import com.haru.pawprint.SelectPetActivity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ScreenSlidePagerAdapter extends RecyclerView.Adapter<ScreenSlidePagerAdapter.MyViewHolder> {

    private Context context;

    public ScreenSlidePagerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_main_date, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 기록 화면으로 이동
                context.startActivity(new Intent(context, RecordActivity.class));
            }
        });

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    }



    @Override
    public int getItemCount() {
        return 15;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBanner;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBanner = itemView.findViewById(R.id.img_cardview);
        }
    }
}