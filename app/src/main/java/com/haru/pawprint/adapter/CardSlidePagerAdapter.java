package com.haru.pawprint.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.haru.pawprint.R;
import com.haru.pawprint.RecordActivity;

import java.net.URI;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CardSlidePagerAdapter extends RecyclerView.Adapter<CardSlidePagerAdapter.CardViewHolder> {

    private ArrayList<URI> uriArrayList;
    private Context context;

    public CardSlidePagerAdapter(Context context, ArrayList<URI> uriArrayList) {
        this.context = context;
        this.uriArrayList = uriArrayList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_main_date, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 기록 화면으로 이동
                context.startActivity(new Intent(context, RecordActivity.class));
            }
        });

        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        holder.imgBanner.setBackgroundResource(R.drawable.pic_example);
    }



    @Override
    public int getItemCount() {
        return uriArrayList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBanner;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBanner = itemView.findViewById(R.id.imageview_cardview);
        }
    }
}